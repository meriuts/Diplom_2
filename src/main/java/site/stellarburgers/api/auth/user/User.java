package site.stellarburgers.api.auth.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static site.stellarburgers.api.Url.USER_URL;

public class User {
    @Step("Сохранить изменение данных пользователя")
    public void changeUserData(String authorizationToken, Object body) {
        given()
                .header("Content-type", "application/json")
                .header("Authorization", authorizationToken)
                .body(body)
                .patch(USER_URL);
    }
    @Step("Получить ответ при сохранении изменений данных пользователя")
    public Response getChangeUserDataResponse(String authorizationToken, Object body) {
        return given()
                        .header("Content-type", "application/json")
                        .header("Authorization", authorizationToken)
                        .body(body)
                        .patch(USER_URL);
    }
    @Step("Получить ответ при сохранении изменений данных неавторизованным пользователем ")
    public Response getChangeUnauthorizationUserDataResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .body(body)
                .patch(USER_URL);
    }

}
