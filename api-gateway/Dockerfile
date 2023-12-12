FROM openjdk:17

ENV APP_NAME api-gateway

MAINTAINER ${APP_NAME}

COPY ./target/${APP_NAME}.jar  /app/${APP_NAME}.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT java -jar ${APP_NAME}.jar