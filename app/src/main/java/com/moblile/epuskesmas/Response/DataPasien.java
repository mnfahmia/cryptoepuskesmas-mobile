package com.moblile.epuskesmas.Response;

import com.google.gson.annotations.SerializedName;

public class DataPasien {



    @SerializedName("pasien_ktp")
    private String pasien_ktp;

    @SerializedName("pasien_nama")
    private String nama_pasien;

    @SerializedName("pasien_noasuransi")
    private String No_Asuransi;

    @SerializedName("pasien_jenisasuransi")
    private String jenis_Asuransi;


    public String getPasien_ktp() {
        return pasien_ktp;
    }

    public void setPasien_ktp(String pasien_ktp) {
        this.pasien_ktp = pasien_ktp;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public String getNo_Asuransi() {
        return No_Asuransi;
    }

    public void setNo_Asuransi(String no_Asuransi) {
        No_Asuransi = no_Asuransi;
    }

    public String getJenis_Asuransi() {
        return jenis_Asuransi;
    }

    public void setJenis_Asuransi(String jenis_Asuransi) {
        this.jenis_Asuransi = jenis_Asuransi;
    }
}
