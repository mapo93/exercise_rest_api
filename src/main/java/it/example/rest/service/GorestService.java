package it.example.rest.service;

import it.example.rest.access.ExampleDataAccess;
import it.example.rest.access.GorestDataAccess;
import it.example.rest.exception.BadResponceException;
import it.example.rest.model.Category;
import it.example.rest.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class GorestService {

    private static final Logger LOGGER= LoggerFactory.getLogger(GorestDataAccess.class);

    @Autowired
    private ExampleDataAccess dataAccess;

    /**
     * Service Method to make logic of api. If categoryId set less than -1 throw exception as badRequest.
     * @param categoryId filed use to filter or not products
     * @return List of Products or list of filtered Products. If somethings wrong during get Product or if category Id
     * not allowed throw ResponseStatusException
     */
    public Response getProducts(int categoryId) {
        if(categoryId < -1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong categoryId");
        }
        Response resp;
        try {
            if (categoryId == -1) {
                resp = getList();
            } else {
                resp = getListByCategoryId(categoryId);
            }
            return resp;
        } catch (IOException | BadResponceException e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something wrong");
        }
    }

    /**
     * Method to retrieve list of Products
     * @return List of Products
     */
    private Response getList() throws IOException, BadResponceException {
        return dataAccess.getData();
    }

    /**
     * Method to retrieve list of Products filtered by categoryId
     * @param categoryId filed use to filter products
     * @return List of Products filtered
     */
    private Response getListByCategoryId(int categoryId) throws IOException, BadResponceException {
        return new Response(dataAccess.getData().getData().stream().filter(p -> p.getCategories().stream().map(Category::getId).collect(Collectors.toList()).contains(categoryId)).collect(Collectors.toList()));
    }

}
