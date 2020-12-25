package com.moblile.epuskesmas.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.moblile.epuskesmas.Models.DaftarPasien;
import com.moblile.epuskesmas.R;
import com.moblile.epuskesmas.RESTAPI.RestApi;
import com.moblile.epuskesmas.Response.ResponseServer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DaftarActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    TextView tglLharir;
    TextView tvagama;
    TextView tvjenkel;
    TextView goldarah;
    Button submit;
    EditText nik,password,nama,alamat, notlp,tempatlahir;
    RestApi api = new RestApi();

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tglLharir.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    public  void AgamalistDialog()
    {
        // setup alert builder

        AlertDialog.Builder builder = new AlertDialog.Builder(DaftarActivity.this);
        builder.setTitle("Pilih Agama");
        // buat radio button list
        final String[] selectedoption = {"Islam", "Protestan", "Katolik", "Hindu", "Buddha", "Khonghucu"};
        int checkedItem = 0; // cow
        builder.setSingleChoiceItems(selectedoption, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user check item
//                Toast.makeText(DaftarActivity.this,"selected option: "+selectedoption[which],Toast.LENGTH_LONG).show();
                //di sini 'which' adalah posisi yang dipilih

            }
        });
        // tambah tombol OK dan Cancel
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                tvagama.setText(selectedoption[selectedPosition]);
//                System.out.println("ini :" +selectedoption[selectedPosition]);
            }
        });
        builder.setNegativeButton("Cancel", null);
        // buat dan tampilkan alert dialog

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void jeniskelamindialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(DaftarActivity.this);
        builder.setTitle("Pilih Jenis Kelamin");
        // buat radio button list
        final String[] selectedoption = {"Laki - Laki", "Perempuan"};
        int checkedItem = 0; // cow
        builder.setSingleChoiceItems(selectedoption, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user check item
//                Toast.makeText(DaftarActivity.this,"selected option: "+selectedoption[which],Toast.LENGTH_LONG).show();
                //di sini 'which' adalah posisi yang dipilih

            }
        });
        // tambah tombol OK dan Cancel
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                tvjenkel.setText(selectedoption[selectedPosition]);
//                System.out.println("ini :" +selectedoption[selectedPosition]);
            }
        });
        builder.setNegativeButton("Cancel", null);
        // buat dan tampilkan alert dialog

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void golongandarahdialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(DaftarActivity.this);
        builder.setTitle("Golongan Darah");
        // buat radio button list
        final String[] selectedoption = {"A", "B", "AB", "0", "Belum Diketahui"};
        int checkedItem = 0; // cow
        builder.setSingleChoiceItems(selectedoption, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user check item
//                Toast.makeText(DaftarActivity.this,"selected option: "+selectedoption[which],Toast.LENGTH_LONG).show();
                //di sini 'which' adalah posisi yang dipilih

            }
        });
        // tambah tombol OK dan Cancel
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                goldarah.setText(selectedoption[selectedPosition]);
//                System.out.println("ini :" +selectedoption[selectedPosition]);
            }
        });
        builder.setNegativeButton("Cancel", null);
        // buat dan tampilkan alert dialog

        AlertDialog dialog = builder.create();
        dialog.show();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        final DaftarPasien daf = new DaftarPasien();
        nik = (EditText) findViewById(R.id.tv_nik);
        password = (EditText) findViewById(R.id.tv_password);
        nama = (EditText) findViewById(R.id.tv_nama);
        alamat = (EditText) findViewById(R.id.tv_alamat);
        notlp = (EditText) findViewById(R.id.tv_NoTlp);
        tempatlahir = (EditText) findViewById(R.id.tempatlahir);
        submit = (Button) findViewById(R.id.submit);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tglLharir = findViewById(R.id.tv_tgllahir);

        tglLharir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });


        tvagama = findViewById(R.id.tv_agama);
        tvagama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgamalistDialog();
            }
        });

        tvjenkel = findViewById(R.id.tv_jenkel);

        tvjenkel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jeniskelamindialog();
            }
        });
        goldarah = findViewById(R.id.tv_golongandarah);
        goldarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                golongandarahdialog();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nik.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "No KTP harus diisi", Toast.LENGTH_LONG).show();
                    return;
                } if(password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password harus diisi", Toast.LENGTH_LONG).show();
                    return;
                } if(nama.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama harus diisi", Toast.LENGTH_LONG).show();
                    return;
                } if(goldarah.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Goldarah harus diisi", Toast.LENGTH_LONG).show();
                    return;
                } if(tglLharir.getText().toString().equals("--Pilih--")){
                    Toast.makeText(getApplicationContext(), "TglLharir harus di pilih", Toast.LENGTH_LONG).show();
                    return;
                } if(tvagama.getText().toString().equals("--Pilih--")){
                    Toast.makeText(getApplicationContext(), "Agama harus di pilih", Toast.LENGTH_LONG).show();
                    return;
                } if(tvjenkel.getText().toString().equals("--Pilih--")){
                    Toast.makeText(getApplicationContext(), "Jenis Kelamin harus di pilih", Toast.LENGTH_LONG).show();
                    return;
                } if(notlp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "No Tlp harus diisi", Toast.LENGTH_LONG).show();
                    return;
                } if(tempatlahir.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Tempat Lahir harus diisi", Toast.LENGTH_LONG).show();
                    return;
                }if(alamat.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Alamat harus diisi", Toast.LENGTH_LONG).show();
                    return;
                } if(goldarah.getText().toString().equals("--Pilih--")){
                    Toast.makeText(getApplicationContext(), "Golongan Darah harus di pilih", Toast.LENGTH_LONG).show();
                    return;
                }

                String tanggal = tglLharir.getText().toString();

                daf.setKtpPasien(nik.getText().toString());
                daf.setNamaPasien(nama.getText().toString());
                daf.setPassword(password.getText().toString());
                daf.setGoldarah(goldarah.getText().toString());
                daf.setAlamatPasien(alamat.getText().toString());
                daf.setTempatLahirPasien(tempatlahir.getText().toString());
                daf.setTeleponPasien(notlp.getText().toString());
                daf.setJenkelPasien(tvjenkel.getText().toString());
                daf.setAgamaPasien(tvagama.getText().toString());
                daf.setGoldarah(goldarah.getText().toString());
                long tanggal_lahir = convertdattolong(tanggal);
                daf.setTgl_lahir(tanggal_lahir);
                daftar(daf);

            }
        });
    }


    private void daftar(final DaftarPasien daf){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Melakukan Proses Data...");
        dialog.show();

    api.daftarpasien(daf, new Callback<ResponseServer>() {
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

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);



                } else if (statusCode.equals("02")) {

                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }


            }
        }

        @Override
        public void onFailure(Call<ResponseServer> call, Throwable t) {
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "Err : " + t.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("PROSES Daftar", "onFailure: " + t.getMessage());
        }
    });

    }



    private long convertdattolong (String tanggalDate)  {
            try {
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                // Convert date to Long (timestamp)
                Date d = f.parse(tanggalDate);
                long milliseconds = d.getTime();
                // Convert selesai

                return milliseconds;
            }catch (Exception e){
                e.printStackTrace();
                long milliseconds = 0;
                return milliseconds;

            }

     }
}