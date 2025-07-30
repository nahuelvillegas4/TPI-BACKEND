FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/gateway-0.0.1-SNAPSHOT.jar /app/gateway.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "gateway.jar"]