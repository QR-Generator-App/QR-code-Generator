FROM openjdk:17
EXPOSE 8080
ADD target/qrgenerator.jar qrgenerator.jar
ENTRYPOINT ["java","-jar","/qrgenerator.jar"]