package site.stellarburgers.api.auth;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import site.stellarburgers.api.auth.delete.Delete;
import site.stellarburgers.api.auth.login.Login;
import site.stellarburgers.api.auth.login.body.request.LoginBody;
import site.stellarburgers.api.auth.login.body.response.LoginResponseBody;
import site.stellarburgers.api.auth.register.Register;
import site.stellarburgers.api.auth.register.body.request.RegisterBody;

import java.util.Locale;

import static site.stellarburgers.api.Url.BASE_URL;

public class BaseTest {

    Register register;
    RegisterBody registerBody;
    Faker faker;
    FakeValuesService fakerValuesService;
    String accessToken;
    String email;
    String password;
    String name;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        register = new Register();

        faker = new Faker();
        fakerValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

        email = fakerValuesService.bothify("??????###@gmail.com");
        password = fakerValuesService.bothify("?#?#???");
        name = faker.name().firstName();

        registerBody = new RegisterBody(email, password, name);
    }

    @After
    public void deleteUser(){
        Delete delete = new Delete();
        Login login = new Login();
        LoginBody loginBody = new LoginBody(email, password);
        LoginResponseBody loginResponseBody = login.getloginUserResponse(loginBody).body().as(LoginResponseBody.class);
        accessToken = loginResponseBody.getAccessToken();
        if(accessToken != null) {
            delete.deleteUser(accessToken);
        }
    }


}
