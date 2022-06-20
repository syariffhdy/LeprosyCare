package com.yarsi.leprosycare.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yarsi.leprosycare.ContentActivity;
import com.yarsi.leprosycare.Data;
import com.yarsi.leprosycare.R;

import java.io.Serializable;

public class EdukasiFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout definisikusta,penyebabkusta,tandagejalakusta,tipekusta,pengobatanpenyakitkusta,faq;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edukasi, container, false);
        definisikusta = view.findViewById(R.id.definisikusta);
        penyebabkusta = view.findViewById(R.id.penyebabkusta);
        tandagejalakusta = view.findViewById(R.id.tandagejalakusta);
        tipekusta = view.findViewById(R.id.tipekusta);
        pengobatanpenyakitkusta = view.findViewById(R.id.pengobatanpenyakitkusta);
        faq = view.findViewById(R.id.faq);
        definisikusta.setOnClickListener(this);
        penyebabkusta.setOnClickListener(this);
        tandagejalakusta.setOnClickListener(this);
        tipekusta.setOnClickListener(this);
        pengobatanpenyakitkusta.setOnClickListener(this);
        faq.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View view){
        Intent i = new Intent(getActivity(), ContentActivity.class);
        switch (view.getId()){
            case R.id.definisikusta:
                String isi[] = getResources().getStringArray(R.array.defkusta);
                Data data = new Data("Definisi Kusta",isi);
                i.putExtra("data",data);
                startActivity(i);
                break;
            case R.id.penyebabkusta:
                isi = getResources().getStringArray(R.array.penykusta);
                data = new Data("Penyebab Kusta",isi);
                i.putExtra("data",data);
                startActivity(i);
                break;
            case R.id.tandagejalakusta:
                break;
        }
    }
}
