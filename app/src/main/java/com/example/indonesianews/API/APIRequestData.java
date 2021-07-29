package com.example.indonesianews.API;

import com.example.indonesianews.Model.ResponeModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {

    String apiKey = "f4d2fc5acb02450285e50184fe4ac528";

    @GET("top-headlines?country=id&apiKey="+apiKey)
    Call<ResponeModel> ardTampilkanBerita();

    @GET("top-headlines?country=id&apiKey="+apiKey)
    Call<ResponeModel> ardKategori(@Query("category") String kategori);

    @GET("top-headlines?country=id&apiKey="+apiKey)
    Call<ResponeModel> ardCari(@Query("q") String cari);

}


