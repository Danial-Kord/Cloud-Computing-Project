# Cloud Computing Project

## Configuration
You should create a folder in a directory and put config files there (under the `appConfigs` directory in this repo).

## Project Build & Run

Run `./mvnw clean package` in the project folder; after a while, Maven prints out the project's `jar` file path on the terminal.

After maven build, the Jar file usually is in `target/finalProject-0.1-spring-boot.jar`, and you can run the application by the following command:

```bash 
java -jar target/finalProject-0.1-spring-boot.jar --spring.config.name=applicationConfig,secretConfig --spring.config.location=file:///home/ali/appConfigs/
```

Note that `applicationConfig.properties` and `secretConfig.properties` are config files name under the directory of `/home/ali/appConfigs/` that I had created before.
Note that there is no need to change user value inside `applicationConfig.properties` if you are making database using below commands

## Docker config
### application Dockerfile
For creating docker runtime image of project run `sudo docker build -t {your desired name of image} .`
Then run the image using `sudo docker run --mount type=bind,src={Host path to config folder},target=/usr/src/conf -p {Host port}:8081 -it {your desired name of image}`

### database build
Simply run `sudo docker run --detach --name={container name} --env="MYSQL_ROOT_PASSWORD={root password}" --publish {host port}:{DB port} --volume=/root/docker/{container name}/conf.d:/etc/mysql/conf.d --volume={DB local storage}:/var/lib/mysql mysql`
you can use `sudo docker inspect {container name}` to check ip and other values for connection purposes

## Usage

```bash 
curl --location --request POST 'http://localhost:8081/doit' --header 'Content-Type: application/json' --data '{"url":"https://01d.ir/test"}'
```

you can go to the desired link by opening `http://localhost:8081/{code}`



Enjoy!
