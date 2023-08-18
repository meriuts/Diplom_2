package site.stellarburgers.api.auth.register;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static site.stellarburgers.api.Url.REGISTER_URL;

public class Register {

    @Step("Регистрация нового пользователя")
    public void registerUser(Object body) {
        given()
                .header("Content-type", "application/json")
                .body(body)
                .post(REGISTER_URL);
    }
    @Step("Получение ответа при регистрации нового пользователя")
    public Response getRegisterUserResponse(Object body) {
        return  given()
                        .header("Content-type", "application/json")
                        .body(body)
                        .post(REGISTER_URL);
    }



}
