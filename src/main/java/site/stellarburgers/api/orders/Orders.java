package site.stellarburgers.api.orders;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static site.stellarburgers.api.Url.ORDERS_URL;

public class Orders {
    @Step("Создать заказ")
    public Response getMakeOrderResponse(String accessToken, Object body){
        return given()
                        .header("Content-type", "application/json")
                        .header("Authorization", accessToken)
                        .body(body)
                        .post(ORDERS_URL);
    }
    @Step("Получить ответ при создании заказа")
    public Response getMakeOrderResponseUnauthorizedUser(Object body){
        return given()
                .header("Content-type", "application/json")
                .body(body)
                .post(ORDERS_URL);
    }

}
