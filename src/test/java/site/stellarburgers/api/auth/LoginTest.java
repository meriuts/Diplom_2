package site.stellarburgers.api.auth;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.api.auth.login.Login;
import site.stellarburgers.api.auth.login.body.request.LoginBody;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;


public class LoginTest extends BaseTest {
    private Login login;
    private  LoginBody loginBody;

    @Before
    public void createTestData(){
        register.registerUser(registerBody);
        login = new Login();
        loginBody = new LoginBody(registerBody.getEmail(), registerBody.getPassword());
    }

    @Test
    @Description("Пользователь может залогиниться")
    public void userCanLogin() {
        Response loginResponse = login.getloginUserResponse(loginBody);
        loginResponse.then()
                            .statusCode(200)
                            .assertThat().body("success", equalTo(true))
                            .assertThat().body("accessToken", startsWith("Bearer"));
    }

    @Test
    @Description("Нельзя залогиниться с почтой, которая незарегистрирована")
    public void userCannotLogin_WrongEmail() {
        loginBody.setEmail("1" + email);

        Response loginResponse = login.getloginUserResponse(loginBody);
        loginResponse.then()
                .statusCode(401)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @Description("Нельзя залогиниться с неверным поролем")
    public void userCannotLogin_WrongPassword() {
        loginBody.setPassword(password + "1");

        Response loginResponse = login.getloginUserResponse(loginBody);
        loginResponse.then()
                .statusCode(401)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("email or password are incorrect"));
    }

}
