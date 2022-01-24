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
Simply run 
```bash 
sudo docker run --detach --name={containerName} --env="MYSQL_ROOT_PASSWORD={rootPassword}" --publish {host port}:{DBport} --volume=/root/docker/{containerName}/conf.d:/etc/mysql/conf.d --volume={DBlocalStorage}:/var/lib/mysql mysql
```
you can use `sudo docker inspect {container name}` to check ip and other values for connection purposes



## Minikube
### Minikube installation
check out https://minikube.sigs.k8s.io/docs/start/ <br>
for x86-64 architucture follow these steps:
1. curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
2. sudo install minikube-linux-amd64 /usr/local/bin/minikube
3. minikube start
Note that if part 3 didnt go well, try to contact me :)

## Minikube project configs
try following steps in order and you will be fine!
first go to the `./kuber` path then:

### data base deploy steps
1. `minikube kubectl apply -f secret.yaml` or `sudo minikube kubectl -- apply -f secret.yaml`

2. `minikube kubectl apply -f config-map.yaml` or `sudo minikube kubectl -- apply -f config-map.yaml`

3. `minikube kubectl apply -f config-map2.yaml` or `sudo minikube kubectl -- apply -f config-map2.yaml`

4. `minikube kubectl apply -f mysql-storage.yaml` or `sudo minikube kubectl -- apply -f mysql-storage.yaml`

5. `minikube kubectl apply -f mysql-deployment.yaml` or `sudo minikube kubectl -- apply -f mysql-deployment.yaml` <br>
after this step check the STATUS of pods by `sudo minikube kubectl get pod -- -o wide` command, it should be RUNNING after a while.

6. `minikube kubectl apply -f mysql-service.yaml` or `sudo minikube kubectl -- apply -f mysql-service.yaml` <br>
now database setup is completed. Use `sudo minikube kubectl get deployment -- -o wide` and `sudo minikube kubectl get service -- -o wide` for checking status.

### urlshortener deploy steps
1. `minikube kubectl apply -f secret.yaml` or `sudo minikube kubectl -- apply -f secret.yaml`

2. `minikube kubectl apply -f config-map.yaml` or `sudo minikube kubectl -- apply -f config-map.yaml`

3. `minikube kubectl apply -f config-map2.yaml` or `sudo minikube kubectl -- apply -f config-map2.yaml`

4. `minikube kubectl apply -f urlshortener-deployment.yaml` or `sudo minikube kubectl -- apply -f urlshortener-deployment.yaml` <br>
after this step check the STATUS of pods by `sudo minikube kubectl get pod -- -o wide` command, it should be RUNNING after a while.

5. `minikube kubectl apply -f urlshortener-service.yaml` or `sudo minikube kubectl -- apply -f urlshortener-service.yaml` <br>
now database setup is completed. Use `sudo minikube kubectl get deployment -- -o wide` and `sudo minikube kubectl get service -- -o wide` for checking status.

If you reach here, It is all done. For testing your service try steps below: <br>
Before moving forward save try to copy the ip of the service in the `sudo minikube kubectl get service -- -o wide` command.

### Testing service pod
1. `sudo minikube kubectl run test -- --image=danialkm/myubuntu:latest sleep infinity`
2. `sudo minikube kubectl exec -- --stdin --tty test -- bash` <br>
in the opened bash check the system working by typing Usage command below! <br>
Note that instead of `localhost` in the POST url, you need to type service ip.

## Usage

```bash 
curl --location --request POST 'http://localhost:8081/doit' --header 'Content-Type: application/json' --data '{"url":"https://01d.ir/test"}'
```

you can go to the desired link by opening `http://localhost:8081/{code}`



Enjoy!
