FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/ecommerce-backend-0.0.1-SNAPSHOT.jar ecommerce-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ecommerce-backend.jar"]