FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/hospitalmanagement-1.0.jar app.jar
# CHANGE above: lowercase e.g. electricitybilling-1.0.jar

CMD ["sh", "-c", "java -jar app.jar; tail -f /dev/null"]
