FROM openjdk:11-oracle
WORKDIR /app
ADD target/*.jar /app/jenkins-example.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "jenkins-example.jar"]