FROM openjdk:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/clj4cs-practice-0.0.1-SNAPSHOT-standalone.jar /clj4cs-practice/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/clj4cs-practice/app.jar"]
