package com.moblile.epuskesmas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.moblile.epuskesmas.R;
import com.moblile.epuskesmas.Session.SessionManager;
import com.moblile.epuskesmas.Utils.StickyService;

public class DashboardActivity extends AppCompatActivity {

    LinearLayout profile,ambilticket,informasi;

    SessionManager mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mSession = new SessionManager(getApplicationContext());
        profile = findViewById(R.id.ll_profile);

        Intent stickyService = new Intent(this, StickyService.class);
        startService(stickyService);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });


        ambilticket = findViewById(R.id.ll_ambilticket);

        ambilticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });


        informasi = findViewById(R.id.ll_informasi);

        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void onBackPressed() {
        //do your action
        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
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
                mSession.exit();
                System.exit(1);
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }
}