FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre-focal
ARG JAR_FILE=target/*.jar
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
