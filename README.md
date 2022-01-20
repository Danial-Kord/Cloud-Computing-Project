# Cloud Computing Project


## Project
after setting your DB and configs in the ` src/main/resources/application.properties`
you can run the main method in the `CloudComputingFinalProjectApplication` class.

usage:

```bash 
curl --location --request POST 'http://localhost:8081/doit' --header 'Content-Type: application/json' --data '{"url":"https://01d.ir/test"}'
```

you can go to the desired link by opening `http://localhost:8081/{code}`

## project Build

run `./mvnw clean package` in the project folder. (after building the project, you can see the output directory on the terminal)

now, the Jar file is in `target/finalProject-0.1-spring-boot.jar`, and you can run the application by the following command:

`java -jar target/finalProject-0.1-spring-boot.jar`

Enjoy!
