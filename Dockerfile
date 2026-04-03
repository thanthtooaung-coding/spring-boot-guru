# OCI image carrying the library JAR (for GHCR). Not a runnable Spring Boot app.
FROM alpine:3.20

LABEL org.opencontainers.image.title="spring-boot-guru"
LABEL org.opencontainers.image.description="Spring Boot 3 shared library JAR"
LABEL org.opencontainers.image.source="https://github.com/thanthtooaung-coding/spring-boot-guru"

WORKDIR /opt/spring-boot-guru

# CI copies the resolved main artifact to this path before build (see workflow).
COPY target/docker-app.jar spring-boot-guru.jar
