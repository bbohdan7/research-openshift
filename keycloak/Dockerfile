FROM quay.io/keycloak/keycloak:13.0.1

ADD standalone.xml /opt/jboss/keycloak/standalone/configuration/standalone.xml
ADD mysql /opt/jboss/keycloak/modules/system/layers/base/com/mysql

EXPOSE 8080
EXPOSE 9990

ENTRYPOINT ["/opt/jboss/keycloak/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
