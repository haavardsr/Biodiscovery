#!/bin/bash

# Kill running instance of tomcat
docker kill tomcat

# Compile and packages source code into .war file via maven volume in docker container
docker run --rm -it --name mavenbuild -v maven-repo:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean install

# Copy and rebuilds tomcat image with latest .war file
docker image build -t haavard/tomcat .

# Start tomcat container, making the webapp available.
docker container run --rm -it -d --name tomcat -v /hdd/FastqDIR:/usr/local/tomcat/FastqDIR --publish 8081:8080 haavard/tomcat

echo ""
echo "Link: http://localhost:8081/"
echo ""

read -n 1 -s -r -p "Press any key to continue..."
echo ""
