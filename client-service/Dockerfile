FROM openjdk:11
ADD ./target/client-service-1.0.0.jar /usr/src/client-service-1.0.0.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "client-service-1.0.0.jar"]
