package com.example.motorcycle.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorcycle.API.APIRequestData;
import com.example.motorcycle.API.RetroServer;
import com.example.motorcycle.Activity.DetailActivity;
import com.example.motorcycle.Activity.MainActivity;
import com.example.motorcycle.Activity.UbahActivity;
import com.example.motorcycle.Model.ModelMotor;
import com.example.motorcycle.Model.ModelResponse;
import com.example.motorcycle.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMotor extends RecyclerView.Adapter<AdapterMotor.VHMotor> {
    private Context ctx;
    private List<ModelMotor> listMotor;

    public AdapterMotor(Context ctx, List<ModelMotor> listMotor) {
        this.ctx = ctx;
        this.listMotor = listMotor;
    }

    @NonNull
    @Override
    public VHMotor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_motor,parent,false);
        return new VHMotor(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHMotor holder, int position) {
        ModelMotor MM = listMotor.get(position);
        holder.tvId.setText(MM.getId());
        holder.tvNama.setText(MM.getNama());
        holder.tvMerk.setText(MM.getMerk());
        holder.tvMesin.setText(MM.getMesin());
        holder.tvTahun.setText(MM.getTahun());
        holder.tvHarga.setText(MM.getHarga());
        Picasso.get().load(MM.getFoto()).into(holder.ivFoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ctx, DetailActivity.class);
                pindah.putExtra("xId", MM.getId());
                pindah.putExtra("xNama", MM.getNama());
                pindah.putExtra("xMerk", MM.getMerk());
                pindah.putExtra("xMesin", MM.getMesin());
                pindah.putExtra("xTahun", MM.getTahun());
                pindah.putExtra("xHarga", MM.getHarga());
                pindah.putExtra("xFoto", MM.getFoto());
                ctx.startActivity(pindah);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);

                pesan.setTitle("Perhatian!!");
                pesan.setMessage("Operasi yang akan dilakukan?");
                pesan.setCancelable(true);

                pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
                        Call<ModelResponse> proses = ARD.ardDelete(MM.getId());
                        proses.enqueue(new Callback<ModelResponse>() {
                            @Override
                            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                                String kode = response.body().getKode();
                                String pesan = response.body().getPesan();

                                Toast.makeText(ctx, "Kode: " + kode + ", Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                                ((MainActivity)ctx).retrieveMotor();
                            }

                            @Override
                            public void onFailure(Call<ModelResponse> call, Throwable t) {
                                Toast.makeText(ctx, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialog.dismiss();
                    }
                });
                pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent pindah = new Intent(ctx, UbahActivity.class);
                        pindah.putExtra("xId", MM.getId());
                        pindah.putExtra("xNama", MM.getNama());
                        pindah.putExtra("xMerk", MM.getMerk());
                        pindah.putExtra("xMesin", MM.getMesin());
                        pindah.putExtra("xTahun", MM.getTahun());
                        pindah.putExtra("xHarga", MM.getHarga());
                        pindah.putExtra("xFoto", MM.getFoto());
                        ctx.startActivity(pindah);
                    }
                });
                pesan.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMotor.size();
    }


    public class VHMotor extends RecyclerView.ViewHolder {
        TextView tvId,tvNama, tvMerk, tvMesin, tvTahun, tvHarga;
        ImageView ivFoto;
        public VHMotor(@NonNull View itemView) {
            super(itemView);
            tvId =itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvMerk = itemView.findViewById(R.id.tv_merk);
            tvMesin = itemView.findViewById(R.id.tv_mesin);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            ivFoto = itemView.findViewById(R.id.iv_foto);


        }
    }
}
