sudo systemctl start redis mongod

./create_network.sh
./start-localstack.sh
./start_loki.sh
./start_grafana.sh
./s3_bucket_creator.sh


curl http://localhost:3100/ready
