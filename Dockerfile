FROM openjdk:22-jdk-slim
COPY target/*.jar server.jar
ARG profile
ENV SPRING_PROFILES_ACTIVE=$profile
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/server.jar"]