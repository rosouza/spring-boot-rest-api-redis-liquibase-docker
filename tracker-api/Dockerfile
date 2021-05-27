FROM openjdk:11
ADD ./target/tracker-api-1.0.0.jar /usr/src/tracker-api-1.0.0.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "tracker-api-1.0.0.jar"]
