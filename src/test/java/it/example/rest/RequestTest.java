package it.example.rest;

import it.example.rest.model.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestTest{

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("Test on call")
    @Test
    public void greetingShouldReturnDefaultMessage() {
        assertFalse(this.restTemplate.getForObject("http://localhost:" + port + "/my-api/products",
                Response.class).getData().isEmpty());
    }
}
