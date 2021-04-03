#!/bin/sh +x
apt-get update && apt-get install inotify-tools -y
place="$1"
shift
rest="$@"
cd "$place"
while true; do
  echo '' &&
  echo '--------------------------------------------------------------------' &&
  echo '' &&
  sleep 1 &&
  inotifywait -m --exclude "target" "$place" -r -e modify|mvn ${rest:--Pdev test -o}
done
cd -
