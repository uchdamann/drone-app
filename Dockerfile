FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/drone-api.jar

WORKDIR /opt/app

COPY ${JAR_FILE} drone.jar

ENTRYPOINT ["java","-jar","drone.jar"]