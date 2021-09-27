          FROM adoptopenjdk/openjdk11:latest
          EXPOSE 8080
          ADD target/dinning-hall-api.jar dinning-hall-api.jar
          ENTRYPOINT ["java", "-jar", "/dinning-hall-api.jar"]