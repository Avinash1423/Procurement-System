FROM maven:3.9.6-openjdk-23 AS BUILD
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:23-jdk
COPY --from=build target/system-0.0.1-SNAPSHOT.jar system.jar
EXPOSE 8080
ENTRYPOINT["java","-jar","-system.jar"]

