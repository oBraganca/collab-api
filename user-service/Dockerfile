FROM openjdk:17

ENV APP_NAME user-service

MAINTAINER ${APP_NAME}

COPY ./target/${APP_NAME}.jar  /app/${APP_NAME}.jar

WORKDIR /app

EXPOSE 9002

ENTRYPOINT java -jar ${APP_NAME}.jar