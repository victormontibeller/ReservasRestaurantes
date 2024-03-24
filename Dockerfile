FROM maven:3.8.6-openjdk-17-slim AS build

WORKDIR /app

COPY . /app/

RUN mvn package

FROM openjdk:17-jdk-slim
#ubuntu

COPY target/ReservasRestaurantes-0.0.1-SNAPSHOT.jar ReservasRestaurantes-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ReservasRestaurantes-0.0.1-SNAPSHOT.jar"]