package com.example.indonesianews.Fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.indonesianews.API.APIRequestData;
import com.example.indonesianews.API.RetrofitServer;
import com.example.indonesianews.Adapter.AdapterBerita;
import com.example.indonesianews.Adapter.AdapterKategori;
import com.example.indonesianews.Model.BeritaModel;
import com.example.indonesianews.Model.ResponeModel;
import com.example.indonesianews.R;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiscoverFragment extends Fragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvBerita)
    RecyclerView rvBerita;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvKategori)
    RecyclerView rvKategori;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pbDiscover)
    ProgressBar progressBar;

    private RecyclerView.Adapter adapterBerita;
    private AdapterKategori adapterKategori;
    private RecyclerView.LayoutManager layoutManager;
    private List<BeritaModel> beritaModels = new ArrayList<>();

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        ButterKnife.bind(this, view);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        tampilkanBerita();
        tampilkanKategori("bisnis");

        tabLayout.addTab(tabLayout.newTab().setText("BISNIS"));
        tabLayout.addTab(tabLayout.newTab().setText("HIBURAN"));
        tabLayout.addTab(tabLayout.newTab().setText("KESEHATAN"));
        tabLayout.addTab(tabLayout.newTab().setText("PENGETAHUAN"));
        tabLayout.addTab(tabLayout.newTab().setText("OLAHRAGA"));
        tabLayout.addTab(tabLayout.newTab().setText("TEKNOLOGI"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                String kategori = tabLayout.getTabAt(tab.getPosition()).getText().toString().toLowerCase();
                progressBar.setVisibility(View.VISIBLE);

                tampilkanKategori(kategori);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                String kategori = tab.getText().toString();

                Toast.makeText(getContext(), "" + kategori, Toast.LENGTH_SHORT).show();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                swipeRefreshLayout.setRefreshing(false);

                tampilkanBerita();
                tampilkanKategori("bisnis");
            }, 5000);

        });


        return view;
    }

    private void tampilkanKategori(String kategori) {

        String kategoriBerita = kategori;

        if (kategori.equals("bisnis")) {
            kategoriBerita = kategori.replace("bisnis", "business");

        } else if (kategori.equals("hiburan")) {
            kategoriBerita = kategori.replace("hiburan", "entertainment");
        } else if (kategori.equals("kesehatan")) {
            kategoriBerita = kategori.replace("kesehatan", "health");
        } else if (kategori.equals("pengetahuan")) {
            kategoriBerita = kategori.replace("pengetahuan", "science");
        } else if (kategori.equals("olahraga")) {
            kategoriBerita = kategori.replace("olahraga", "sport");
        } else if (kategori.equals("teknologi")) {
            kategoriBerita = kategori.replace("teknologi", "technology");
        }
        APIRequestData kategoriApi = RetrofitServer.konekRetrofit().create(APIRequestData.class);

        Call<ResponeModel> tampilKategori = kategoriApi.ardKategori(kategoriBerita);

        tampilKategori.enqueue(new Callback<ResponeModel>() {
            @Override
            public void onResponse(Call<ResponeModel> call, Response<ResponeModel> response) {

                assert response.body() != null;
                int kode = response.body().getTotalResult();
                String pesan = response.body().getStatus();

                beritaModels = response.body().getArticles();

                adapterKategori = new AdapterKategori(getContext(), beritaModels);
                rvKategori.setAdapter(adapterKategori);
                adapterKategori.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponeModel> call, Throwable t) {
                Toast.makeText(getContext(), "gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void tampilkanBerita() {

        APIRequestData beritaApi = RetrofitServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponeModel> tampilBerita = beritaApi.ardTampilkanBerita();

        tampilBerita.enqueue(new Callback<ResponeModel>() {
            @Override
            public void onResponse(Call<ResponeModel> call, Response<ResponeModel> response) {
                int kode = response.body().getTotalResult();
                String pesan = response.body().getStatus();

                beritaModels = response.body().getArticles();

                adapterBerita = new AdapterBerita(getContext(), beritaModels);
                rvBerita.setAdapter(adapterBerita);
                adapterBerita.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ResponeModel> call, Throwable t) {
                Log.d("gagal", t.getMessage());
                Toast.makeText(getContext(), "gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
