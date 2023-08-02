FROM maven:3.9.2 AS stage1
WORKDIR /winderton-tutor
COPY pom.xml /winderton-tutor
RUN mvn dependency:resolve
COPY src ./src/
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:20 as final
COPY --from=stage1 /winderton-tutor/target/winderton-tutor-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]