FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/notification-management-0.0.1-SNAPSHOT.jar /app/notification-management-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/notification-management-0.0.1-SNAPSHOT.jar"]
