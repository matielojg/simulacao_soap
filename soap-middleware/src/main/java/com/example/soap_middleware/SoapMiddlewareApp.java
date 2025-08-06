package com.example.soap_middleware;

// =============================
// 2. Middleware (porta 8081)
// =============================

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SoapMiddlewareApp {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SoapMiddlewareApp.class);
		app.run(args);
	}

	@PostMapping(value = "/processar", consumes = "text/xml", produces = "text/xml")
	public ResponseEntity<String> processar(@RequestBody String xml) {
		System.out.println("Recebido pelo middleware:\n" + xml);

		String resposta = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
              <soapenv:Header/>
              <soapenv:Body>
                <msg>Processado com sucesso pelo Atlântico</msg>
              </soapenv:Body>
            </soapenv:Envelope>
            """;

		return ResponseEntity.ok()
				.contentType(MediaType.TEXT_XML)
				.body(resposta);
	}
}

