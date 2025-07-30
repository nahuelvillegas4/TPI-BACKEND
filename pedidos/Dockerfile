FROM openjdk:21-jdk-slim 

WORKDIR /app

COPY target/pedidos-0.0.1-SNAPSHOT.jar /app/pedidos.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "pedidos.jar"]