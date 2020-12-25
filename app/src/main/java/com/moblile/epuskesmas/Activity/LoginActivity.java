package com.moblile.epuskesmas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moblile.epuskesmas.R;
import com.moblile.epuskesmas.RESTAPI.RestApi;
import com.moblile.epuskesmas.Response.ResponseServer;
import com.moblile.epuskesmas.Session.SessionManager;
import com.moblile.epuskesmas.Utils.StickyService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView daftar;
    Button login;
    EditText nik,password;

    SessionManager mSession;

    RestApi api = new RestApi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mSession = new SessionManager(getApplicationContext());
        mSession.checkLogin();

        daftar = (TextView) findViewById(R.id.tv_daftar);
        login = findViewById(R.id.btn_login);
        nik = findViewById(R.id.tv_nik);
        password = findViewById(R.id.tv_password);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), DaftarActivity.class);
                startActivity(in);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nik.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "No KTP harus diisi", Toast.LENGTH_LONG).show();
                    return;
                } if(password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password harus diisi", Toast.LENGTH_LONG).show();
                    return;
                }

                login(nik.getText().toString(), password.getText().toString());
            }
        });
    }


    private void login (String no_ktp, String password){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Melakukan Proses Data...");
        dialog.show();


    api.LoginPasien(no_ktp, password, new Callback<ResponseServer>() {
        @Override
        public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
            dialog.dismiss();
            String message, statusCode;
            if (response.code() != 200)
            {
                message = "Server Not Respond";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
            else {

                message = response.body().getStatus();
                statusCode = response.body().getStatusCode();

                if (statusCode.equals("00"))
                {
                    String idpasien,noktp,idlogin;
                    idpasien = response.body().getLogin().getId_pasien();
                    noktp = response.body().getLogin().getNo_ktp();
                    idlogin = response.body().getLogin().getId_login();

                    Log.d("idpasien", idpasien);
                    Log.d("noktp", noktp);
                    Log.d("idlogin", idlogin);


                    doLogin(idpasien,idlogin,noktp);




                } else if(statusCode.equals("01")){

                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                }


            }

        }

        @Override
        public void onFailure(Call<ResponseServer> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Err : " + t.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("PROSES Login", "onFailure: " + t.getMessage());
        }
    });

    }



    private void doLogin(String idpasien, String idlogin, String noktp)
    {

        Log.d("idpasien1", idpasien);
        Log.d("noktp2", noktp);
        Log.d("idlogin3", idlogin);
        mSession.createLoginSession(idpasien, idlogin, noktp);
        mSession.checkLogin();
    }

    @Override
    public void onBackPressed() {
        //do your action
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah Anda ingin Keluar Dari Aplikasi ini ?");
        builder.setIcon(R.drawable.ic_undraw_questions_re_1fy7);

        builder.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Ya", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
             System.exit(1);
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }


}