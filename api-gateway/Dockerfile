# Use lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR into the container
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Expose Eureka default port
EXPOSE 9000

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
