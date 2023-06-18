package com.example.motorcycle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.motorcycle.API.APIRequestData;
import com.example.motorcycle.API.RetroServer;
import com.example.motorcycle.Model.ModelResponse;
import com.example.motorcycle.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private  String nama, merk, mesin, tahun, harga, foto;
    private EditText etNama, etMerk, etMesin, etTahun, etHarga, etFoto;
    private Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etMerk = findViewById(R.id.et_merk);
        etMesin =findViewById(R.id.et_mesin);
        etTahun = findViewById(R.id.et_tahun);
        etHarga = findViewById(R.id.et_harga);
        etFoto = findViewById(R.id.et_foto);


        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                merk = etMerk.getText().toString();
                mesin = etMesin.getText().toString();
                tahun = etTahun.getText().toString();
                harga = etHarga.getText().toString();
                foto = etFoto.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama Tidak Boleh Kosong!!!");
                }
                else if (merk.trim().isEmpty()){
                    etMerk.setError("Nama Merk Tidak Boleh Kosong!!!");
                }
                else if (mesin.trim().isEmpty()){
                    etMesin.setError("Nama Mesin Tidak Boleh Kosong!!!");
                }
                else if (tahun.trim().isEmpty()){
                    etTahun.setError("Tahun Tidak Boleh Kosong!!!");
                }
                else if (harga.trim().isEmpty()){
                    etHarga.setError("Harga Tidak Boleh Kosong!!!");
                } else if (foto.trim().isEmpty()){
                    etFoto.setError("Link Foto Tidak Boleh Kosong!!!");
                }
                else {
                    tambahMotor();
                }

            }
        });
    }
    private void tambahMotor() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(nama, merk, mesin, tahun, harga, foto); //Ambil dari Main Activity, Auto Import

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + ", Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
            }
        });
    }
}