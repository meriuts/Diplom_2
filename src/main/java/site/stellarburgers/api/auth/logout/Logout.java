package site.stellarburgers.api.auth.logout;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static site.stellarburgers.api.Url.LOGOUT_URL;

public class Logout {
    @Step("Выход из аккаунта")
    public void logout(Object body){
        given()
                .header("Content-type", "application/json")
                .body(body)
                .post(LOGOUT_URL);
    }
}
