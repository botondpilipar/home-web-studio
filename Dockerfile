FROM eclipse-temurin:25

ARG JAR_FILE=target/home-web-studio-0.0.1-SNAPSHOT.jar

RUN mkdir /opt/app

RUN groupadd -r spring
RUN useradd -r -g spring spring

USER spring:spring

COPY ${JAR_FILE} hws-backend.jar

ENTRYPOINT ["java","-jar","/hws-backend.jar"]