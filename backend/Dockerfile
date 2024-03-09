FROM amazoncorretto:17-alpine3.19

WORKDIR /usr/src/app

COPY build/libs/ziplink-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "ziplink-0.0.1-SNAPSHOT.jar"]