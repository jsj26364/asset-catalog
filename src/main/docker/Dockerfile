FROM centos

LABEL artifactId="@artifactId@" version="@version@"

EXPOSE 8080

RUN yum install -y java

# This is where a Spring Boot application creates working directories for Tomcat by default. 
#The effect is to create a temporary file on your host under "/var/lib/docker" and 
#link it to the container under "/tmp". This step is optional for a simple app, but can be necessary for other Spring 
# Boot applications if they need to actually write in the filesystem.

VOLUME /tmp

#ADD @artifactId@-@version@.jar app.jar
ADD asset-catalog-0.0.1-SNAPSHOT.jar app.jar

#You can use a RUN command to "touch" the jar file so that it has a file modification time 
#(Docker creates all container files in an "unmodified" state by default). 
#This actually isn’t important for the simple app that we wrote, but any static content 
#(e.g. "index.html") would require the file to have a modification time.

RUN sh -c 'touch /app.jar'

# To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
# To add SSL handshake debug logging: "-Djavax.net.debug=ssl:handshake"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=Pacific/Auckland", "-jar", "/app.jar"]
