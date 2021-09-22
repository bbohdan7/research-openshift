#!/bin/bash
kubectl delete $(kubectl get all -n $1 | grep replicaset.apps | grep "0         0         0" | cut -d' ' -f 1) -n $1

