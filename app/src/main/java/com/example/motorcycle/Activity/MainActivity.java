package com.example.motorcycle.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.motorcycle.API.APIRequestData;
import com.example.motorcycle.API.RetroServer;
import com.example.motorcycle.Adapter.AdapterMotor;
import com.example.motorcycle.Model.ModelMotor;
import com.example.motorcycle.Model.ModelResponse;
import com.example.motorcycle.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMotor;
    private FloatingActionButton fabTambah;
    private ProgressBar pbMotor;
    private RecyclerView.Adapter adMotor;
    private RecyclerView.LayoutManager lmMotor;
    private List<ModelMotor> listMotor = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMotor =findViewById(R.id.rv_motor);
        fabTambah = findViewById(R.id.fab_tambah);
        pbMotor = findViewById(R.id.pb_motor);

        lmMotor =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMotor.setLayoutManager(lmMotor);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        retrieveMotor();
    }

    public void retrieveMotor (){
        pbMotor.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardRetrieve();
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode =response.body().getKode();
                String pesan =response.body().getPesan();
                listMotor = response.body().getData();
                adMotor =new AdapterMotor(MainActivity.this, listMotor);
                rvMotor.setAdapter(adMotor);

                pbMotor.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbMotor.setVisibility(View.GONE);
            }
        });

    }
}