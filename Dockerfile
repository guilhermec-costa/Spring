FROM eclipse-temurin:21-jdk-alpine

WORKDIR /SpringApp

COPY target/SpringApp-0.0.1-SNAPSHOT.jar /SpringApp/SpringApp.jar

EXPOSE 8080

CMD ["java", "-jar", "SpringApp.jar"]