FROM eclipse-temurin:8u342-b07-jre-focal as builder
WORKDIR /opt/application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:8u342-b07-jre-focal

LABEL Name="java-test-app"
LABEL Description="component built on Spring Boot"
LABEL Vendor="com.example"

WORKDIR /opt/application

COPY --from=builder /opt/application/dependencies/ ./
COPY --from=builder /opt/application/spring-boot-loader/ ./
COPY --from=builder /opt/application/snapshot-dependencies/ ./
COPY --from=builder /opt/application/application/ ./

COPY config ./config
COPY bin    ./bin

RUN groupadd -r app -g 1000 && \
    useradd -u 1000 -r -g app -d /opt/application -s /sbin/nologin -c "app user" app

RUN mkdir -p ./logs && chown -R app ./logs

USER app

EXPOSE 8080

ENTRYPOINT ["/opt/application/bin/docker-entrypoint.sh"]