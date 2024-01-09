FROM openjdk:17

ENV APP_NAME email-service

MAINTAINER ${APP_NAME}

COPY ./target/${APP_NAME}.jar  /app/${APP_NAME}.jar

WORKDIR /app

EXPOSE 9003

ENTRYPOINT java -jar ${APP_NAME}.jar