FROM openjdk:11.0-jre

COPY ./target/roche-0.0.1-SNAPSHOT.jar ./roche-0.0.1-SNAPSHOT.jar
EXPOSE 8080/tcp

ENTRYPOINT ["java","-jar","/roche-0.0.1-SNAPSHOT.jar"]