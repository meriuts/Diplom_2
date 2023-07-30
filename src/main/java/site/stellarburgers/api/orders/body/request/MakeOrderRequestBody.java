package site.stellarburgers.api.orders.body.request;

public class MakeOrderRequestBody {
    private String[] ingredients;

    public MakeOrderRequestBody(String[] ingredients) {
        this.ingredients = ingredients;
    }
    public MakeOrderRequestBody() {
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
