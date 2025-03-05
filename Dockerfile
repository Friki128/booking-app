FROM maven:3.8.4-openjdk-17 AS base
WORKDIR /app 
COPY ./pom.xml /app
RUN mvn dependency:go-offline -B
COPY ./README.md /app
COPY ./src /app/src
RUN mvn package

FROM eclipse-temurin:17-jre-alpine
run mkdir /opt/app
EXPOSE 8080
COPY --from=base /app/target/BookingApp-0.0.1-basic.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]

