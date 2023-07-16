FROM openjdk:17-oracle
ADD target/jenkins-example-0.0.1-SNAPSHOT.jar jenkins-example-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "jenkins-example-0.0.1-SNAPSHOT.jar"]