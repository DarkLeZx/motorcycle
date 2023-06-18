package com.example.motorcycle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.motorcycle.R;

public class AboutMe extends AppCompatActivity {
    private ImageView ivFotoDepniel, ivMdp;
    private TextView namaDepniel,tvJudulAboutMe, tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("ABOUT ME");
        setContentView(R.layout.activity_about_me);

        ivFotoDepniel = findViewById(R.id.iv_foto_depniel);
        ivMdp = findViewById(R.id.iv_mdp);

        namaDepniel =findViewById(R.id.nama_depniel);

        tvJudulAboutMe = findViewById(R.id.tv_judul_aboutMe);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
    }
}