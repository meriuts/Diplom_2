package site.stellarburgers.api.auth;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.api.auth.login.Login;
import site.stellarburgers.api.auth.login.body.request.LoginBody;
import site.stellarburgers.api.auth.login.body.response.LoginResponseBody;
import site.stellarburgers.api.ingredients.Ingredients;
import site.stellarburgers.api.ingredients.body.Data;
import site.stellarburgers.api.ingredients.body.IngredientsResponseBody;
import site.stellarburgers.api.orders.GetOrders;
import site.stellarburgers.api.orders.Orders;
import site.stellarburgers.api.orders.body.request.MakeOrderRequestBody;
import site.stellarburgers.api.orders.body.response.ListOrders;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;

public class GetOrdersTest extends BaseTest {
    private Login login;
    private  LoginBody loginBody;
    private String authorizationToken;
    private Ingredients ingredients;
    private IngredientsResponseBody ingredientsResponseBody;
    private List<Data> ingredientsList;
    private Orders orders;
    private GetOrders getOrders;



    @Before
    public void setTestDat(){
        register.registerUser(registerBody);
        login = new Login();
        loginBody = new LoginBody(email, password);

        ingredients = new Ingredients();
        ingredientsResponseBody = ingredients.getIngredientsResponse().body().as(IngredientsResponseBody.class);
        ingredientsList = ingredientsResponseBody.getData();

        orders = new Orders();
        getOrders = new GetOrders();
    }

    @Test
    @Description("Авторизованный пользователь может получить список заказов")
    public void authorizedUserCanGetListOrders() {
        LoginResponseBody loginResponseBody = login.getloginUserResponse(loginBody).body().as(LoginResponseBody.class);
        authorizationToken = loginResponseBody.getAccessToken();

        String ingredient = ingredientsList.get(0).get_id();
        String ingredient2 = ingredientsList.get(1).get_id();
        String[] ingredients = new String[]{ingredient, ingredient2};

        MakeOrderRequestBody makeOrderRequestBody = new MakeOrderRequestBody(ingredients);
        orders.getMakeOrderResponse(authorizationToken, makeOrderRequestBody);

        Response getOrdersResponse = getOrders.getOrders(authorizationToken);
        ListOrders listOrders = getOrdersResponse.body().as(ListOrders.class);

        getOrdersResponse.then()
                                .statusCode(200)
                                .assertThat().body("success", equalTo(true));
       Assert.assertTrue(listOrders.getOrders().size() > 0);
    }

    @Test
    @Description("Неввторизованный пользователь не может получить список заказов")
    public void unauthorizedUserCannotGetListOrders() {
        LoginResponseBody loginResponseBody = login.getloginUserResponse(loginBody).body().as(LoginResponseBody.class);
        authorizationToken = loginResponseBody.getAccessToken();

        String ingredient = ingredientsList.get(0).get_id();
        String ingredient2 = ingredientsList.get(1).get_id();
        String[] ingredients = new String[]{ingredient, ingredient2};

        MakeOrderRequestBody makeOrderRequestBody = new MakeOrderRequestBody(ingredients);
        orders.getMakeOrderResponse(authorizationToken, makeOrderRequestBody);

        authorizationToken = "";
        Response getOrdersResponse = getOrders.getOrders(authorizationToken);
        getOrdersResponse.then()
                .statusCode(401)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("You should be authorised"));
    }

}
