# Java Example application
Java 8 example application.

* Spring boot v2.3.8
* Java 8 (OpenJDK)
* Dependencies:
    * spring-boot-starter-actuator
    * spring-boot-starter-web
    * spring-boot-starter-test
    * micrometer-registry-prometheus
* [Graceful shutdown](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/reference/htmlsingle/#boot-features-graceful-shutdown) 
* [Liveness&Readiness probes](https://spring.io/blog/2020/03/25/liveness-and-readiness-probes-with-spring-boot) 
* Actuator enabled endpoints:
    * health
    * info
    * env
    * loggers 
    * metrics
    * prometheus
* Standardized, layered `Dockerfile`
* standardized configuration management:
    * configurations under `src/main/resources/application.yaml`
    * You can choose a profile using the environment variable `ACTIVE_PROFILES`.
* The container is run with the user `app` (UID 1000, GID 1000)  to avoid the root user


## Docker Image Build
The `Dockerfile` specify how the image is created. This file create a layered image, [here](https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1) for more info. 
The base image is  `eclipse-temurin:8u342-b07-jre-focal`.

The process is run from `bin/docker-entrypoint.sh`.

Docker build command `docker build -t <tag> .`

## Actuator
Spring Boot Actuator enabled on `src/main/resources/application.properties`:

`management.endpoints.web.exposure.include = health, info, env, loggers, metrics, prometheus, configprops`

## Connect to a Database
You can connect a mysql database activating the spring profile `db`.
The environment variabile activate this profile is `ACTIVE_PROFILES=db`.
In the default behaiviour no database is conected.
You can also set other environment variabiles:

DB_URL default value `localhost:3306`

DB_USER default value `user`

DB_PASSWORD default value `password`

## Rest Interface
The application expose the default context path `/test-app/v1`.

The class TestController expose some test endpoints. 

For example, you can get the instance id calling GET localhost:8080/test-app/v1/app/id

