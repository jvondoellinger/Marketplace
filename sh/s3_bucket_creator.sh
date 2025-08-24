BUCKET_NAME="marketplace-images"

ENDPOINT_URL="http://localhost:4566"
RENDER_ENDPOINT='https://localstack-8xp5.onrender.com'

echo "Criando bucket '$BUCKET_NAME' no LocalStack..."

aws --endpoint-url="$RENDER_ENDPOINT" s3api create-bucket \
  --bucket "$BUCKET_NAME" \
  --region sa-east-1 \
  --create-bucket-configuration LocationConstraint=sa-east-1

echo "Bucket '$BUCKET_NAME' criado com sucesso!"
