FROM gradle:8.0.2-jdk17-alpine AS builder

WORKDIR /source
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle build

FROM amazoncorretto:17-alpine3.19

WORKDIR /usr/src/app
COPY --from=builder /source/build/libs/ziplink-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "ziplink-0.0.1-SNAPSHOT.jar"]