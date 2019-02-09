package com.example.asus.dconfo_app;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.dconfo_app.helpers.Utilidades;
import com.example.asus.dconfo_app.presentation.view.activity.administrador.Home_AdminActivity;
import com.example.asus.dconfo_app.presentation.view.activity.docente.HomeDocenteActivity;
import com.example.asus.dconfo_app.presentation.view.activity.estudiante.HomeEstudianteActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    BottomBar bottomBar;
    private Intent intentAdministrador;
    private Intent intentDocente;
    private Intent intentEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = findViewById(R.id.bar_home_access1);
        //bottomBar.setDefaultTab(R.id.home_exercice);
        cargarMiddleBar();
    }

    private void cargarMiddleBar() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.home_administrador:
                        intentAdministrador=new Intent(MainActivity.this,Home_AdminActivity.class);
                        startActivity(intentAdministrador);
                        break;
                        case R.id.home_docente:
                        intentDocente=new Intent(MainActivity.this,HomeDocenteActivity.class);
                        startActivity(intentDocente);
                        break;
                        case R.id.home_estudiante:
                        intentEstudiante=new Intent(MainActivity.this,HomeEstudianteActivity.class);
                        startActivity(intentEstudiante);
                        break;
                }
            }
        });
    }


}
