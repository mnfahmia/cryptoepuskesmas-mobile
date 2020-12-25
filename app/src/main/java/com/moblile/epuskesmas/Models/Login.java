package com.moblile.epuskesmas.Models;

import com.google.gson.annotations.SerializedName;

public class Login {


    @SerializedName("id_login")
    private String id_login;

    @SerializedName("id_pasien")
    private String id_pasien;

    @SerializedName("no_ktp")
    private String no_ktp;


    public String getId_login() {
        return id_login;
    }

    public void setId_login(String id_login) {
        this.id_login = id_login;
    }

    public String getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(String id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

}
