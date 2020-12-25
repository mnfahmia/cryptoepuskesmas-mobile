package com.moblile.epuskesmas.RESTAPI;
import com.moblile.epuskesmas.Response.Pasien;
import com.moblile.epuskesmas.Response.ResponseServer;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConsumeApi {

    @POST("daftar")
    Call<ResponseServer> daftar(@Body Map<String, Object> request);


    @POST("login")
    Call<ResponseServer> login(@Body Map<String, Object> request);

    @POST("dapatkanantrian")
    Call<ResponseServer> dapatkanantrian(@Body Map<String, Object> request);

    @GET("noantrian/{id}")
    Call<ResponseServer> getnoantrian(@Path("id") String id);

    @DELETE("noantrian/{id}")
    Call<ResponseServer> deletenoantrian(@Path("id") String id);

    @GET("pasien/{id}")
    Call<Pasien> getprofile(@Path("id") String id);
}
