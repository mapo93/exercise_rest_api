package it.example.rest.model;

import java.util.List;

public class Response {
    private List<Product> data;

    public Response() { }

    public Response(List<Product> data) {
        this.data = data;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
