package site.stellarburgers.api.ingredients;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static site.stellarburgers.api.Url.INGREDIENTS_URL;

public class Ingredients {
    @Step("Получить список ингридиентов")
    public Response getIngredientsResponse(){
        return given()
                        .get(INGREDIENTS_URL);
    }
}
