FROM openjdk:11

RUN mkdir -p /app

COPY target/employee-management-*.jar /app/employee-management.jar

ENTRYPOINT ["java", "-jar", "/app/employee-management.jar"]