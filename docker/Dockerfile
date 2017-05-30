FROM postgres:alpine
MAINTAINER swarooprajg@gmail.com

EXPOSE 5432/tcp

COPY entrypoint.sh /
RUN chmod 755 /entrypoint.sh
ENTRYPOINT sudo /entrypoint.sh

ENV POSTGRES_DB tournaments