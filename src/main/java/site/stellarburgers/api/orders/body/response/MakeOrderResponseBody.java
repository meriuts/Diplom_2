package site.stellarburgers.api.orders.body.response;

public class MakeOrderResponseBody {
    private boolean success;
    private String name;
    private Orders order;

    public MakeOrderResponseBody(boolean success, String name, Orders order) {
        this.success = success;
        this.name = name;
        this.order = order;
    }

    public MakeOrderResponseBody() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
