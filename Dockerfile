# Use a base image with OpenJDK
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/ms-card-1.0-SNAPSHOT.jar /app/app.jar

# Expose the port your application runs on
EXPOSE 8989

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
