package com.yarsi.leprosycare.content;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yarsi.leprosycare.Alarm;
import com.yarsi.leprosycare.DBPerawatan;
import com.yarsi.leprosycare.DataAlarm;
import com.yarsi.leprosycare.R;
import com.yarsi.leprosycare.RecyclerViewAdapter;
import java.util.ArrayList;


public class AlarmFragment extends Fragment implements View.OnClickListener{
    private DBPerawatan dbPerawatan;
    private RecyclerView rvView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DataAlarm> dataSet;
    private FloatingActionButton fab;
    protected Context context;

    @Override
    public void onResume() {
        super.onResume();
        adapter.clear();
        initDataSet();
        //Memasang Adapter pada RecyclerView
        dbPerawatan = new DBPerawatan(getActivity());
        //dataSet = dbPerawatan.getSemuaData();
        adapter = new RecyclerViewAdapter(dataSet);
        rvView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        rvView = view.findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        adapter = new RecyclerViewAdapter();

        rvView.setLayoutManager(layoutManager);
        rvView.setAdapter(adapter);

        dataSet = new ArrayList<>();
        dbPerawatan = new DBPerawatan(getActivity().getBaseContext());
        initDataSet();
        //Memasang Adapter pada RecyclerView
        dbPerawatan = new DBPerawatan(getActivity());
        //dataSet = dbPerawatan.getSemuaData();
        adapter = new RecyclerViewAdapter(dataSet);
        rvView.setAdapter(adapter);
        fab  = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return view;
    }

    private void initDataSet() {
        //mengambil repository dengan membaca
        SQLiteDatabase readData = dbPerawatan.getReadableDatabase();
        Cursor cursor = readData.rawQuery("SELECT * FROM "+ DBPerawatan.MyColums.namaTabel, null);
        cursor.moveToFirst();

        for (int count=0; count<cursor.getCount(); count++){
            cursor.moveToPosition(count);

            dataSet.add( new DataAlarm(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3)+"",cursor.getString(4),cursor.getString(5),
                    cursor.getString(6),cursor.getString(7),cursor.getString(8),
                    cursor.getString(9)));
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getActivity(), Alarm.class);
        switch (v.getId()){
            case R.id.fab:
                startActivity(i);
                break;
        }
    }

}
