package site.stellarburgers.api.orders;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static site.stellarburgers.api.Url.ORDERS_URL;

public class GetOrders {

    public Response getOrders(String accessToken){
        return given()
                .header("Authorization", accessToken)
                .get(ORDERS_URL);
    }


}
