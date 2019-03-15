package com.example.asus.dconfo_app.presentation.view.activity.estudiante;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.asus.dconfo_app.LoginMainEstudianteActivity;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.presentation.view.activity.docente.ManageEjercicioDocenteActivity;
import com.example.asus.dconfo_app.presentation.view.activity.docente.NewEjercicioDocenteActivity;
import com.example.asus.dconfo_app.presentation.view.fragment.docente.HomeEjerciciosDocenteFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class HomeEstudianteActivity extends AppCompatActivity {
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_estudiante);
        bottomBar = findViewById(R.id.bottombar_estudiante);
        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();

        String nameestudiante = extra.getString("nameestudiante");
        int idestudiante= extra.getInt("idestudiante");
        showToolbar("Est: "+nameestudiante+" ,id: "+idestudiante, true);
    }

    private void cargarBottombar() {

        bottomBar.setDefaultTab(R.id.bo);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.bott__deber_home_deberes:
                        homeEjerciciosDocenteFragment = new HomeEjerciciosDocenteFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_DocenteEjercicios, homeEjerciciosDocenteFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        //Toast.makeText(getApplicationContext(), "Ejercicios Home", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bott_deber_t1:

                        break;
                        case R.id.bott_deber_t2:

                        break;
                }
            }
        });
    }

    public void showToolbar(String tittle, boolean upButton) {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_ejercicio);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        //getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
