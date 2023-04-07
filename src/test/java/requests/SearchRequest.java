package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONArray;

import java.util.logging.Level;

public class SearchRequest extends BaseRequest{

    public SearchRequest() {

    }
    public Response search(String url, String countryName) {
        this.response = RestAssured
                .given()
                .param("country", countryName)
                .get(url + "search");
        if (this.response.getBody().as(JSONArray.class).isEmpty()) {
            this.response = RestAssured
                    .given()
                    .param("name", countryName)
                    .get(url + "search");
        }
        logger.log(Level.INFO, "Sending request GET " + url + "search");
        return this.response;
    }

    public Response search(String url, String countryName, String universityName) {
        this.response = RestAssured
                .given()
                .param("country", countryName)
                .param("name", universityName)
                .get(url + "search");
        logger.log(Level.INFO, "Sending request GET " + url + "search");
        return this.response;
    }

    public Response search(String url, int name) {
        this.response = RestAssured
                .given()
                .param("name", name)
                .get(url + "search");
        logger.log(Level.INFO, "Sending request GET " + url + "search");
        return this.response;
    }
}
