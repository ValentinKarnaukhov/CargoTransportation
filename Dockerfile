FROM tomcat:8-jre8

MAINTAINER "valentin <admin@admin.com">

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]

ADD logging.properties /usr/local/tomcat/conf/logging.properties
ADD setenv.sh  /usr/local/tomcat/bin/
ADD web/target/web.war /usr/local/tomcat/webapps/ROOT.war
