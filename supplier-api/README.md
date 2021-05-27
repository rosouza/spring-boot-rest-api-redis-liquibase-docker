# supplier

This application was generated using ROSOUZA 0.1.0.

This is a "microservice" application intended to be part of a microservice architecture, please refer to the [Doing microservices with ROSOUZA][] page of the documentation for more information.

## Development

To start your application in the dev profile, run:

    ./mvnw

## Building for production

### Packaging as jar

To build the final jar and optimize the supplier application for production, run:

    ./mvnw -Pprod clean verify

To ensure everything worked, run:
java -jar target/\*.jar

Refer to [Using ROSOUZA in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

    ./mvnw -Pprod,war clean verify

## Testing

To launch your application's tests, run:

    ./mvnw verify

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

For more information, refer to the [Code quality page][].
