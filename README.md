# spring-boot-rest-api-redis-liquibase-docker
Code Challenge with SpringBoot, REST Api, Redis, Liquibase, Docker

# Local Enrivonment Requirements:
Maven 3.x

Docker

# In the root folder run:

mvn install

docker compose build

docker compose up

# Client will be running as a scheduled service under client-service module running with a given cron expression:

com.rosouza.supplier.service.ClientService
