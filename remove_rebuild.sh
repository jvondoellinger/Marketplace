#!/bin/bash

echo "Parando containers e removendo volumes..."
sudo docker-compose down -v

echo "Reconstruindo e subindo os containers..."
sudo docker-compose up --build
