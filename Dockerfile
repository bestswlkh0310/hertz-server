FROM openjdk:17-oracle

COPY "./build/libs/hertz-0.0.1-SNAPSHOT.jar" "./hertz.jar"

ENTRYPOINT ["java", "-jar", "hertz.jar"]