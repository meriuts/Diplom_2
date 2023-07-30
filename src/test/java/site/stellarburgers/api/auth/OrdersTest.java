package site.stellarburgers.api.auth;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.api.auth.login.Login;
import site.stellarburgers.api.auth.login.body.request.LoginBody;
import site.stellarburgers.api.auth.login.body.response.LoginResponseBody;
import site.stellarburgers.api.ingredients.Ingredients;
import site.stellarburgers.api.ingredients.body.Data;
import site.stellarburgers.api.ingredients.body.IngredientsResponseBody;
import site.stellarburgers.api.orders.Orders;
import site.stellarburgers.api.orders.body.request.MakeOrderRequestBody;
import java.util.List;
import static org.hamcrest.Matchers.*;


public class OrdersTest extends BaseTest {
    private Login login;
    private  LoginBody loginBody;
    private String authorizationToken;
    private Ingredients ingredients;
    private IngredientsResponseBody ingredientsResponseBody;
    private List<Data> ingredientsList;
    private String wrongHashIngredient;
    private Orders orders;

    @Before
    public void setTestDat(){
        register.registerUser(registerBody);
        login = new Login();
        loginBody = new LoginBody(email, password);

        ingredients = new Ingredients();
        ingredientsResponseBody = ingredients.getIngredientsResponse().body().as(IngredientsResponseBody.class);
        ingredientsList = ingredientsResponseBody.getData();

        orders = new Orders();

        wrongHashIngredient = fakerValuesService.bothify("?#?##??###????###??###??");
    }

    @Test
    @Description("Авторизованный пользователь может создать заказ")
    public void authorizedUserCanMakeOrder(){
        LoginResponseBody loginResponseBody = login.getloginUserResponse(loginBody).body().as(LoginResponseBody.class);
        authorizationToken = loginResponseBody.getAccessToken();

        String ingredientId = ingredientsList.get(0).get_id();
        String[] ingredients = new String[]{ingredientId};
        MakeOrderRequestBody makeOrderRequestBody = new MakeOrderRequestBody(ingredients);

        Response orderResponse = orders.getMakeOrderResponse(authorizationToken, makeOrderRequestBody);
        orderResponse.then()
                            .statusCode(200)
                            .assertThat().body("success", equalTo(true))
                            .assertThat().body("order.ingredients[0]._id", equalTo(ingredientId));
    }

    @Test
    @Description("Нельзя создать заказ с без указания id ингредиента")
    public void userCannotMakeOrderWithoutIngredient(){
        LoginResponseBody loginResponseBody = login.getloginUserResponse(loginBody).body().as(LoginResponseBody.class);
        authorizationToken = loginResponseBody.getAccessToken();

        String[] ingredients = new String[]{};
        MakeOrderRequestBody makeOrderRequestBody = new MakeOrderRequestBody(ingredients);

        Response orderResponse = orders.getMakeOrderResponse(authorizationToken, makeOrderRequestBody);
        orderResponse.then()
                .statusCode(400)
                .assertThat().body("message", equalTo("Ingredient ids must be provided"))
                .assertThat().body("success", equalTo(false));
    }

    @Test
    @Description("Нельзя создать заказ с неверныйм id ингредиента")
    public void userCannotMakeOrderWrongHashIngredient(){
        LoginResponseBody loginResponseBody = login.getloginUserResponse(loginBody).body().as(LoginResponseBody.class);
        authorizationToken = loginResponseBody.getAccessToken();

        String[] ingredients = new String[]{wrongHashIngredient};
        MakeOrderRequestBody makeOrderRequestBody = new MakeOrderRequestBody(ingredients);

        Response orderResponse = orders.getMakeOrderResponse(authorizationToken, makeOrderRequestBody);
        orderResponse.then()
                .statusCode(500);
    }

    @Test
    @Description("Неввторизованный пользователь не может создать заказ")
    public void unauthorizedUserCanMakeOrder(){
        String ingredientId = ingredientsList.get(0).get_id();
        String[] ingredients = new String[]{ingredientId};
        MakeOrderRequestBody makeOrderRequestBody = new MakeOrderRequestBody(ingredients);

        Response orderResponse = orders.getMakeOrderResponseUnauthorizedUser(makeOrderRequestBody);
        orderResponse.then()
                .statusCode(200)
                .assertThat().body("success", equalTo(true));
    }

}
