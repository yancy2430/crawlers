#!/bin/sh
echo "********************************************************"
echo "Starting the crawler Server"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/crawler/crawler-0.1.jar
