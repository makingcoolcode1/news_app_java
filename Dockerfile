# Stage 1: Build the application using Maven
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set the working directory inside the container for this stage
WORKDIR /workspace

# Copy pom.xml and the src directory for Maven to build
COPY pom.xml .
COPY src ./src

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image with JRE
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container for runtime
WORKDIR /workspace

# Copy the .jar file from the build stage to the runtime container
COPY --from=build /workspace/target/news_app_java-1.0-SNAPSHOT.jar /workspace/news_app_java-1.0-SNAPSHOT.jar

EXPOSE 8080

# Run the .jar file (ensure the path is correct)
ENTRYPOINT ["java", "-jar", "/workspace/news_app_java-1.0-SNAPSHOT.jar"]
