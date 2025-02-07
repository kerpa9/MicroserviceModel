FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/model_courses-0.0.1-SNAPSHOT.jar .
EXPOSE 3100
ENTRYPOINT ["java", "-jar", "model_courses-0.0.1-SNAPSHOT.jar"]
