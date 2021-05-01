FROM websphere-liberty:javaee8

COPY --chown=1001:0 mysql-connector-java-5.1.48.jar /liberty/wlp/usr/shared/resources/
COPY --chown=1001:0 server.xml /config/
COPY --chown=1001:0 target/payment-processor.war /config/dropins/

EXPOSE 9080/tcp
EXPOSE 9443/tcp

ENV LICENSE accept

ARG VERBOSE=true

RUN configure.sh
