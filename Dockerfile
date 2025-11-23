<<<<<<< HEAD
# 1️⃣ Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -DskipTests clean package

# 2️⃣ Run Stage
FROM eclipse-temurin:21.0.8_10-jdk-focal  # ✅ valid image

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
=======
# 1️⃣ Use OpenJDK 21 Alpine as base image
FROM openjdk:21-jdk-alpine

# 2️⃣ Set working directory
WORKDIR /app

# 3️⃣ Copy jar file from target folder
COPY target/*.jar app.jar

# 4️⃣ Expose port (Render provides dynamic PORT)
EXPOSE 8080

# 5️⃣ Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
>>>>>>> 6477591625410d0c8481c2d0a50e8ffdee19761d
