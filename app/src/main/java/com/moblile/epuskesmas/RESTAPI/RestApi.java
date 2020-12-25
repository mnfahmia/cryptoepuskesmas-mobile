package com.moblile.epuskesmas.RESTAPI;

import android.util.Log;

import com.moblile.epuskesmas.Models.DaftarPasien;
import com.moblile.epuskesmas.Response.Pasien;
import com.moblile.epuskesmas.Response.ResponseServer;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moblile.epuskesmas.Utils.Constans.Base_URL;


public class RestApi {

    public void daftarpasien (DaftarPasien daf, Callback<ResponseServer> callback){

        String baseURL = Base_URL+"/service/pasien/";
        Log.e("URL", "POSTDATA : " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsumeApi service = retrofit.create(ConsumeApi.class);
        final Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("data", daf);
        Call<ResponseServer> result = service.daftar(mapData);
        result.enqueue(callback);

    }

    public void LoginPasien (String no_ktp, String Password, Callback<ResponseServer> callback){


        String baseURL = Base_URL+"/service/pasien/";
        Log.e("URL", "POSTDATA : " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsumeApi service = retrofit.create(ConsumeApi.class);

        final Map<String, String> isiData = new HashMap<String, String>();
        isiData.put("pasien_ktp", no_ktp);
        isiData.put("password", Password);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", isiData);

        Call<ResponseServer> result = service.login(data);
        result.enqueue(callback);

    }


    public void getAntrian(String id_login, Callback<ResponseServer> callback){
        String baseURL = Base_URL+"/service/pasien/";
        Log.e("URL", "GETDATA : " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsumeApi service = retrofit.create(ConsumeApi.class);
        Call<ResponseServer> result = service.getnoantrian(id_login);
        result.enqueue(callback);

    }


    public void deleteantrian(String id_login, Callback<ResponseServer> callback){

        String baseURL = Base_URL+"/service/pasien/";
        Log.e("URL", "POSTDATA : " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsumeApi service = retrofit.create(ConsumeApi.class);
        Call<ResponseServer> result = service.deletenoantrian(id_login);
        result.enqueue(callback);

    }

    public void dapatkanantrian(String id_login, Callback<ResponseServer> callback){
        String baseURL = Base_URL+"/service/pasien/";
        Log.e("URL", "POSTDATA : " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsumeApi service = retrofit.create(ConsumeApi.class);

        final Map<String, String> isiData = new HashMap<String, String>();
        isiData.put("id_login", id_login);


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", isiData);

        Call<ResponseServer> result = service.dapatkanantrian(data);
        result.enqueue(callback);

    }

    public void getprofile(String id_pasien, Callback<Pasien> callback){
        String baseURL = Base_URL+"/service/masterdata/";
        Log.e("URL", "GETDATA : " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsumeApi service = retrofit.create(ConsumeApi.class);
        Call<Pasien> result = service.getprofile(id_pasien);
        result.enqueue(callback);

    }
}
