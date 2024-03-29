# Java Base Biodiscovery Web Application

This project belongs to BiodiscoveryAS
Homepage Biodisovery.no
This application uses Docker to manage maven, tomcat and mariadb to build and host a web application using Java.

<br>

## How to use
### Prerequisites:
To deploy the website, you need to have [Docker](https://www.docker.com/) installed and running on your system.    

### Via commandline with docker (Recommended):
Compile and pacakge source code with maven in docker.

> Note: On Unix and Unix-like systems (Mac and Linux) you might need to run the commands with `sudo` to make them work.

##### 1. Copy compiled source code into tomcat docker container:    

|Bash (Mac and Linux)|Powershell (Windows)|
|--------------------|--------------------|
|`docker run --rm -it --name mavenbuild -v maven-repo:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean install` | `docker run --rm -it --name mavenbuild -v maven-repo:/root/.m2 -v "%cd%":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean install`|


##### 2. Build then start the docker container with the web application:    
`docker image build -t biodiscovery/tomcat .`    
`docker container run --rm -it -d --name tomcat --publish 8081:8080 trym/tomcat`

##### 3. Start a mariadb container using the localdirectory "database" to store the data:    

|Bash (Mac and Linux)|Powershell (Windows)|
|--------------------|--------------------|
|`docker run --rm --name mariadb -p 3308:3306/tcp -v "$(pwd)/database":/var/lib/mysql -e MYSQL_ROOT_PASSWORD=12345 -d mariadb:10.5.11`|`docker run --rm --name mariadb -p 3308:3306/tcp -v "%cd%\database":/var/lib/mysql -e MYSQL_ROOT_PASSWORD=12345 -d mariadb:10.5.11`|

##### 4. Enter the database and create the database and table for this skeleton:    
`docker exec -it mariadb mysql -p`    
When prompted enter the password (`12345`), then type or copy in the SQL from [this file](databaseskript/Biodiscovery.sql) (line by line).

##### 5. Test out the swebpage at http://localhost:8081/

<br>

### Via scripts:
The following takes the above steps and deduce them into scripts. (all the above commands are present in the below scripts).
The scripts allow us to build and deploy our application faster, which can be beneficial when the core concepts of using docker are understood.
|Bash (Mac and Linux)|Powershell (Windows)|
|--------------------|--------------------|
|Run `build.sh` to compile source code and build tomcat docker image.|Run `build.cmd` to compile source code and build tomcat docker image.|
|Run `startDb.sh` to start database|Run `startDb.cmd` to start database|

When those have been run you should be able to access the website at http://localhost:8081/ or http://<ip_adress>:8081/ if you have deployed it at a virtual server.


> Note: On Unix and Unix-like systems (Mac and Linux) you might need to run the scripts with `sudo` to make them work.

<br>

#### PS
Remember to recompile your code with maven after code changes.
