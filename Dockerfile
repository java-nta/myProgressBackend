# Stage 1: Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the Maven project files and build the application
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM adoptium/openjdk:17-jre AS runtime

# Create a directory to hold the application files
RUN mkdir /app

# Copy the JAR file from the build stage to the runtime image
COPY --from=build /app/target/JavaApp-0.0.1-SNAPSHOT.jar /app/app.jar

# Set the working directory inside the container
WORKDIR /app

# Command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]
