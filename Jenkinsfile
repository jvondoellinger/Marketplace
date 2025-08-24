pipeline {
    agent any
    
    environment {
        EMAIL_CREDENTIALS= credentials('email-account-credentials')
        DATABASE_CREDENTIALS = credentials('database-credentials')

        APP_NAME = "zjg_marketplace"
        DOCKER_IMAGE = "jvondoellinger/zjg_marketplace"

        SPRING_PROFILES_ACTIVE='prod'
        PORT = credentials('server-port')

        MERCADO_PAGO_SECRET_KEY= credentials('mercado-pago-secret-key')
        
        EMAIL= EMAIL_CREDENTIALS_USR
        EMAIL_PASSWORD= EMAIL_CREDENTIALS_PSW
        SMTP_HOST= credentials('smtp-email-host')
        SMTP_PORT=credentials('smtp-email-port')
        
        DATABASE_URI= credentials('database-connection-uri')
        
        REDIS_URI= credentials('redis-connection-uri')
        
        AWS_ENDPOINT= credentials('aws-endpoint')
        AWS_REGION= credentials('aws-region')
        AWS_ACCESS_KEY_ID= credentials('aws-access-key')
        AWS_SECRET_KEY= credentials('aws-secret-key')
        AWS_S3_IMAGE_BUCKET= credentials('aws-s3-image-bucket')
        
        SECRET_API_KEY= credentials('api-key')
    }
    
    stages {
        stage("Checkout") {
            steps {
                echo "Fazendo checkout no repositorio..."
                git branch: 'beta-v0.3',
                    url: 'https://github.com/jvondoellinger/Marketplace.git'
            }
        }
        
        stage("Build") {
            steps {
                echo "Fazendo build da aplicação..."
                sh './mvnw clean package -DskipTests'
            }
        }
        
        stage('Prepare envs') {
            steps {
                script {
                    env.EMAIL = env.EMAIL_CREDENTIALS_USR
                    env.EMAIL_PASSWORD = env.EMAIL_CREDENTIALS_PSW
                }
            }
        }
        
        stage("Debug Workspace") {
            steps {
                echo "Diretório atual: ${pwd()}"
                sh 'git branch -a'
                sh 'git log -1'
                sh 'ls -R'
                sh 'ls -R src/test'
            }
        }
        
        stage("Test") {
            steps {
                echo "Realizando testes..."
                sh './mvnw test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage("Docker Build & Push") {
            when {
                branch 'main'
            }
            steps {
                echo "Construindo imagem docker... [ainda não]"
            }
        }
    }

    post {
        success {
            echo "Pipeline executada com sucesso ✅"
        }
        failure {
            echo "Pipeline falhou ❌"
        }
    }
}
