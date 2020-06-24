FROM openjdk:11
VOLUME /tmp
ARG ARTIFACT_NAME
ADD target/${ARTIFACT_NAME} app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]