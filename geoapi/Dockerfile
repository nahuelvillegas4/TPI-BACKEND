FROM openjdk:21-jdk-slim        

WORKDIR /app

COPY target/geoapi-0.0.1-SNAPSHOT.jar /app/geoapi.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "geoapi.jar"]