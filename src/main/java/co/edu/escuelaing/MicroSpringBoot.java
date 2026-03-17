package co.edu.escuelaing;

import java.io.File;
import java.lang.reflect.Method;

import co.edu.escuelaing.annotations.GetMapping;
import co.edu.escuelaing.annotations.RestController;
import co.edu.escuelaing.server.HttpServer;

public class MicroSpringBoot {

    public static void main(String[] args) throws Exception {
        HttpServer servidor = new HttpServer(6000);

        if (args.length > 0) {
            loadClass(args[0], servidor);
        } else {
            scanClasspath(servidor);
        }

        servidor.start();
    }

    private static void loadClass(String className, HttpServer servidor) throws Exception {
        Class<?> clazz = Class.forName(className);

        if (clazz.isAnnotationPresent(RestController.class)) {
            Object controllerInstance = clazz.getDeclaredConstructor().newInstance();

            for (Method methodRef : clazz.getDeclaredMethods()) {
                if (methodRef.isAnnotationPresent(GetMapping.class)) {
                    String path = methodRef.getAnnotation(GetMapping.class).value();
                    servidor.registerRoute(path, methodRef, controllerInstance);
                    System.out.println("Registered: GET " + path + " -> " + className);
                }
            }
        }
    }

    private static void scanClasspath(HttpServer servidor) throws Exception {
        String classpath = System.getProperty("java.class.path");
        for (String path : classpath.split(File.pathSeparator)) {
            File file = new File(path);
            if (file.isDirectory()) {
                scanDirectory(file, file, servidor);
            }
        }
    }

    private static void scanDirectory(File rootDir, File currentDir, HttpServer servidor) throws Exception {
        for (File file : currentDir.listFiles()) {
            if (file.isDirectory()) {
                scanDirectory(rootDir, file, servidor);
            } else if (file.getName().endsWith(".class")) {
                String className = rootDir.toURI().relativize(file.toURI())
                        .getPath()
                        .replace("/", ".")
                        .replace(".class", "");
                try {
                    loadClass(className, servidor);
                } catch (Exception ignored) {}
            }
        }
    }
}