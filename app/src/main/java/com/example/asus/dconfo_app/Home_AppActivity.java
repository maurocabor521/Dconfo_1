package com.example.asus.dconfo_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;

public class Home_AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__app);
        BottomBar bottomBar=findViewById(R.id.bar_home_access1);
        bottomBar.setDefaultTab(R.id.icono_id);
    }
}
