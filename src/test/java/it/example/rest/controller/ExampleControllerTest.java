package it.example.rest.controller;

import it.example.rest.service.GorestService;
import it.example.rest.support.SupportTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ExampleController.class)
public class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GorestService gorestService;

    private final SupportTest supportTest = new SupportTest();

    @DisplayName("Test categoryId no set")
    @Test
    public void getProductsNoCategoryId() throws Exception {
		when(gorestService.getProducts(-1)).thenReturn(supportTest.getTestResponse());
        mockMvc.perform(get("/my-api/products")).andDo(print()).andExpect(status().isOk());
	}

    @DisplayName("Test categoryId set as 2")
    @Test
    public void getProductsFiltered() throws Exception {
        when(gorestService.getProducts(2)).thenReturn(supportTest.getTestResponse());
        mockMvc.perform(get("/my-api/products?categoryId=2")).andDo(print()).andExpect(status().isOk());
    }

    @DisplayName("Test categoryId set as -2")
    @Test
    public void getProductsWrongCategoryId() throws Exception {
        when(gorestService.getProducts(-2)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong categoryId"));
        mockMvc.perform(get("/my-api/products?categoryId=-2")).andDo(print()).andExpect(status().is4xxClientError());
    }


}
