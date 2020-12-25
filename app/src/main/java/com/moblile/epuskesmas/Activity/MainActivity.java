package com.moblile.epuskesmas.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class MainActivity extends AppCompatActivity {
    SessionManager mSession;
    RestApi Api = new RestApi();
    LinearLayout llambil;


    SwipeRefreshLayout pullToRefresh;

    TextView noantrian,totalantrian, tvambil_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.sr_pullToRefresh);



    llambil = findViewById(R.id.ll_ambilno);


        Intent stickyService = new Intent(this, StickyService.class);
        startService(stickyService);

        noantrian = findViewById(R.id.tv_noantrian);
        totalantrian = findViewById(R.id.tv_jumlahAntrian);
        tvambil_no = findViewById(R.id.tv_ambilno);

        mSession = new SessionManager(getApplicationContext());

        getantrian(mSession.getIdlogin());

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getantrian(mSession.getIdlogin());
                pullToRefresh.setRefreshing(false);
            }
        });



        llambil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ceknoantrian();
            }
        });




    }






private void getantrian(String id_login){
        Api.getAntrian(id_login, new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                String message, statusCode;
                if (response.code() != 200)
                {
                    message = "Server Not Respond";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }else{

                    message = response.body().getStatus();
                    statusCode = response.body().getStatusCode();
                    if (statusCode.equals("00")) {
                        // Berhasil simpan Data

                        noantrian.setText("A : " + response.body().getNoantrian());
                        totalantrian.setText(response.body().getTotalantrian());
                        tvambil_no.setText("Batalkan Nomor Antrian");

                    } else if (statusCode.equals("01")) {

                        noantrian.setText("Anda Belum Memiliki No Antrian ");
                        totalantrian.setText(response.body().getTotalantrian());
                        noantrian.setTextSize(14);
                        tvambil_no.setText("Ambil Nomor Antrian");
                    }


                }

            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Err : " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("PROSES Daftar", "onFailure: " + t.getMessage());

            }
        });

}



    private void ceknoantrian(){
        Log.d("no antrian", "antrian: " +noantrian );
        if(noantrian.getText().toString().equals("Anda Belum Memiliki No Antrian ")){
//            int antrian = Integer.parseInt(totalantrian.getText().toString());
//            int ambilno = antrian+1;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Informasi");
            builder.setMessage("Setuju Ingin Mengambil No Antrian");
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
                    dapatkanantrian(mSession.getIdlogin());
                    // Do nothing
//                    mSession.exit();
//                    System.exit(1);
//                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Informasi");
            builder.setMessage(" Apakah Anda Yakin Ingin Membatalkan No Antrian ");
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

                    deleteantrian(mSession.getIdlogin());
//                    dialog.dismiss();
                }
            });


            AlertDialog alert = builder.create();
            alert.show();
        }

    }



    private void deleteantrian(String id_login){

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Melakukan Proses Data...");
        dialog.show();

        Api.deleteantrian(id_login, new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                Log.d("ini" ,"ini resonse :" + response );
                dialog.dismiss();
                String message, statusCode;
                if (response.code() != 200)
                {
                    message = "Server Not Respond";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }else{

                    message = response.body().getStatus();
                    statusCode = response.body().getStatusCode();
                    if (statusCode.equals("00")) {
                        // Berhasil simpan Data
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);



                    } else if (statusCode.equals("01")) {

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }


                }

            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Err : " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("PROSES Daftar", "onFailure: " + t.getMessage());
            }
        });

    }


    private void dapatkanantrian(String id_login){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Melakukan Proses Data...");
        dialog.show();


        Api.dapatkanantrian(id_login, new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {

                dialog.dismiss();
                String message, statusCode;
                if (response.code() != 200)
                {
                    message = "Server Not Respond";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }else{

                    message = response.body().getStatus();
                    statusCode = response.body().getStatusCode();
                    if (statusCode.equals("00")) {
                        // Berhasil simpan Data
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);



                    } else if (statusCode.equals("01")) {

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }


                }

            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Err : " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("PROSES Daftar", "onFailure: " + t.getMessage());

            }
        });


    }






}