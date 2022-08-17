FROM openjdk:12-alpine

# Install system requirements
RUN apk update && apk add jq curl bash -y

EXPOSE 5900

ENV JAVA_OPTS=""
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /brik/app.jar

ADD target/lib /brik/lib
ARG JAR_FILE
ADD target/${JAR_FILE} /brik/app.jar