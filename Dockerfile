# Use a base image with Java installed
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app
COPY *.jar app.jar

CMD ["java", "-jar", "/app.jar"]
