package it.example.rest.access;

import it.example.rest.model.Response;
import it.example.rest.exception.BadResponceException;

import java.io.IOException;

public interface ExampleDataAccess {

    Response getData() throws IOException, BadResponceException;

}
