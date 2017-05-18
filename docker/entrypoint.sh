sudo service postgresql start
sudo psql -U postgres -c "ALTER USER postgres WITH PASSWORD 'sabha123'"
sudo psql -U postgres -c "CREATE DATABASE tournaments"
tail