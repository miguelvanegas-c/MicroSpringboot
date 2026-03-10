package co.edu.escuelaing.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import co.edu.escuelaing.annotations.RequestParam;

public class HttpServer {
    private final int serverPort;
    private final Map<String, Method> routeMap = new HashMap<>();
    private final Map<String, Object> controllerMap = new HashMap<>();

    public HttpServer(int portNumber) { this.serverPort = portNumber; }

    public void registerRoute(String endpoint, Method methodRef, Object controllerInstance) {
        routeMap.put(endpoint, methodRef);
        controllerMap.put(endpoint, controllerInstance);
    }

    public void start() throws IOException {
        ServerSocket socketServidor = new ServerSocket(serverPort);
        System.out.println("Server running on http://localhost:" + serverPort);

        while (true) {
            Socket cliente = socketServidor.accept();
            handleRequest(cliente);
        }
    }

    private void handleRequest(Socket clientSocket) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());

        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isEmpty()) {
            clientSocket.close();
            return;
        }

        String[] requestParts = requestLine.split(" ");
        String fullEndpoint = requestParts[1];

        String endpoint = fullEndpoint.contains("?") ? fullEndpoint.split("\\?")[0] : fullEndpoint;
        String queryString = fullEndpoint.contains("?") ? fullEndpoint.split("\\?")[1] : "";

        if (routeMap.containsKey(endpoint)) {
            Method methodRef = routeMap.get(endpoint);
            Object controllerInstance = controllerMap.get(endpoint);
            try {
                String response = invokeMethod(methodRef, controllerInstance, queryString);
                sendResponse(writer, 200, response);
            } catch (Exception e) {
                sendResponse(writer, 500, "Error: " + e.getMessage());
            }
        } else {
            sendResponse(writer, 404, "<h1>404 - Not Found</h1>");
        }
        clientSocket.close();
    }

    private String invokeMethod(Method methodRef, Object controllerInstance, String queryString)
            throws Exception {
        Map<String, String> queryMap = parseQuery(queryString);
        Parameter[] methodParams = methodRef.getParameters();
        Object[] args = new Object[methodParams.length];

        for (int i = 0; i < methodParams.length; i++) {
            RequestParam rp = methodParams[i].getAnnotation(RequestParam.class);
            if (rp != null) {
                args[i] = queryMap.getOrDefault(rp.value(), rp.defaultValue());
            }
        }
        return (String) methodRef.invoke(controllerInstance, args);
    }

    private Map<String, String> parseQuery(String queryString) {
        Map<String, String> queryPairs = new HashMap<>();
        if (queryString == null || queryString.isEmpty()) return queryPairs;
        for (String pair : queryString.split("&")) {
            String[] kv = pair.split("=");
            if (kv.length == 2) queryPairs.put(kv[0], kv[1]);
        }
        return queryPairs;
    }

    private void sendResponse(PrintWriter writer, int statusCode, String body) {
        writer.println("HTTP/1.1 " + statusCode + " OK");
        writer.println("Content-Type: text/html");
        writer.println("Connection: close");
        writer.println();
        writer.println(body);
        writer.flush();
    }
}