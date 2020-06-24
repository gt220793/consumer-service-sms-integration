# Mastercard Billpay

This project contains the micro-services, modules for Mastercard Billpay system. The services are developed using using SpringBoot.

## 1. Building Project
If building Docker Images on Windows, install [Docker for Windows](https://docs.docker.com/docker-for-windows/). Make sure you configured the following things.

* expose daemon on non tls

* container type should be linux

* Hyper-V feature disabled for windows

Install [java](https://oracle.com) and [Maven](https://maven.apache.org) if it is not done already

Clone the code from Git into a folder, lets call this ***project_root***

Run below maven commands from ***project_root*** directory for various tasks.

**clean project**. This will clean the **Target** directory (aka output folder).

```bash
mvnw clean
```
**clean & build project**. This will perform the **clean** task followed by **build** process.

```bash
mvnw clean package
```
The above command will generate the artifact file in the **target** directory.

**clean, build** project & **create** Docker Image. This will perform the **clean** task followed by **build** process. Then it creates **Docker** Image. Please make sure that the Docker Service is running locally before running this goal. \
Note: run this command in the module directory

```
mvnw clean package dockerfile:build
```

## 2. Run Microservice using JAR

If you need to perform deployment on local system, it can be done by running jar directly or using a Docker image. Use the below mentioned command to run the microservice directly using jar file.

```
java -jar <app-name>.jar &
```

Note the **&** at the end of the command, this is to run the process in background

By default, the server runs on port ***8090***, we can change that by passing the port number as below (make sure to kill the above process if it is already running)

```
java -Dserver.port=9023 -jar <app-name>.jar &
```

## 3. Run Microservice using Docker Image
Once the **Docker** image is available, we can run the Docker container by using the following command.

For **Windows**, run the following command

```bash
winpty docker run -p 8081:8080 -it <image-name>
```

For **Linux**, run the following command

```bash
docker run -p 8081:8080 -it <image-name>
```

## 4. Useful Docker Commands

List docker images

```bash
$ docker images
```

This will print output like the one shown below.
```
$ docker images
REPOSITORY           TAG          IMAGE ID            CREATED             SIZE
billpay-service      latest       fa256c6a5e56        52 minutes ago      643MB
```

Remove Docker image
```bash
docker rmi <image-name>
```

Run Docker image with name 'billpay-service'
```bash
$ winpty docker run -p 8080:8080 -it billpay-service
```

Tagging Docker image
```bash
docker tag billpay-service gcr.io/mastercard/billpay-service:0.1
```
