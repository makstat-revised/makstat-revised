FROM maven:3.6.3-jdk-11

# create /makstat folder (bind-mount location)
RUN mkdir /makstat
WORKDIR /makstat

# by default, start spring boot when the container starts
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.arguments=--server.port=8080"]

# use port 5005 for remote debugging (set JAVA_OPTS in pom.xml)
EXPOSE 5005

# tomcat/spring boot will run on port 8080 inside the container
EXPOSE 8080

# spring boot live reload port
EXPOSE 35729
