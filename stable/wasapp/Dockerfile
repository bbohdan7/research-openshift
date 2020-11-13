FROM websphere-liberty:javaee8

RUN server create wasapp --template=javaee8

COPY server.xml /opt/ibm/wlp/usr/servers/wasapp
COPY mysql-connector-java-5.1.48.jar /opt/ibm/wlp/usr/servers/wasapp

ENTRYPOINT ["server", "run", "wasapp"]
