#!/bin/sh
mkdir /logs
touch /logs/postgresql.log
chmod -R 777 /logs
tail -f /logs/* &
/docker-entrypoint.sh "$@"
