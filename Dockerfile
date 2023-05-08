# First stage: Build the application
FROM maven:3.9.1-amazoncorretto-19 as builder
WORKDIR /app

COPY . .

RUN mvn clean package

# Second stage: Run the application
FROM openjdk:19 as runner
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar" ]