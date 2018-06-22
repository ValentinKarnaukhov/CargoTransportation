From tomcat:8-jre8

MAINTAINER "valentin <admin@admin.com">

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]

ADD web/target/web.war /usr/local/tomcat/webapps/ROOT.war
