# Final Health Project - Sample Database

This project is meant to be used for the Final Health Project which serves as the final evaluation for cycle associates.
The Dockerfile in this project creates a modified MongoDB
Database with sample data for the Final Health Project.

## Getting Started

These instructions will get you a copy of the project up and running on your local
machine for development and testing purposes.

	$ git clone git@git.catalystitservices.com:CatalystTraining/final-health-project-db.git

Make sure that you have [Docker](https://www.docker.com/products/overview) installed
on your local machine. You will need Docker in order to run the sample database image.

### Using the Docker Image

The following sections outline the use

#### Building The Docker Image

```
$ docker build -t healthdb .
```
#### Running The Docker Image

```
$ docker run -d -p 27017:27017 --name healthdb healthdb
```

#### Connecting to MongoDB

You can use MongoDB Compass, RoboMongo, or the Terminal to connect to the
running MongoDB instance. All collections are located in the 'healthdb' database.

```
In a terminal window:

$ docker exec -it healthdb mongo - will connect to the running container and start the mongo shell.
...
> show databases
	admin
	healthdb
	local
> type your commands here...
```

## Built With

* [Docker](https://www.docker.com/) - Containerization Engine
* [MongoDB](https://www.mongodb.com/) - Document Database Management System

## Authors

* **Paolo Hilario** - *Initial work* - [philario@catalystdevworks.com](mailto:philario@catalystdevworks.com)
* **Dan Reuther** - *Updated for health final* - [dreuther@catalyte.io.com](mailto:dreuther@catalyte.io.com)
