docker run --rm --env "TZ=Europe/Oslo" --name mariadb -p 3308:3306/tcp -v "%cd%\database":/var/lib/biodiscovery -e MYSQL_ROOT_PASSWORD=12345 -d mariadb:10.8.3