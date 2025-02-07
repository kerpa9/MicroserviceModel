FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/model_users-0.0.1-SNAPSHOT.jar .
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "model_users-0.0.1-SNAPSHOT.jar"]
