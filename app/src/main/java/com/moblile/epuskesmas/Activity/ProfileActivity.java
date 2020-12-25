package com.moblile.epuskesmas.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.moblile.epuskesmas.R;
import com.moblile.epuskesmas.RESTAPI.RestApi;
import com.moblile.epuskesmas.Response.Pasien;
import com.moblile.epuskesmas.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    CardView keluar;

    SessionManager mSession;
    RestApi Api = new RestApi();
    TextView nama,noktp,noasuransi,jenisasuransi;

    SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mSession = new SessionManager(getApplicationContext());

        keluar = findViewById(R.id.card_keluar);
        nama = findViewById(R.id.tv_nama);
        noktp = findViewById(R.id.tv_ktp);
        noasuransi = findViewById(R.id.tv_no_asuransi);
        jenisasuransi = findViewById(R.id.tv_jenisAsuransi);

        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.sr_pullToRefresh);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getprofile(mSession.getIdpasien());
                pullToRefresh.setRefreshing(false);
            }
        });
        getprofile(mSession.getIdpasien());


        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage("Apakah Anda Ingin Keluar");
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
                        mSession.logoutUser();
                        // Do nothing
//                    mSession.exit();
//                    System.exit(1);
//                    dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }


    private void getprofile(String id_pasien){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Melakukan Proses Data...");
        dialog.show();

        Api.getprofile(id_pasien, new Callback<Pasien>() {
            @Override
            public void onResponse(Call<Pasien> call, Response<Pasien> response) {
                dialog.dismiss();
                String message, statusCode;
                if(response.code() != 200){
                    message = "Server Not Respond";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                }else{

                    Pasien pass = new Pasien();
                    pass = response.body();

                    if(pass != null){
                        nama.setText(pass.getPasien().getNama_pasien());
                        noktp.setText(pass.getPasien().getPasien_ktp());
                        noasuransi.setText(pass.getPasien().getNo_Asuransi());
                        jenisasuransi.setText(pass.getPasien().getJenis_Asuransi());

                    }else{
                        nama.setText("-");
                        noktp.setText("-");
                        noasuransi.setText("-");
                        jenisasuransi.setText("-");

                    }
                }
            }

            @Override
            public void onFailure(Call<Pasien> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Err : " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("PROSES get Profile", "onFailure: " + t.getMessage());

            }
        });


    }

}



