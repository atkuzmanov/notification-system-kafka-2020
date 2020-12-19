FROM adoptopenjdk/openjdk11:alpine
MAINTAINER  atkuzmanov <https://github.com/atkuzmanov>
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
COPY ${JAR_FILE} notification-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java","-jar","/notification-service-0.0.1-SNAPSHOT.jar"]
