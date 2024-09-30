FROM ubuntu:22.04 as base

RUN apt-get update -y && \
    apt-get install openjdk-17-jdk maven -y

RUN mkdir -p /app

WORKDIR /app

COPY . .

RUN mvn clean install

FROM openjdk:17-alpine as java

RUN mkdir -p /app

WORKDIR /app

EXPOSE 8080

COPY --from=base /app/target/*.jar application.jar

ENTRYPOINT ["java","-jar", "application.jar"]
