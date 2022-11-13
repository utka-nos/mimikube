FROM openjdk:11-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user-app.jar

ENTRYPOINT ["java", "-jar", "user-app.jar"]