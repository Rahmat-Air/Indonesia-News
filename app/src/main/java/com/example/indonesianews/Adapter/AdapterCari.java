package com.example.indonesianews.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indonesianews.Activies.DetailActivity;
import com.example.indonesianews.Model.BeritaModel;
import com.example.indonesianews.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCari extends RecyclerView.Adapter<AdapterCari.HolderData>
        implements Filterable {

    private List<BeritaModel> beritaModels;
    private List<BeritaModel> cariberita;
    private final Context context;
    private View view, kategori;

    private String judulBerita;
    private String descBerita;
    private String coverBerita;
    private String penulisBerita;
    private String tanggalberita;
    private String sumberBerita;
    private String isiBerita;

    public AdapterCari(Context context, List<BeritaModel> beritaModels) {
        this.beritaModels = beritaModels;
        this.cariberita = beritaModels;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori_layout, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterCari.HolderData holder, int position) {
        BeritaModel beritaModel = beritaModels.get(position);

        //        Mengambil Data dari Model
        judulBerita = beritaModel.getTitle();
        descBerita = beritaModel.getDescription();
        coverBerita = beritaModel.getUrlToImage();
        sumberBerita = beritaModel.getSource().getName();
        tanggalberita = beritaModel.getPublishedAt();
        penulisBerita = beritaModel.getAuthor();
        isiBerita = beritaModel.getContent();

        //        Menampilkan Data ke Layout
        holder.judul_berita.setText(judulBerita);
        holder.sumber_berita.setText(sumberBerita);
        holder.desc_berita.setText(descBerita);
        holder.tanggal_kategori.setText(tanggalberita);

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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String cari = constraint.toString();
                if (cari.isEmpty()) {
                    cariberita = beritaModels;
                } else {
                    List<BeritaModel> cariBeritaModels = new ArrayList();
                    for (BeritaModel row : beritaModels) {
                        if (row.getTitle().toLowerCase().contains(cari.toLowerCase())) {
                            cariBeritaModels.add(row);
                        }
                    }
                    cariberita = cariBeritaModels;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cariberita;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                cariberita = (ArrayList<BeritaModel>) results.values;
                notifyDataSetChanged();
            }
        };

    }

    public class HolderData extends RecyclerView.ViewHolder {

        //        Inisialisasi Id Layout
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.judul_kategori)
        TextView judul_berita;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.desc_kategori)
        TextView desc_berita;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.sumber_kategori)
        TextView sumber_berita;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.cover_kategori)
        ImageView cover_berita;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tanggal_kategori)
        TextView tanggal_kategori;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
