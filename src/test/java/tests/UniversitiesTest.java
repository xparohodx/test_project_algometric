package tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import requests.SearchRequest;

public class UniversitiesTest extends BaseTest {

@Test(description = "Find all universities by country")
    public void testSearchByCountry() {
    String countryName = "canada";

    SearchRequest request = new SearchRequest();

    request.search(baseURL, countryName);
    request.checkResponseStatusCode(200);
    request.checkResponseContentType(ContentType.JSON);
    request.checkValuesInResponse();
}

@Test(description = "Find universities with given name")
    public void testSearchByName() {
        String universityName = "toronto";

        SearchRequest request = new SearchRequest();

        request.search(baseURL, universityName);
        request.checkResponseStatusCode(200);
        request.checkResponseContentType(ContentType.JSON);
        request.checkValuesInResponse();
    }

@Test(description = "Find specific university by country and part of an university name")
    public void testSearchByCountryAndName() {
    String countryName = "uruguay";
    String universityName = "montevideo";

    SearchRequest request = new SearchRequest();

    request.search(baseURL, countryName, universityName);
    request.checkResponseStatusCode(200);
    request.checkResponseContentType(ContentType.JSON);
    request.checkValuesInResponse();
}

@Test(description = "Nothing found, valid parameter in request")
    public void testEmptySearchResponse() {
    String countryName = "canada";
    String universityName = "london";

    SearchRequest request = new SearchRequest();

    request.search(baseURL, countryName, universityName);
    request.checkResponseStatusCode(200);
    request.checkResponseContentType(ContentType.JSON);
    request.checkResponseBodyIsEmpty();
}

@Test(description = "Nothing found, invalid parameter in request")
    public void testEmptySearchResponseWithInvalidParameter() {
    int universityName = 12345;

    SearchRequest request = new SearchRequest();

    request.search(baseURL, universityName);
    request.checkResponseStatusCode(200);
    request.checkResponseContentType(ContentType.JSON);
    request.checkResponseBodyIsEmpty();
}


}
