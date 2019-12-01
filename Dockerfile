FROM openjdk:8u201-jre-alpine3.9
RUN mkdir /app
COPY ./target/scala-2.13/scalatra-app-assembly-*.jar /app/
WORKDIR /app
ENTRYPOINT java -jar scalatra-app-assembly-*.jar