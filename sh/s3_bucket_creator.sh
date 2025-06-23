BUCKET_NAME="test"

ENDPOINT_URL="http://localhost:4566"

echo "Criando bucket '$BUCKET_NAME' no LocalStack..."

aws --endpoint-url="$ENDPOINT_URL" s3api create-bucket \
  --bucket "$BUCKET_NAME" \
  --region us-east-1 \
  --create-bucket-configuration LocationConstraint=us-east-1

echo "Bucket '$BUCKET_NAME' criado com sucesso!"
