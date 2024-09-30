# Use the official OpenJDK 11 image as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file of the Java application into the container
COPY target/automation-application.jar /app/application.jar

# Expose the port that your Java application will use (if needed)
# For example, if your app runs on port 8080, expose that port
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "application.jar"]