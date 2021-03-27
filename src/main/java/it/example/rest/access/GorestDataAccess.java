package it.example.rest.access;

import com.google.gson.Gson;
import it.example.rest.model.Response;
import it.example.rest.exception.BadResponceException;
import it.example.rest.model.GorestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class GorestDataAccess implements ExampleDataAccess {

    private static final Logger LOGGER= LoggerFactory.getLogger(GorestDataAccess.class);
    private final static String PATH = "https://gorest.co.in/public-api/products";
    private final static String ACCEPT = "accept";
    private final static String JSON = "application/json";

    /**
     * Method to get response from gorest api.
     * @return Response of gorest api, in case not 200 response or not 200 code in response answer data throw BadResponceException
     * @throws IOException if something wrong during the connection
     * @throws BadResponceException if code of response is not 200 even if inside the response data, code is not 200
     */
    @Override
    public Response getData() throws IOException, BadResponceException {
        HttpURLConnection con = getConnection();
        GorestResponse response = new Gson().fromJson(new BufferedReader(new InputStreamReader(con.getInputStream())), GorestResponse.class);
        if (response.getCode() != HttpStatus.OK.value() || con.getResponseCode() != HttpStatus.OK.value()){
            int resp = (response.getCode() != HttpStatus.OK.value() ? response.getCode() : con.getResponseCode());
            LOGGER.error("Response of service was: " + resp);
            throw new BadResponceException("Response of service was: " + resp);
        }
        LOGGER.info("Call to " + PATH +" OK");
        return response;
    }

    /**
     * Method to make get call to gorest service
     * @return HttpUrlConnection of call to gorest service
     * @throws IOException in case of runtime error
     */
    private HttpURLConnection getConnection() throws IOException {
        URL url = new URL(PATH);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty(ACCEPT, JSON);
        return connection;
    }
}
