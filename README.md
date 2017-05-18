# Tournament Related Backend Services for TournamentPark!

[![Build Status][1]][2]
[![Coverage Status][3]][4]

## DB Setup (Do-It-Once thing!) 
**This section is only need the first time**
This project needs a Postgres DB. So, spin up a postgres DB container
* Open terminal at the project folder
* `docker build -t postgres docker`
* `docker run -di -p 5432:5432 postgres`
* Can verify the status of the container with `docker ps` or can view the details in Kitematic.

## Test DB Connection
You can check your DB connection with 
* Container powershell with `psql -h localhost -U postgres` -> `\l`. Should contain `tournaments` db.
* Can you a DB Tool like Valentina Studio

## Run Boot
* `gradle bootrun`
* This step will run the Liquibase Schema Updates before starting the application.

## Database Schema
<img src="https://github.com/tour-park/tournament-services/blob/master/documents/schema.png" alt="Tournament Schema" width="60%" height="80%">

## Helpful Intellij Plugins & Tips
* Plugin for generating serialVersionUID for classes that are Serializable
** `File > Settings > Plugins > Browse repositories > GenerateSerialVersionUID`

[1]: https://travis-ci.org/tour-park/tournament-services.svg
[2]: http://www.travis-ci.org/tour-park/tournament-services

[3]: https://coveralls.io/repos/github/tour-park/tournament-services/badge.svg?branch=master
[4]: https://coveralls.io/github/tour-park/tournament-services?branch=master