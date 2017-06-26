echo deb http://apt.postgresql.org/pub/repos/apt/ xenial-pgdg main > /etc/apt/sources.list.d/pgdg.list
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc |   sudo apt-key add -
apt-get update
apt-get install -y vim postgresql
pg_createcluster 9.6 replica
pg_ctlcluster 9.6 replica start
