package site.stellarburgers.api.auth;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.api.auth.login.Login;
import site.stellarburgers.api.auth.login.body.request.LoginBody;
import site.stellarburgers.api.auth.login.body.response.LoginResponseBody;
import site.stellarburgers.api.auth.user.User;
import site.stellarburgers.api.auth.user.UserBody;
import static org.hamcrest.Matchers.equalTo;

public class UserTest extends BaseTest {
    private Login login;
    private  LoginBody loginBody;
    private User user;
    private String newEmail;
    private String newPassword;
    private String newName;
    private String authorizationToken;
    @Before
    public void createTestData(){
        register.registerUser(registerBody);
        login = new Login();
        loginBody = new LoginBody(registerBody.getEmail(), registerBody.getPassword());

        user = new User();

        newEmail = fakerValuesService.bothify("??????###@mail.com");
        newPassword = fakerValuesService.bothify("?#?#??");
        newName = faker.name().firstName();
    }

    @Test
    @Description("Авторизованный пользователь может изменить свою почту")
    public void authorizedUserCanChangeEmail(){
        LoginResponseBody loginResponseBody = login.getloginUserResponse(loginBody).body().as(LoginResponseBody.class);
        authorizationToken = loginResponseBody.getAccessToken();

        UserBody userBody = new UserBody(newEmail, password,name);
        Response response = user.getChangeUserDataResponse(authorizationToken, userBody);
        response.then()
                        .statusCode(200)
                        .assertThat().body("success", equalTo(true))
                        .assertThat().body("user.email", equalTo(newEmail));
    }
    @Test
    @Description("Авторизованный пользователь может изменить свой пароль")
    public void authorizedUserCanChangePassword(){
        LoginResponseBody loginResponseBody = login.getloginUserResponse(loginBody).body().as(LoginResponseBody.class);
        authorizationToken = loginResponseBody.getAccessToken();

        UserBody userBody = new UserBody(email, newPassword,name);
        Response response = user.getChangeUserDataResponse(authorizationToken, userBody);
        response.then()
                .statusCode(200)
                .assertThat().body("success", equalTo(true));
    }

    @Test
    @Description("Авторизованный пользователь может изменить свое имя")
    public void authorizedUserCanChangeName(){
        LoginResponseBody loginResponseBody = login.getloginUserResponse(loginBody).body().as(LoginResponseBody.class);
        authorizationToken = loginResponseBody.getAccessToken();

        UserBody userBody = new UserBody(email, password,newName);
        Response response = user.getChangeUserDataResponse(authorizationToken, userBody);
        response.then()
                .statusCode(200)
                .assertThat().body("success", equalTo(true))
                .assertThat().body("user.name", equalTo(newName));
    }

    @Test
    @Description("Неавторизованный пользователь не может изменить почту")
    public void unauthorizedUserCannotChangeEmail(){
        UserBody userBody = new UserBody(newEmail, password,name);
        Response response = user.getChangeUnauthorizationUserDataResponse(userBody);

        response.then()
                .statusCode(401)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("You should be authorised"));
    }

    @Test
    @Description("Неавторизованный пользователь не может изменить пароль")
    public void unauthorizedUserCanChangePassword(){
        UserBody userBody = new UserBody(email, newPassword,name);
        Response response = user.getChangeUnauthorizationUserDataResponse(userBody);

        response.then()
                .statusCode(401)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("You should be authorised"));
    }

    @Test
    @Description("Неавторизованный пользователь не может изменить имя")
    public void unauthorizedUserCanChangeName(){
        UserBody userBody = new UserBody(email, password, newName);
        Response response = user.getChangeUnauthorizationUserDataResponse(userBody);
        response.then()
                .statusCode(401)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("You should be authorised"));
    }

}
