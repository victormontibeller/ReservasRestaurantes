FROM maven:3.8.6-openjdk-17-slim AS build

WORKDIR /mnt/c/Users/leona/OneDrive/Documentos/Estudos_em_java/TechChallenge_III

COPY . /mnt/c/Users/leona/OneDrive/Documentos/Estudos_em_java/TechChallenge_III

RUN mvn package

FROM openjdk:17-jdk-slim
#ubuntu

COPY target/ReservasRestaurantes-0.0.1-SNAPSHOT.jar ReservasRestaurantes-0.0.1-SNAPSHOT.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "ReservasRestaurantes-0.0.1-SNAPSHOT.jar"]