# Stage 1: Build the Spring Boot app
FROM maven:3.9.0-eclipse-temurin-20 AS build
COPY . .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot app
FROM eclipse-temurin:20-jdk
COPY --from=build /target/*.jar demo.jar
EXPOSE 6060
ENTRYPOINT ["java", "-jar", "app.jar"]
