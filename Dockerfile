# Use a base image with Java installed
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

CMD ["java", "-jar", "JavaApp-0.0.1-SNAPSHOT.jar"]
