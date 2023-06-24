# First stage: Build the application
FROM maven:3.9.1-amazoncorretto-19 as builder
WORKDIR /app

COPY . .

RUN mvn clean package -Dmaven.test.skip

# Second stage: Run the application
FROM amazoncorretto:19 as runner
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar" ]