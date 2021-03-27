package it.example.rest.service;

import it.example.rest.access.GorestDataAccess;
import it.example.rest.exception.BadResponceException;
import it.example.rest.support.SupportTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class GorestServiceTest {

    @InjectMocks
    private GorestService gorestService;

    @Mock
    private GorestDataAccess dataAccess;

    @Autowired
    private SupportTest supportTest;

    @BeforeEach
    void setMock() throws IOException, BadResponceException {
        when(dataAccess.getData()).thenReturn(supportTest.getTestResponse());
    }

    @DisplayName("Test categoryId less then -1")
    @Test
    void throwBadRequest() throws IOException, BadResponceException {
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            gorestService.getProducts(-2);
        });
        assertTrue(exception.getMessage().contains("Wrong categoryId"));
        verify(dataAccess, times(0)).getData();
    }

    @DisplayName("Test categoryId equal -1 return list")
    @Test
    void getList() throws IOException, BadResponceException {
        assertEquals(4, gorestService.getProducts(-1).getData().size());
        verify(dataAccess, times(1)).getData();
    }

    @DisplayName("Test categoryId equal 2 return filtered list")
    @Test
    void getListFiltered() throws IOException, BadResponceException {
        assertEquals(3, gorestService.getProducts(2).getData().size());
        verify(dataAccess, times(1)).getData();
    }

    @DisplayName("Test categoryId equal 2 return empty list")
    @Test
    void getEmptyList() throws IOException, BadResponceException {
        assertEquals(0, gorestService.getProducts(8).getData().size());
        verify(dataAccess, times(1)).getData();
    }

    @DisplayName("Test exception on BadResponceException gorestAccess")
    @Test
    void throwGorestAccessBadResponceException() throws IOException, BadResponceException {
        when(dataAccess.getData()).thenThrow(new BadResponceException());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            gorestService.getProducts(2);
        });
        assertTrue(exception.getMessage().contains("Something wrong"));
        verify(dataAccess, times(1)).getData();
    }

    @DisplayName("Test exception on IOException gorestAccess")
    @Test
    void throwGorestAccessIOException() throws IOException, BadResponceException {
        when(dataAccess.getData()).thenThrow(new IOException());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            gorestService.getProducts(2);
        });
        assertTrue(exception.getMessage().contains("Something wrong"));
        verify(dataAccess, times(1)).getData();
    }

}
