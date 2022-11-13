FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user-app.jar

ENTRYPOINT ["java", "-jar", "user-app.jar"]