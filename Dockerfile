# Start with a base image containing Java runtime (Java 17)
FROM openjdk:17-slim as build

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Unpack the jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# Use a Docker multi-stage build to create a slimmed down image
FROM openjdk:17-slim

# Copy the dependencies into the new container
COPY --from=build target/dependency/BOOT-INF/lib /app/lib
COPY --from=build target/dependency/META-INF /app/META-INF
COPY --from=build target/dependency/BOOT-INF/classes /app

# Execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.classichotel.ClassicHotelApplication"]
