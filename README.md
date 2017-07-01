# Tournament Related Backend Services for TournamentPark!

[![Build Status][1]][2]
[![Coverage Status][3]][4]

## Project Run

### Boot Run
* `gradle bootrun`
* This step will run the Liquibase Schema Updates before starting the application.

### Containerized Run
* `gradle buildDocker`
* `docker run -t -p 8080:8080 tournament-services`

## Deployment

### DockerHub Publish 
Publish the latest image to DockerHub - [TournamentServices](https://hub.docker.com/r/swarooprajg/tournament-services)
* `gradle buildDocker`
* `docker login`
* `docker tag tournament-services:latest swarooprajg/tournament-services:latest`
* `docker push swarooprajg/tournament-services`

### DockerHub Pull & Deploy
Pull the image to the machine you to deploy to
* `docker pull swarooprajg/tournament-services`
* `docker run -di -p 8200:8080 swarooprajg/tournament-services`

## DB Setup (Do-It-Once thing! Choose of the following steps) 

**This section is only need the first time**
This project can run against a Postgres DB if needed. So, spin up a postgres DB container

### Option 1: DB from DockerHub
* `docker pull swarooprajg/tourpark-postgres`
* `docker run -di -p 5432:5432 swarooprajg/tourpark-postgres`

### Option 2: DB from Project
* Open terminal at the project folder
* `docker build -t postgres docker/postgres`
* `docker run -di -p 5432:5432 postgres`
* Can verify the status of the container with `docker ps` or can view the details in Kitematic.

### Test DB Connection
You can check your DB connection with 
* Container powershell with `psql -h localhost -U postgres` -> `\l`. Should contain `tournaments` db.
* Can you a DB Tool like Valentina Studio

## Database Schema
<img src="https://github.com/tour-park/tournament-services/blob/master/documents/schema.png" alt="Tournament Schema" width="60%" height="80%">

## Helpful Intellij Plugins & Tips
* Plugin for generating serialVersionUID for classes that are Serializable
** `File > Settings > Plugins > Browse repositories > GenerateSerialVersionUID`

[1]: https://travis-ci.org/tour-park/tournament-services.svg
[2]: http://www.travis-ci.org/tour-park/tournament-services

[3]: https://coveralls.io/repos/github/tour-park/tournament-services/badge.svg?branch=master
[4]: https://coveralls.io/github/tour-park/tournament-services?branch=master