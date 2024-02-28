FROM ubuntu:latest
LABEL authors="yurii kotsiuba"

FROM maven:3-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package spring-boot:repackage -Pprod

FROM openjdk:17-jdk-slim

ENV PROJECT_NAME="client-prod"

COPY --from=build /app/target/${PROJECT_NAME}.jar /app/${PROJECT_NAME}.jar

WORKDIR /app

CMD ["java", "-jar", "client-prod.jar"]