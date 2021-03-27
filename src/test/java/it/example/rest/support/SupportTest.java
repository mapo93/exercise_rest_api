package it.example.rest.support;

import it.example.rest.model.Category;
import it.example.rest.model.Product;
import it.example.rest.model.Response;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupportTest {

    public Response getTestResponse(){
        return new Response(getProducts());
    }

    private List<Product> getProducts(){
        List<Product> prod = new ArrayList<>();
        for(int i=0; i<4;i++){
            prod.add(getProduct(i));
        }
        return prod;
    }

    private Product getProduct(int id){
        Product prd = new Product();
        prd.setId(id);
        List<Category> categories = new ArrayList<>();
        for(int i=id; i<7;i++){
            Category cat = new Category();
            cat.setId(i);
            categories.add(cat);
        }
        prd.setCategories(categories);
        return prd;
    }

}
