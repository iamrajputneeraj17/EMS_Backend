FROM openjdk:17
EXPOSE 8080
ADD target/ems-backend-with-react.jar ems-backend-with-react.jar
ENTRYPOINT ["java","-jar","/ems-backend-with-react.jar"]