#!/usr/bin/env bash
rm -f *.jar
mvn clean install -f ../pom.xml -P prod -Dmaven.test.skip=true
cp ../target/mail-sender.jar app.jar
docker build . -t hub.docker.com/mail-sender:0.1
docker push hub.docker.com/mail-sender:0.1