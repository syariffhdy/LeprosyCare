package com.yarsi.leprosycare;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.yarsi.leprosycare.content.AlarmFragment;
import com.yarsi.leprosycare.content.BerandaFragment;
import com.yarsi.leprosycare.content.EdukasiFragment;
import com.yarsi.leprosycare.content.FormFragment;
import com.yarsi.leprosycare.content.QuizFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private int backButtonCount;
    private static final String COMMON_TAG = "OrintationChange";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBUser dbUser = new DBUser(this);
        Cursor cur = dbUser.getData();

        loadFragment(new BerandaFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFragment, fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.beranda_menu:
                fragment = new BerandaFragment();
                break;
            case R.id.edukasi_menu:
                fragment = new EdukasiFragment();

                break;
            case R.id.quiz_menu:
                fragment = new QuizFragment();
                break;
            case R.id.form_menu:
                fragment = new FormFragment();
                break;
            case R.id.alarm_menu:
                fragment = new AlarmFragment();
                break;
        }
        return loadFragment(fragment);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.i(COMMON_TAG, "landscape");
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i(COMMON_TAG, "portrait");
        }
    }

    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            Intent intent=new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this, "Press back again to exit.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
