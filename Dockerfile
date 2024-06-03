FROM maven:3.9.5-sapmachine-21 AS build
WORKDIR /opt/rozproszenie

COPY ./ /opt/rozproszenie
RUN mvn clean install -DskipTests
FROM openjdk:21-rc-oracle

COPY --from=build /opt/rozproszenie/target/*.jar rozproszenie.jar

ENV PORT 80
EXPOSE $PORT

ENTRYPOINT ["java","-jar","-Xmx1024M","-Dserver.port=${PORT}","rozproszenie.jar"]