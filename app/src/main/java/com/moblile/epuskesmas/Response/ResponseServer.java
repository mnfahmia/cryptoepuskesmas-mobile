package com.moblile.epuskesmas.Response;
import com.google.gson.annotations.SerializedName;
import com.moblile.epuskesmas.Models.Login;


public class ResponseServer {

    @SerializedName("data")
    private Login login;

    @SerializedName("noantrian")
    private String noantrian;

    @SerializedName("totalantrian")
    private String totalantrian;

    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("status")
    private String status;

    public String getTotalantrian() {
        return totalantrian;
    }

    public void setTotalantrian(String totalantrian) {
        this.totalantrian = totalantrian;
    }

    public String getNoantrian() {
        return noantrian;
    }

    public void setNoantrian(String noantrian) {
        this.noantrian = noantrian;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
