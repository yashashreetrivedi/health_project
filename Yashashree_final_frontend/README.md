# Catalyte Health Api and Frontend

This is the Catalyte Health website and the API. The instructions on how to download and use will be found below.


# Getting Started

These instructions will get you a copy of the project up and running on your local
machine for development and testing purposes.


## First


Download the Catalyte Health website

Open the Terminal on your computer and copy the command below:

git clone `git@git.catalystitservices.com:MDW_2018_/YASHASHREE/Yashashree_final_frontend.git`

# Setting up the Database

Download the database

git clone`git@git.catalystitservices.com:MDW_2018_/final-health-project-db.git`

Make sure that you have Docker installed
on your local machine. You will need Docker in order to run the sample database image.


# Using the Docker Image

The following sections outline the use


# Building The Docker Image

First make sure you are in the final-health-project directory

Then from the command line or git bash run the following commands:

docker build -t healthdb .

Running The Docker Image

docker run -d -p 27017:27017 --name healthdb healthdb        

# Connecting to MongoDB

You can use MongoDB Compass, RoboMongo, or the Terminal to connect to the
running MongoDB instance. All collections are located in the healthdb database.
We would recommend using Kitematic to find the port on localhost that your
MongoDB is running on, as it may differ from one device to another.


## In a terminal window:

$ docker exec -it healthdb mongo - will connect to the running container and start the mongo shell.

# Starting Application 
For use a application you need to do some change in data base just make in the database you need to use encryped password .
put this password with this email id <br/><br/>
Email: pwilliams@superhealth.com <br/>

password:  $2a$10$m/pAPCxV6QuezR9cMwwGAukeDeu2EgqXW.tKrf9/Bldj6u0n/bVFG

# Start Kitematic
Open Ktematic and find healthdb image you create with the help of above mention command.Select that image and  click star button.now kitematic is running.

# For MongoDB Compass:
Open MongoDB Compass make sure port number should be 27017 and click connect button ,now you connect with Mongocompass find healthdb database.

## Third

Clone down the Final_Project_Backend.

 git clone `git@git.catalystitservices.com:MDW_2018_/YASHASHREE/Yashashree_final_backend.git`
 
 Type command - git checkout rework

After you have completed that Open the Final_Project_Backend inside your Java IDE. Navigate
 to the HealthAPIRunner file and run the application as a java Application to start the server.

## Fourth

Navigate to the Yashashree_final_frontend Once inside type the following command

git checkout final

then

npm install

then

npm start
This should open a tab inside of your chosen web browser.
If not, just type into the Navigation of your browser the following link

http://localhost:3000


# Completed

This should have the development enviroment up and running.

#Nevigation information
1. First is login page enter email and password and click login button it will take you to patient page .
2. If you are User so you can only see patient basic info you hane no write to access other functionality
3. If  you are Admin so you can see patient info and can do Delete,Add,Update patient.
4. If you want to logout there is logout button on right cornor of patient page click that button it will take you to again login page.

# Testing

This guide is assuming you are using the Java IDE Eclipse.


##  First

In order to test the functional classes inside of the API, you have to first open the Final_Project_Backend inside of Eclipse by importing it as an existing maven project.


## Second

If you open the file structure of Final_Project_Backend, follow it down until you see src/test/java.

If you right click on the src/test/folder you will see two options close to the middle of the popup.


# Run as Junit test

If you select run as Junit test, this will run all the tests in the application and show you what passes and what fails.


Run coverage as Junit test

This will run all the tests as well showing you the percentage of the application that is covered. 


End notes

## Authored by: Yashashree Trivedi 
ytrivedi@catalyte.io