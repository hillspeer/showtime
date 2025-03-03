FROM azul/zulu-openjdk:21
LABEL authors="hills"

WORKDIR /app

COPY ./build/libs/showtime-1.4-SNAPSHOT.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]