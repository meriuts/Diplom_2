package site.stellarburgers.api.auth;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class RegisterTest extends BaseTest  {

    @Test
    @Description("Пользователь может зарегистрироваться с валидными данными")
    public void userCanRegister() {
        Response responseRegister = register.getRegisterUserResponse(registerBody);
        responseRegister.then()
                        .statusCode(200)
                        .assertThat().body("success", equalTo(true))
                        .assertThat().body("accessToken", startsWith("Bearer"));
    }
    @Test
    @Description("Нельзя зарегистрировать пользователей с одинаковой почтой")
    public void userCannotRegisterWithSameEmail() {
        register.registerUser(registerBody);

        Response responseRegister = register.getRegisterUserResponse(registerBody);
        responseRegister.then()
                .statusCode(403)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("User already exists"));
    }
    @Test
    @Description("Нельзя зарегистрировать пользователей без указания почты")
    public void userCannotRegisterWithoutEmail() {
        registerBody.setEmail("");

        Response responseRegister = register.getRegisterUserResponse(registerBody);
        responseRegister.then()
                .statusCode(403)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @Description("Нельзя зарегистрировать пользователей без указания пароля")
    public void userCannotRegisterWithoutPassword() {
        registerBody.setPassword("");

        Response responseRegister = register.getRegisterUserResponse(registerBody);
        responseRegister.then()
                .statusCode(403)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @Description("Нельзя зарегистрировать пользователей без указания имени")
    public void userCannotRegisterWithoutName() {
        registerBody.setName("");

        Response responseRegister = register.getRegisterUserResponse(registerBody);
        responseRegister.then()
                .statusCode(403)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("Email, password and name are required fields"));
    }

}
