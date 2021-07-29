package com.example.indonesianews.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.indonesianews.API.APIRequestData;
import com.example.indonesianews.API.RetrofitServer;
import com.example.indonesianews.Adapter.AdapterBerita;
import com.example.indonesianews.Adapter.AdapterCari;
import com.example.indonesianews.Model.BeritaModel;
import com.example.indonesianews.Model.ResponeModel;
import com.example.indonesianews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CariFragment extends Fragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cariBerita2)
    SearchView cariBerita2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvCari)
    RecyclerView rvCari;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pgBar)
    ProgressBar progressBar;

    private RecyclerView.LayoutManager layoutManager;
    private List<BeritaModel> beritaModels = new ArrayList<>();
    AdapterCari adapterCari;



    public CariFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cari, container, false);
        ButterKnife.bind(this, view);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        tampilkanBerita("");
        cariBerita2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tampilkanBerita(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tampilkanBerita(newText);
                return false;
            }
        });

        return view;
    }

    private void tampilkanBerita(String cari) {


        APIRequestData beritaApi = RetrofitServer.konekRetrofit().create(APIRequestData.class);

        Call<ResponeModel> tampilBerita = beritaApi.ardCari(cari);

        tampilBerita.enqueue(new Callback<ResponeModel>() {
            @Override
            public void onResponse(Call<ResponeModel> call, Response<ResponeModel> response) {
                int kode = response.body().getTotalResult();
                String pesan = response.body().getStatus();

                beritaModels = response.body().getArticles();
                adapterCari = new AdapterCari(getContext(), beritaModels);
                rvCari.setAdapter(adapterCari);
                adapterCari.notifyDataSetChanged();
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