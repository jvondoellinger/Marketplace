#!/bin/bash

NETWORK=grafana_network
NAME=loki

sudo docker rm -f "$NAME" 2>/dev/null

echo "Iniciando o Grafana LOKI na network: $NETWORK"
sudo docker run -d \
  --name "$NAME" \
  --network "$NETWORK" \
  -p 3100:3100 \
  grafana/loki

