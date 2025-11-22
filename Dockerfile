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
