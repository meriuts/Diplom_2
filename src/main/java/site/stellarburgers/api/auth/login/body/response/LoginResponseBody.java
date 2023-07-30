package site.stellarburgers.api.auth.login.body.response;


public class LoginResponseBody {
    private boolean success;
    private LoginResponseUserBody loginResponseUserBody;
    private String accessToken;
    private String refreshToken;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LoginResponseUserBody getLoginResponseUserBody() {
        return loginResponseUserBody;
    }

    public void setLoginResponseUserBody(LoginResponseUserBody loginResponseUserBody) {
        this.loginResponseUserBody = loginResponseUserBody;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
