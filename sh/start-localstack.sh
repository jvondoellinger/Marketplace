#!/bin/bash

set -e

# DOCKER - CONFIGURAÇÃO
LOCALSTACK_IMAGE="localstack/localstack"
LOCALSTACK_CONTAINER_NAME="localstack-sh"

# DEFAULT - CONFIGURAÇÃO
LOCALSTACK_PORT=4566
AWS_REGION="sa-east-1"
ENDPOINT_URL="http://localhost:$LOCALSTACK_PORT"

# SERVIÇO - INICIANDO LOCALSTACK EM BACKGROUND
echo "🔄 Iniciando LocalStack..."
sudo docker rm -f $LOCALSTACK_CONTAINER_NAME
sudo docker run -d --rm --name $LOCALSTACK_CONTAINER_NAME -p $LOCALSTACK_PORT:4566 $LOCALSTACK_IMAGE > /dev/null

echo "✅ LocalStack iniciado com sucesso na porta $LOCALSTACK_PORT !"
