#!/bin/bash
result=$(kubectl get all -l app=payment-processor)

if [ -z "$result" ]; then
  echo "Empty"
else
  kubectl rollout restart deploy payment-processor
fi
