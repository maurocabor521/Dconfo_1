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
       cargarBottombar();
    }

    private void cargarBottombar() {


    }


}
