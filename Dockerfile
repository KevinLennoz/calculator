FROM openjdk:11
VOLUME [ "/tmp" ]
ADD demo-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "sh","-c","java -jar /app.jar" ]