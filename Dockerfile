FROM java:8

RUN mkdir -p /app
WORKDIR /app

COPY . /app

VOLUME /tmp
EXPOSE 8080

ADD /build/libs/english-0.0.1-SNAPSHOT.jar english-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","english-0.0.1-SNAPSHOT.jar"]