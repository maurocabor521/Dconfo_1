package com.example.asus.dconfo_app;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.dconfo_app.helpers.Utilidades;
import com.example.asus.dconfo_app.presentation.view.activity.administrador.Home_AdminActivity;
import com.example.asus.dconfo_app.presentation.view.activity.docente.HomeDocenteActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private BottomBar bottomBar;
    private Intent intentAdministrador;
    private Intent intentDocente;
    private Intent intentEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = findViewById(R.id.bar_home_access);
        cargarBottombar();
    }

    private void cargarBottombar() {

        // bottomBar.setDefaultTab(R.id.btn_Administrador);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.btn_Administrador:
                        intentAdministrador = new Intent(MainActivity.this, Home_AdminActivity.class);
                        startActivity(intentAdministrador);
                        break;
                    case R.id.btn_Docente:

                        intentDocente = new Intent(MainActivity.this, HomeDocenteActivity.class);
                        startActivity(intentDocente);
                        //Validamos que se trabaja en modo portrait desde un smarthPhone  //video p3
              /*          if (findViewById(R.id.container_exercice) != null) {
                            Utilidades.PORTRAIT = true;//video p4 min 1:46
                            if (savedIS != null) {
                                return;//retorna ultima instancia
                            }
                            homeEjercicioFragment = new HomeEjercicioFragment();
                            getSupportFragmentManager().beginTransaction().
                                    replace(R.id.container_exercice, homeEjercicioFragment).commit();//video p3 hasta aqui
                        } else {//video p4
                            Utilidades.PORTRAIT = false;

                        }*/
                        break;
                    case R.id.btn_Estudiante:
                        intentEstudiante = new Intent(MainActivity.this, HomeDocenteActivity.class);
                        startActivity(intentEstudiante);
                        break;


                }
            }
        });
    }


}
