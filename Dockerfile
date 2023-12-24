# Use a base image with Maven to build the application
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the entire project directory into the container
COPY . /app

# Build the application inside the container
RUN mvn clean package -DskipTests

# Stage for running the application
FROM adoptopenjdk:17-jre-hotspot

WORKDIR /app

# Copy the compiled Spring Boot JAR file from the build stage
COPY --from=build /app/target/your-app.jar /app/app.jar

# Expose the port your app runs on (if needed)
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
