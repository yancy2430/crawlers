FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd && echo "Asia/Shanghai" > /etc/timezone;
RUN mkdir -p /usr/local/crawler
ADD crawler-0.1.jar /usr/local/crawler
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
