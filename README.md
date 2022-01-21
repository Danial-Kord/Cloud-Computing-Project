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

## Usage

```bash 
curl --location --request POST 'http://localhost:8081/doit' --header 'Content-Type: application/json' --data '{"url":"https://01d.ir/test"}'
```

you can go to the desired link by opening `http://localhost:8081/{code}`



Enjoy!
