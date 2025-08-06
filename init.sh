#!/bin/bash

# init.sh - Sobe os dois projetos Spring Boot em paralelo

# Sobe o cliente (porta 8080)
echo "Iniciando soap-cliente na porta 8080..."
cd soap-cliente || exit 1
./mvnw spring-boot:run &
CLIENT_PID=$!
cd ..

# Sobe o middleware (porta 8081)
echo "Iniciando soap-middleware na porta 8081..."
cd soap-middleware || exit 1
./mvnw spring-boot:run &
MIDDLEWARE_PID=$!
cd ..

# Mostra os PIDs e status
echo "Aplicações iniciadas:"
echo "soap-cliente (PID: $CLIENT_PID)"
echo "soap-middleware (PID: $MIDDLEWARE_PID)"

# Espera os processos
wait $CLIENT_PID
wait $MIDDLEWARE_PID
