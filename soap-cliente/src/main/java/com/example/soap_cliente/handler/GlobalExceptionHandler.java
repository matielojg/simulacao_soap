package com.example.soap_cliente.handler;

import com.example.soap_cliente.exceptions.MiddlewareIndisponivelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MiddlewareIndisponivelException.class)
    public ResponseEntity<String> handleMiddlewareIndisponivel(MiddlewareIndisponivelException ex) {
        String resposta = """
            <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">
              <soapenv:Header/>
              <soapenv:Body>
                <fault>Middleware indisponível</fault>
              </soapenv:Body>
            </soapenv:Envelope>
        """;

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .contentType(MediaType.TEXT_XML)
                .body(resposta);
    }
}
