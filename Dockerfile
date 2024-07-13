FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTest

FROM openjdk:17-alpine
COPY --from=build /target/qrcodegenerator-0.0.1-SNAPSHOT.jar qrcodegenerator.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","qrcodegenerator.jar"]