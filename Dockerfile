FROM maven:3-jdk-11 as builder

WORKDIR /usr/src/app
COPY codeBase/ .


RUN mvn clean package

FROM openjdk:11


COPY --from=builder /usr/src/app/target/finalProject-0.1-spring-boot.jar /usr/app/finalProject-0.1-spring-boot.jar

ENTRYPOINT ["java", "-jar", "/usr/app/finalProject-0.1-spring-boot.jar"]