package com.yarsi.leprosycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yarsi.leprosycare.content.EdukasiFragment;

import java.util.List;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Data data = (Data)i.getSerializableExtra("data");
        setContentView(R.layout.activity_content);
        TextView txt = findViewById(R.id.txt);
        TextView txt2 = findViewById(R.id.txt2);
        txt.setText(data.getIsi()[0]);
        txt2.setText(data.getIsi()[1]);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ContentActivity.this,MainActivity.class);
        startActivity(i);
    }
}
