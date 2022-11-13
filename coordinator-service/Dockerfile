FROM openjdk:11-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} coordinator-app.jar

ENTRYPOINT ["java", "-jar", "coordinator-app.jar"]