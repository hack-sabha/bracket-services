# Bracket Related Backend Services for BracketPark

## DB Setup (Do-It-Once thing!) 
**This section is only need the first time**
This project needs a Postgres DB. So, spin up a postgres DB container
* Open terminal at the project folder
* `docker build -t postgres docker`
* `docker run -di -p 32222:5432 postgres`
* Can verify the status of the container with `docker ps` or can view the details in Kitematic.

## Test DB Connection
You can check your DB connection with 
* Container powershell with `psql -h localhost -U postgres` -> `\l`. Should `brackets` db.
* Can you a DB Tool like Valentina Studio

## Run Boot
* `gradle bootrun`
* This step will run the Liquibase Schema Updates before starting the application.

## Database Schema
<img src="https://github.com/hack-sabha/bracket-services/blob/master/documents/schema.png" alt="Bracket Schema" width="60%" height="80%">

## Helpful Intellij Plugins & Tips
* Plugin for generating serialVersionUID for classes that are Serializable
** `File > Settings > Plugins > Browse repositories > GenerateSerialVersionUID`



