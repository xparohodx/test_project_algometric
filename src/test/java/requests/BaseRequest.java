package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONArray;
import utils.JsonHelper;
import utils.University;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseRequest {
    Response response;
    static Logger logger = Logger.getLogger(BaseRequest.class.getName());

    //checks that response status is correct
    public ValidatableResponse checkResponseStatusCode(int code) {
        logger.log(Level.INFO, "Check that response code is " + code);
        return this.response.then().statusCode(code);
    }

    //checks that response content type is correct
    public ValidatableResponse checkResponseContentType(ContentType type) {
        logger.log(Level.INFO, "Check that response has type " + type);
        return this.response.then().contentType(type);
    }

    //checks that all values in response body are present in control JSON file
    public void checkValuesInResponse() {
        JsonHelper jsonHelper = new JsonHelper();
        String json = this.response.getBody().asString().replace("state-province", "state_province");
        List<University> current = jsonHelper.jsonArrayToUniversityList(json);
        List<University> expected = jsonHelper.getAllRecordsFromJson();
        jsonHelper.compareObjects(expected, current);
        logger.log(Level.INFO, "Check that response body has correct data");
    }

    //checks that response body is empty
    public boolean checkResponseBodyIsEmpty() {
        logger.log(Level.INFO, "Check that response body is empty");
        return this.response.getBody().as(JSONArray.class).isEmpty();
    }
}
