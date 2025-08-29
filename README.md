
# 🛒 zjg_marketplace

Projeto de marketplace desenvolvido com foco em escalabilidade e integração com serviços em nuvem.

## 📦 Tecnologias Utilizadas

- **MongoDB Atlas**
- **Docker**
- **AWS S3**
- **Grafana Loki**
- **Redis**
- **JWT JWE**
- **Spring Boot**
- **WebFlux**

---

## 🚀 Ambiente de Produção

A aplicação está hospedada via **Render** e está disponível publicamente.

🔗 **Swagger UI (Documentação dos Endpoints):**  
[https://zjg-marketplace-1-0-0-rc.onrender.com/webjars/swagger-ui/index.html)

> ⚠️ **Atenção:** A aplicação pode ser temporariamente suspensa em períodos de inatividade devido às limitações do plano gratuito da Render.

Diagrama de fluxo:
[Diagrama editável](.github/images/diagram.drawio)


---

## 🧪 Como Rodar Localmente com Docker

Antes de iniciar, **certifique-se de que as configurações no application-dev.yaml estão corretas!**.  Caso utilize a versão de produção, não esqueça de injetar as variaveis de ambiente (facil acesso via .envexample)

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
  
# Marketplace
