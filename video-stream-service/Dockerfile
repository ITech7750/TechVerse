FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17
EXPOSE 8001
RUN mkdir /authservice
COPY --from=build /home/gradle/src/build/libs/*.jar /authservice/authservice.jar
ENTRYPOINT ["java", "-jar", "/authservice/authservice.jar"]
