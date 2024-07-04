FROM docker.io/maven:3-eclipse-temurin-17 AS builder
ADD pom.xml .
ADD thesisman-app/pom.xml ./thesisman-app/pom.xml
ADD thesisman-desktop/pom.xml ./thesisman-desktop/pom.xml
RUN mvn dependency:go-offline -pl thesisman-app
COPY . .
RUN mvn clean package -pl thesisman-app

FROM docker.io/eclipse-temurin:17
VOLUME /tmp
EXPOSE 8082
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
COPY --from=builder thesisman-app/target/thesisman-app-0.2.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]
