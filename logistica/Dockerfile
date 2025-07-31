FROM openjdk:21-jdk-slim 

WORKDIR /app

COPY target/logistica-0.0.1-SNAPSHOT.jar /app/logistica.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "logistica.jar"]