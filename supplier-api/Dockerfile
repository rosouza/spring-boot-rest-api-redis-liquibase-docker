FROM openjdk:11
ADD ./target/supplier-api-1.0.0.jar /usr/src/supplier-api-1.0.0.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "supplier-api-1.0.0.jar"]
