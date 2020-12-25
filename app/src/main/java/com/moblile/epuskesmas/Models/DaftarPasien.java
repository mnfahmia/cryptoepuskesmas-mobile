package com.moblile.epuskesmas.Models;

import com.google.gson.annotations.SerializedName;

public class DaftarPasien {

    @SerializedName("pasien_ktp")
    private String ktpPasien;

    @SerializedName("pasien_nama")
    private String namaPasien ;

    @SerializedName("password")
    private String password;

    @SerializedName("pasien_goldar")
    private String goldarah;

    @SerializedName("pasien_alamat")
    private String alamatPasien;

    @SerializedName("pasien_tempatlahir")
    private String tempatLahirPasien;

    @SerializedName("pasien_telepon")
    private String teleponPasien;

    @SerializedName("pasien_jenkel")
    private String jenkelPasien;

    @SerializedName("pasien_agama")
    private String agamaPasien;

    @SerializedName("pasien_tgllahir")
    private long  tgl_lahir;

    public String getGoldarah() {
        return goldarah;
    }

    public void setGoldarah(String goldarah) {
        this.goldarah = goldarah;
    }

    public String getKtpPasien() {
        return ktpPasien;
    }

    public void setKtpPasien(String ktpPasien) {
        this.ktpPasien = ktpPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamatPasien() {
        return alamatPasien;
    }

    public void setAlamatPasien(String alamatPasien) {
        this.alamatPasien = alamatPasien;
    }

    public String getTempatLahirPasien() {
        return tempatLahirPasien;
    }

    public void setTempatLahirPasien(String tempatLahirPasien) {
        this.tempatLahirPasien = tempatLahirPasien;
    }

    public String getTeleponPasien() {
        return teleponPasien;
    }

    public void setTeleponPasien(String teleponPasien) {
        this.teleponPasien = teleponPasien;
    }

    public String getJenkelPasien() {
        return jenkelPasien;
    }

    public void setJenkelPasien(String jenkelPasien) {
        this.jenkelPasien = jenkelPasien;
    }

    public String getAgamaPasien() {
        return agamaPasien;
    }

    public void setAgamaPasien(String agamaPasien) {
        this.agamaPasien = agamaPasien;
    }

    public long getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(long tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }
}
