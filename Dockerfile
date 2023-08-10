FROM openjdk:11

COPY target/student-manager-0.0.1-SNAPSHOT.jar student-manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT["java", "-jar", "/student-manager-0.0.1-SNAPSHOT.jar"]