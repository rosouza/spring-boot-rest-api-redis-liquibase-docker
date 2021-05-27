# spring-boot-rest-api-redis-liquibase-docker
Code Challenge with SpringBoot, REST Api, Redis, Liquibase, Docker

In the root folder run:

mvn install

docker-compose build --no-cache tracker-api
docker-compose build --no-cache client-service
docker-compose build --no-cache supplier-api

docker compose up
