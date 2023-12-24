FROM adoptopenjdk:11-jre-hotspot

# Create a directory to hold the application files
RUN mkdir /app

# Copy the JAR file from the project root into the /app directory in the image
COPY JavaApp-0.0.1-SNAPSHOT.jar /app/app.jar

# Set the working directory inside the container
WORKDIR /app

# Command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]
