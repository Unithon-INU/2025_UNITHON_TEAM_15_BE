FROM openjdk:17-jdk-slim
LABEL maintainer="team15-server"
COPY ./build/libs/team15-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]