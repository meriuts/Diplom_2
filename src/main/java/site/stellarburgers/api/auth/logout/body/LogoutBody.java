package site.stellarburgers.api.auth.logout.body;

public class LogoutBody {
    private String token;

    public LogoutBody(String token) {
        this.token = token;
    }

    public LogoutBody() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
