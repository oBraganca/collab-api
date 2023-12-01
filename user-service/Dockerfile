FROM openjdk:17
ADD target/*.jar user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]
EXPOSE 9002