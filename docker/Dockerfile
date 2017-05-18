FROM ubuntu:16.04
MAINTAINER swarooprajg@gmail.com

RUN apt-get update -y
RUN apt-get install -y sudo
RUN apt-get install -y wget
RUN apt-get install -y vim

RUN sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ trusty-pgdg main" > /etc/apt/sources.list.d/pgdg.list'
RUN wget -q https://www.postgresql.org/media/keys/ACCC4CF8.asc -O - | sudo apt-key add -

RUN apt-get update -y
RUN apt-get install postgresql-9.6 postgresql-contrib-9.6 -y

RUN mv /etc/postgresql/9.6/main/pg_hba.conf /etc/postgresql/9.6/main/pg_hba.conf.backup
COPY pg_hba.conf /etc/postgresql/9.6/main/pg_hba.conf
RUN echo "listen_addresses = '*'" >> /etc/postgresql/9.6/main/postgresql.conf

EXPOSE 5432/tcp

COPY entrypoint.sh /
RUN chmod 755 /entrypoint.sh
ENTRYPOINT sudo /entrypoint.sh

ENV POSTGRES_DB tournaments