package it.example.rest.access;

import it.example.rest.exception.BadResponceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GorestDataAccessTest {

    @Autowired
    private GorestDataAccess gorestDataAccess;

    @DisplayName("Test method getData of GorestDataAccess Class")
    @Test
    void testGetData() throws IOException, BadResponceException {
        assertEquals(20, gorestDataAccess.getData().getData().size());
    }
}
