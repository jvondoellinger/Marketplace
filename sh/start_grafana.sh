#!/bin/bash

NETWORK=grafana_network

sudo docker rm -f grafana

echo "Iniciando o grafana na network: $NETWORK"
sudo docker run -d \
  --name grafana \
  --network "$NETWORK" \
  -p 3000:3000 \
  grafana/grafana
