# 1️⃣ Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn -DskipTests clean package

# 2️⃣ Run Stage
FROM eclipse-temurin:21.0.8_10-jdk-focal  # ✅ Valid tag

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
