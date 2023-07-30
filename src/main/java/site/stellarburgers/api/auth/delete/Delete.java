package site.stellarburgers.api.auth.delete;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static site.stellarburgers.api.Url.DELETE_URL;

public class Delete {
    @Step("Удвлить пользователя")
    public void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .delete(DELETE_URL);
    }

}
