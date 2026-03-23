FROM eclipse-temurin:21
LABEL authors="ItaloUser"
WORKDIR /app
COPY target/copia-video-0.0.1-SNAPSHOT.jar app/copia-video.jar
ENTRYPOINT ["java","-jar", "copia-video.jar"]