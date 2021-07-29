package com.example.indonesianews.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indonesianews.Activies.DetailActivity;
import com.example.indonesianews.Model.BeritaModel;
import com.example.indonesianews.Model.Source;
import com.example.indonesianews.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.HolderData> {

    private List<BeritaModel> beritaModels;
    private final Context context;
    private View view, kategori;

    private String judulBerita;
    private String descBerita;
    private String coverBerita;
    private String penulisBerita;
    private String tanggalberita;
    private String sumberBerita;
    private String linkBerita;
    private String isiBerita;

    public AdapterBerita(Context context, List<BeritaModel> beritaModels) {
        this.beritaModels = beritaModels;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.berita_layout, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull AdapterBerita.HolderData holder, int position) {
        BeritaModel beritaModel = beritaModels.get(position);

//        Mengambil Data dari Model
        judulBerita = beritaModel.getTitle();
        descBerita = beritaModel.getDescription();
        coverBerita = beritaModel.getUrlToImage();
        sumberBerita = beritaModel.getSource().getName();
        tanggalberita = beritaModel.getPublishedAt();
        penulisBerita = beritaModel.getAuthor();
        linkBerita = beritaModel.getUrl();
        isiBerita = beritaModel.getContent();

//        Menampilkan Data ke Layout
        holder.judul_berita.setText(judulBerita);
        holder.sumber_berita.setText(sumberBerita);
        holder.desc_berita.setText(descBerita);
        holder.tanggal_berita.setText(tanggalberita);

        Picasso.get().load(coverBerita).into(holder.cover_berita);

        holder.itemView.setOnClickListener(v -> {
//            Berpindah Activity Sekaligus Mengirim Data
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("link", beritaModel.getUrl());
            intent.putExtra("judul", beritaModel.getTitle());

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
//        Jumlah Data dari Model
        return beritaModels.size();
    }

    public static class HolderData extends RecyclerView.ViewHolder {

//        Inisialisasi Id Layout

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.judul_berita)
        TextView judul_berita;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.desc_berita)
        TextView desc_berita;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.sumber_berita)
        TextView sumber_berita;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.cover_berita)
        ImageView cover_berita;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tanggal_berita)
        TextView tanggal_berita;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
