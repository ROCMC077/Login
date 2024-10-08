FROM openjdk:8
EXPOSE 8080
ADD target/spring-boot-docker.jar test1.jar
ENTRYPOINT ["java","-jar","/test1.jar"]