FROM java:8

ADD pingbus-service.jar /pingbus/pingbus-service.jar
ADD app.properties /pingbus/app.properties

WORKDIR /pingbus

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "pingbus-service.jar"]