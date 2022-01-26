FROM maven:3-jdk-11 as builder

WORKDIR /usr/src/app
COPY codeBase/ .


RUN mvn clean package

#FROM openjdk:11

FROM adoptopenjdk/openjdk11:jre-11.0.9_11.1-alpine

COPY --from=builder /usr/src/app/target/finalProject-0.1-spring-boot.jar /usr/app/finalProject-0.1-spring-boot.jar


RUN apk update && \
    apk add mysql-client

RUN apk upgrade

RUN apk add curl

RUN mkdir -p /usr/src/conf

#COPY appConfigs/ /usr/src/conf

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/usr/app/finalProject-0.1-spring-boot.jar", "--spring.config.name=applicationConfig,secretConfig", "--spring.config.location=file:///usr/src/conf/"]
