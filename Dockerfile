FROM eclipse-temurin:17-jre-jammy

WORKDIR /usrapp/bin

COPY /target/classes /usrapp/bin/classes


CMD ["java","-cp","./classes","co.edu.escuelaing.MicroSpringBoot"]