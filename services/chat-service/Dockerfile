# First stage: Build the application
FROM maven:3.9.1-amazoncorretto-19 as builder
WORKDIR /app

COPY . .

ARG CHAT_SERVICE_DB_URL
ARG CHAT_SERVICE_DB_USERNAME
ARG CHAT_SERVICE_DB_PASSWORD

RUN mvn clean package -Dspring-boot.run.jvmArguments="--enable-preview"

# Second stage: Run the application
FROM openjdk:20 as runner
WORKDIR /app

ARG CHAT_SERVICE_DB_URL
ARG CHAT_SERVICE_DB_USERNAME
ARG CHAT_SERVICE_DB_PASSWORD

ENV SPRING_DATASOURCE_URL=$CHAT_SERVICE_DB_URL
ENV SPRING_DATASOURCE_USERNAME=$CHAT_SERVICE_DB_USERNAME
ENV SPRING_DATASOURCE_PASSWORD=$CHAT_SERVICE_DB_PASSWORD

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar" ]