# Use a base image with Maven to build the application
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the entire project directory into the container
COPY . /app

# Build the application inside the container
RUN mvn clean package -DskipTests

# Stage for running the application
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/your-app.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
