# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/target/system-0.0.1-SNAPSHOT.jar system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "system.jar"]

