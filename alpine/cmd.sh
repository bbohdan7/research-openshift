#!/bin/sh

COUNT=60

echo "Initial counter is ${COUNT}"

while [ $COUNT -gt 0 ]
  do
    clear;echo "The Counter's value is ${COUNT}"
    let COUNT=COUNT-1
    sleep 1
  done
