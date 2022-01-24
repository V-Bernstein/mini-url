# Steps to run project locally
## DB setup
1. docker pull mysql
2. docker run --name url-storage -e MYSQL_ROOT_PASSWORD=pword -p 3306:3306 -d mysql:latest
3. docker exec -it url-storage /bin/sh
    1. mysql -uroot -p
    ### Use the password we set up when running the docker command
    2. CREATE DATABASE mini_url;
### Run application via CLI (Flyway takes care of DB table structure)
4. ./gradlew bootRun