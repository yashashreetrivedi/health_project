#  Final Project
This project is meant to be used with the final project. The Dockerfile in this project creates a modified MongoDB
Database with sample data for the Pet Emporium.

# Getting Started
These instructions will get you a copy of the project up and running on your local
machine for development and testing purposes.

$ git clone git@git.catalystitservices.com:MDW_2018_/YASHASHREE/Yashashree_final_backend.git

for db clone below url
git@git.catalystitservices.com:MDW_2018_/final-health-project-db.git

Make sure that you have Docker installed
on your local machine. You will need Docker in order to run the sample database image.
#  For  Database Please Follow below Command
##  Using the Docker Image
The following sections outline the use

## Building The Docker Image 
$ docker build -t healthdb .

## Running The Docker Image
$ docker run -d -p 27017:27017 --name healthdb healthdb


## Connecting To MongoDB
$ docker exec -it pedb mongo - will connect to the running container and start the mongo shell.
   
# Using API 
######  git clone  git@git.catalystitservices.com:MDW_2018_/YASHASHREE/Yashashree_final_backend.git

This API makes use of the spring-security library. When submitting requests
through Postman, you must provide the authentication credentials in the "Users"
collection of the database. just like sign up.
1.Go to user collection "http://localhost:8080/users"  give all information like fname ,email ,password ,roles,title then send request 
2.Go to login collection "http://localhost:8080/auth/signin" It will return token user this token for Authantication.

# Reference, the URLs are:
Use this URl to test from Postman
######  for Patient doamin:
 GET-  http://localhost:8080/patient (only admin can access) <br/>
 GET- http://localhost:8080/patient/{id} (only admin can access) <br/>
 POST -http://localhost:8080/patient (only admin can access)<br/>
 GET - http://localhost:8080/patient/info (User and  admin both can access)<br/>
 PUT- http://localhost:8080/patient/{id} (only admin can access)<br/>
 DELETE - http://localhost:8080/patient/{Id}(only admin can access)<br/>
 
###### for User domain
GET-http://localhost:8080/users  (only admin can access)<br/>
GET-http://localhost:8080/users/{id}  (only admin can access)<br/>
POST -http://localhost:8080/users (only admin can access)<br/>
DELETE-http://localhost:8080/users/{Id}(only admin can access)<br/>
PUT-http://localhost:8080/users/{Id}(only admin can access)<br/>

###### for Encounter domain
Only admin can access Encounter detalis<br/>
GET-http://localhost:8080/encounters<br/>
GET-http://localhost:8080/encounters/{id}<br/>
POST-http://localhost:8080/encounters<br/>
DELETE-http://localhost:8080/encounters/{Id}<br/>
PUT--http://localhost:8080/encounters/{Id}<br/>
GET-http://localhost:8080/encounters/patient/{patientId}


# Built With
- Docker - Containerization Engine

- MongoDB - Document Database Management System

- Java SDK 8 - Powerful, Established Backend Language

- Testing - junit

# Fornt End <br/>
For this Project we used React-redux and for fatch data we use Saga
1. Login form -Just login with email and password before that you need to create a accout . For create go to backend create account with user doamin . 
2. If you are admin can access full curd  operation of patient and see encounter as well. 
3. If you are only see patient firstname, last name , age, gender.

	git@git.catalystitservices.com:MDW_2018_/YASHASHREE/Yashashree_final_frontend.git
	
## npm install

It will intall all depandencies in you system which are necessary to run app.you can go to http://localhost:3000 to view the application.


# Supported Browsers

By default, the generated project uses the latest version of React.

You can refer to the React documentation for more information about supported browsers.

# Made With

Visual Studio Code: IDE

React: Front-End Library

Create-React-App: Used to quickly get react dependencies

NPM: Used to managed dependencies

# Redux
install  - npm install react-redux


# Saga

install - npm install redux-saga

In this project we fetch API from backend to preform a operation get, post ,put ,delete


# Running  Project

npm start

# Test
npm test

# Author
- Yashashree Trivedi - ytrivedi@catalyte.io



