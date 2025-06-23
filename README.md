<<<<<<< HEAD
# 🛒 zjg_marketplace

Projeto de marketplace desenvolvido com foco em escalabilidade e integração com serviços em nuvem.

## 📦 Tecnologias Utilizadas

- **MongoDB Atlas**
- **Docker**
- **AWS S3**
- **Spring Boot**

---

## 🚀 Ambiente de Produção

A aplicação está hospedada via **Render** e está disponível publicamente.

🔗 **Swagger UI (Documentação dos Endpoints):**  
[https://marketplace-beta-e0fd.onrender.com/webjars/swagger-ui/index.html](https://marketplace-beta-e0fd.onrender.com/webjars/swagger-ui/index.html)

> ⚠️ **Atenção:** A aplicação pode ser temporariamente suspensa em períodos de inatividade devido às limitações do plano gratuito da Render.

---

## 🧪 Como Rodar Localmente com Docker

Antes de iniciar, **certifique-se de que há um bucket S3 criado com o nome `test`**, pois ele será utilizado pela aplicação.  
**Importante:** O arquivo `dev.yaml` será ajustado futuramente para dispensar o uso de variáveis de ambiente, facilitando ainda mais os testes locais.

### Comando Docker:

```bash
sudo docker run -d \
  -e AWS_ACCESS_KEY_ID=<SUA_CHAVE> \
  -e AWS_SECRET_KEY=<SUA_CHAVE_SECRETA> \
  -e AWS_REGION=<SUA_REGIÃO> \
  -e AWS_ENDPOINT=<SEU_ENDPOINT> \
  -e SERVER_PORT=<PORT> \
  -e SPRING_DATA_MONGODB_URI=<SUA_URI> \
  -e SPRING_PROFILES_ACTIVE=dev \
  -p <PORT>:<PORT> \
  --name zjg_marketplace_dev \
  jvondoellinger/zjg_marketplace:beta-0.1
=======
# Marketplace
>>>>>>> 0f51633 (update - v0.2)
