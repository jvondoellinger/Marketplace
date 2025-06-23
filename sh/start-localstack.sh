#!/bin/bash

set -e

# Configuração
LOCALSTACK_IMAGE="localstack/localstack"
LOCALSTACK_CONTAINER_NAME="localstack-sh"
LOCALSTACK_PORT=4566
AWS_REGION="sa-east-1"
QUEUE_NAME="product-creation-test"
TABLE_NAME="marketplace-test"
ENDPOINT_URL="http://localhost:$LOCALSTACK_PORT"

# Subir o LocalStack com Docker (em background)
echo "🔄 Iniciando LocalStack..."
docker rm -f $LOCALSTACK_CONTAINER_NAME
docker run -d --rm --name $LOCALSTACK_CONTAINER_NAME -p $LOCALSTACK_PORT:4566 $LOCALSTACK_IMAGE > /dev/null

echo "⏳ Aguardando LocalStack responder na porta 4566..."

for i in {1..30}; do
  status=$(curl -s "$ENDPOINT_URL/_localstack/health" | jq -r '.services.sqs')
  if [[ "$status" == "available" ]]; then
    echo "✅ LocalStack SQS disponível."
    break
  fi
  echo "⏳ Esperando SQS iniciar... ($i)"
  sleep 1
done

export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_REGION=$AWS_REGION

echo "📬 Criando fila SQS: $QUEUE_NAME"
aws --endpoint-url=$ENDPOINT_URL sqs create-queue --queue-name $QUEUE_NAME || echo "⚠️ Fila já existe."

QUEUE_NAME="product-update-test"


echo "📬 Criando fila SQS: $QUEUE_NAME"
aws --endpoint-url=$ENDPOINT_URL sqs create-queue --queue-name $QUEUE_NAME || echo "⚠️ Fila já existe."

QUEUE_NAME="product-delete-test"


echo "📬 Criando fila SQS: $QUEUE_NAME"
aws --endpoint-url=$ENDPOINT_URL sqs create-queue --queue-name $QUEUE_NAME || echo "⚠️ Fila já existe."


echo "🗃️ Criando tabela DynamoDB: $TABLE_NAME"

CREATE_OUTPUT=$(aws --endpoint-url=$ENDPOINT_URL dynamodb create-table \
  --table-name "$TABLE_NAME" \
  --attribute-definitions AttributeName=id,AttributeType=S \
  --key-schema AttributeName=id,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 2>&1)


if echo "$CREATE_OUTPUT" | grep -q "Table already exists"; then
  echo "⚠️ Tabela $TABLE_NAME já existe."
else
  echo "✅ Tabela criada com sucesso!"
  echo "$CREATE_OUTPUT" | jq .
fi


echo "✅ LocalStack configurado com sucesso!"
