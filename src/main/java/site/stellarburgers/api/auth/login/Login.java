package site.stellarburgers.api.auth.login;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static site.stellarburgers.api.Url.LOGIN_URL;

public class Login {
    @Step("Залогиниться в приложение")
    public void loginUser(Object body){
        given()
                .header("Content-type", "application/json")
                .body(body)
                .post(LOGIN_URL);
    }
    @Step("Получить ответ при логине в приложение")
    public Response getloginUserResponse(Object body){
        return given()
                .header("Content-type", "application/json")
                .body(body)
                .post(LOGIN_URL);
    }
}
