package site.stellarburgers.api.auth.register.body.response;



public class RegisterResponseBody {
    private boolean success;
    private RegisterResponseUserBody registerResponseUserBody;
    private String accessToken;
    private String refreshToken;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RegisterResponseUserBody getRegisterResponseUserBody() {
        return registerResponseUserBody;
    }

    public void setRegisterResponseUserBody(RegisterResponseUserBody registerResponseUserBody) {
        this.registerResponseUserBody = registerResponseUserBody;
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
