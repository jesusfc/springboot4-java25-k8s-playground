FROM maven:3.9.11-eclipse-temurin-25 AS build

WORKDIR /workspace

COPY pom.xml .
COPY java-app/pom.xml java-app/pom.xml

RUN mvn -B -f java-app/pom.xml dependency:go-offline

COPY java-app/src java-app/src

RUN mvn -B -f java-app/pom.xml clean package spring-boot:repackage -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

LABEL org.opencontainers.image.title="k8s-playground" \
      org.opencontainers.image.version="latest"

RUN useradd --system --create-home --home-dir /home/spring spring

COPY --from=build /workspace/java-app/target/*.jar app.jar

RUN chown spring:spring app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]