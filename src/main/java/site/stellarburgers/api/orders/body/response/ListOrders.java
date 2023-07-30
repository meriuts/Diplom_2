package site.stellarburgers.api.orders.body.response;

import java.util.List;

public class ListOrders {
    private boolean success;
    private List<Orders> orders;
    private String total;
    private String totalToday;

    public ListOrders(boolean success, List<Orders> orders, String total, String totalToday) {
        this.success = success;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }
    public ListOrders(){

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(String totalToday) {
        this.totalToday = totalToday;
    }
}
