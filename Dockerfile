#FROM eclipse-temurin:21-jdk
#
#WORKDIR /app
#
#COPY target/notesapi-0.0.1-SNAPSHOT.jar app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java","-jar","app.jar"]

# Step 1: Build the application using Maven and JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Compile and package the application (skipping unit tests to save free-tier time)
RUN mvn clean package -DskipTests

# Step 2: Create the lightweight runtime image
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the compiled .jar dynamically from the build step
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]