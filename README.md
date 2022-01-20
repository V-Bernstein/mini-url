docker pull mysql
docker run --name url-storage -e MYSQL_ROOT_PASSWORD=pword -p 3306:3306 -d mysql:latest
docker exec -it url-storage /bin/sh
    mysql -uroot -p
    # Use the password we set up when running the docker command
    CREATE DATABASE mini_url;

# To run via CLI
./gradlew bootRun