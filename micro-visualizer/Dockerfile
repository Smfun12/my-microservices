FROM --platform=linux/amd64 adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8083
ARG JAR_FILE=target/micro-visualizer-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]