FROM tomcat:9.0-jdk8-temurin

RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY springCommunity.war /usr/local/tomcat/webapps/ROOT.war

CMD ["sh", "-c", "JAVA_TOOL_OPTIONS=\"-Dserver.port=$PORT\" catalina.sh run"]