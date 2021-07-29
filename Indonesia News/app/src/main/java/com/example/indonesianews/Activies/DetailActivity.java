package com.example.indonesianews.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.indonesianews.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.back)
    RelativeLayout back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.judul_detail)
    TextView judul_detail;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.shareBtn)
    RelativeLayout share_btn;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.webView)
    WebView webView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pbDetail)
    ProgressBar pbDetail;

    private String link, judulDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        back.setOnClickListener(v -> onBackPressed());

        terimaData();
        judul_detail.setText(judulDetail);
        tampilkanDetail();

        share_btn.setOnClickListener(v -> {
//            Aksi Tombol Share
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, link);
            startActivity(Intent.createChooser(shareIntent, "Bagikan..."));
        });

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void tampilkanDetail() {

//        Menampilkan Detail Berita dengan Webview dengan Cara Mengambil Url Link yang Dikirim Adapter
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);

        pbDetail.setVisibility(View.GONE);


    }

    private void terimaData() {

//        Menerima Data dari Adapter
        Intent intent = getIntent();

        link = intent.getStringExtra("link");
        judulDetail = intent.getStringExtra("judul");

    }

}