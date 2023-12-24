# Use a base image with Java installed
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Spring Boot JAR file into the container
COPY target/your-spring-app.jar /app/app.jar

# Expose the port your app runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
