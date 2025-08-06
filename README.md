# 🧪 Simulação de Integração SOAP: Cliente <-> Middleware
![educational](https://img.shields.io/badge/purpose-educational-blue)
![test](https://img.shields.io/badge/type-test-lightgrey)

Este projeto simula um fluxo SOAP completo entre duas aplicações Spring Boot rodando em Java 24:

- `soap-cliente`: recebe XML via `/enviar`, encaminha para o middleware
- `soap-middleware`: recebe em `/processar`, responde com XML SOAP

## 🧰 Tecnologias

- Java 24
- Spring Boot (Web + Web Services)
- Maven Wrapper
- HTTP Client Java (HttpClient)
- Formato SOAP 1.1 (`text/xml`)

---

## 📦 Estrutura

```bash
simulacao-soap/
├── init.sh               # Script para subir os dois serviços
├── soap-cliente/         # Projeto cliente
└── soap-middleware/      # Projeto middleware
````

---

## 🚀 Como rodar

### Pré-requisitos:

* Java 24 instalado
* Permissão de execução no script:

```bash
chmod +x init.sh
```

### Rodar os serviços:

```bash
./init.sh
```

* Cliente disponível em `http://localhost:8080/enviar`
* Middleware disponível em `http://localhost:8081/processar`

---

## 📡 Testando a integração

Use `curl`, Postman ou Insomnia:

```bash
curl -X POST http://localhost:8080/enviar \
  -H "Content-Type: text/xml" \
  -d '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
        <soapenv:Header/>
        <soapenv:Body>
          <msg>Olá Middleware</msg>
        </soapenv:Body>
      </soapenv:Envelope>'
```

Você receberá uma resposta SOAP do middleware como:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Header/>
  <soapenv:Body>
    <msg>Processado com sucesso pelo Middleware</msg>
  </soapenv:Body>
</soapenv:Envelope>
```

---

## ⚠️ Tratamento de Erros

Caso o middleware esteja offline, o cliente responde com:

* **HTTP 502 Bad Gateway**
* **Mensagem SOAP de falha**:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Header/>
  <soapenv:Body>
    <fault>Middleware indisponível</fault>
  </soapenv:Body>
</soapenv:Envelope>
```

---

## 📂 Licença

Este projeto é de uso livre para fins educacionais.

