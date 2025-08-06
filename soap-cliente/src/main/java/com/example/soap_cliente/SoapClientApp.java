package com.example.soap_cliente;
// Simularemos duas aplicações Spring Boot:
// - Porta 8080: Cliente que envia um XML SOAP
// - Porta 8081: Servidor que atua como "middleware"

// =============================
// 1. Cliente (porta 8080)
// =============================

// pom.xml (dependências básicas SOAP)
// spring-boot-starter-web
// spring-boot-starter-web-services
// jaxb-api

import com.example.soap_cliente.exceptions.MiddlewareIndisponivelException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

// src/main/java/com/exemplo/cliente/SoapClientApp.java
@SpringBootApplication
@RestController
public class SoapClientApp {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SoapClientApp.class);
		app.run(args);
	}

	@PostMapping(value = "/enviar", consumes = {"application/xml"}, produces = "text/xml")
	public ResponseEntity<String> enviarXmlRecebido(@RequestBody String xmlRecebido) throws Exception {

		System.out.println("Cliente recebeu e vai repassar:\n" + xmlRecebido);


		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8081/processar"))
				.header("Content-Type", "text/xml")
				.POST(HttpRequest.BodyPublishers.ofString(xmlRecebido))
				.build();

		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			return ResponseEntity
					.ok()
					.contentType(MediaType.TEXT_XML)
					.body(response.body());

		} catch (IOException | InterruptedException e) {
			throw new MiddlewareIndisponivelException("Falha ao se comunicar com o middleware", e);
		}
	}
}