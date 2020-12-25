package com.moblile.epuskesmas.Response;

import com.google.gson.annotations.SerializedName;

public class Pasien {

    @SerializedName("pasien")
    private DataPasien pasien;

    public DataPasien getPasien() {
        return pasien;
    }

    public void setPasien(DataPasien pasien) {
        this.pasien = pasien;
    }
}
