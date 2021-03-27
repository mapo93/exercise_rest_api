package it.example.rest.controller;

import it.example.rest.model.Response;
import it.example.rest.service.GorestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("my-api")
public class ExampleController {

    @Autowired
    private GorestService gorestService;

    /**
     * Entry point of api
     * @param categoryId not mandatory, if set products are filtered
     * @return list of products if categoryId set as -1 or not set, list of products filtered filtered by categoryId if
     * set and exist or empty list if doesn't exist category id, error if category id less than -1
     */
    @GetMapping("/products")
    public Response getProducts(@RequestParam(value = "categoryId", defaultValue = "-1") int categoryId) {
        return gorestService.getProducts(categoryId);
    }

}

