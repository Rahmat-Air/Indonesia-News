package com.example.indonesianews.Fragments;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indonesianews.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TentangFargment extends Fragment {


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linkTentang)
    TextView link_tentang;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.versiAplikasi)
    TextView versi_aplikasi;

    public TentangFargment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tentang, container, false);

        ButterKnife.bind(this,view);

        try {
//            Mengambil Info Versi Aplikasi
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            String version = pInfo.versionName;

            versi_aplikasi.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        link_tentang.setText(": "+"https://newsapi.org/");
        Linkify.addLinks(link_tentang,Linkify.ALL);


        link_tentang.setMovementMethod(LinkMovementMethod.getInstance());



        return view;
    }


}