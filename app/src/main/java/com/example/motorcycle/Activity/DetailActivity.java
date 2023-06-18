package com.example.motorcycle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.motorcycle.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView tvId, tvNama, tvMerk, tvMesin, tvTahun, tvHarga;
    ImageView ivFoto;
    private String yId, yNama, yMerk, yMesin, yTahun, yHarga, yFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yMerk = ambil.getStringExtra("xMerk");
        yMesin = ambil.getStringExtra("xMesin");
        yTahun = ambil.getStringExtra("xTahun");
        yHarga = ambil.getStringExtra("xHarga");
        yFoto = ambil.getStringExtra("xFoto");

        tvId = findViewById(R.id.tv_id);
        tvNama = findViewById(R.id.tv_nama);
        tvMerk = findViewById(R.id.tv_merk);
        tvMesin = findViewById(R.id.tv_mesin);
        tvTahun = findViewById(R.id.tv_tahun);
        tvHarga = findViewById(R.id.tv_harga);
        ivFoto = findViewById(R.id.iv_foto);

        tvNama.setText(yNama);
        tvMerk.setText(yMerk);
        tvMesin.setText(yMesin);
        tvTahun.setText(yTahun);
        tvHarga.setText(yHarga);
        Picasso.get().load(yFoto).into(ivFoto);




    }
}