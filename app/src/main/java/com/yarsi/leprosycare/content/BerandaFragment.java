package com.yarsi.leprosycare.content;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yarsi.leprosycare.DBUser;
import com.yarsi.leprosycare.DataUser;
import com.yarsi.leprosycare.R;

import java.io.ByteArrayInputStream;

public class BerandaFragment extends Fragment {
    ImageView imgView;
    TextView txtNamauser,txtPoin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        imgView = view.findViewById(R.id.imgView);
        txtNamauser = view.findViewById(R.id.namauser);
        txtPoin = view.findViewById(R.id.poin);
        /*Bundle bundle = getArguments();
        DataUser dtuser = (DataUser)bundle.getSerializable("DataUser");
        imgView.setImageBitmap(dtuser.getBitmap());
        txtNamauser.setText(dtuser.getNama());
        txtPoin.setText(dtuser.getPoin());*/
        return view;
    }
}
