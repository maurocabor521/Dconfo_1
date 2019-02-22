package com.example.asus.dconfo_app.presentation.view.activity.docente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.dconfo_app.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ManageEjercicioDocenteActivity extends AppCompatActivity {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ejercicio_docente);
        showToolbar("Gestionar Ejercicios Léxicos", true);
        bottomBar = findViewById(R.id.bottombar_CED);
    }

    private void cargarBottombar() {

        bottomBar.setDefaultTab(R.id.bott_curso);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.bott_curso:
                      /*  newCursoFragment = new NewCursoFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_Admincurso, newCursoFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        Toast.makeText(getApplicationContext(), "curso", Toast.LENGTH_LONG).show();
                        break;*/
                /*    case R.id.bott_grupo:
                        newGrupoFragment = new NewGrupoFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_Admincurso, newGrupoFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        Toast.makeText(getApplicationContext(), "grupo", Toast.LENGTH_LONG).show();*/
                        //Validamos que se trabaja en modo portrait desde un smarthPhone  //video p3
                  /*      if (findViewById(R.id.container_exercice) != null) {
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
              /*          break;
                    case R.id.bott_estudiantes:
                        Toast.makeText(getApplicationContext(), "estudiantes", Toast.LENGTH_LONG).show();
                        newEstudianteFragment = new NewEstudianteFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_Admincurso, newEstudianteFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.bott_all_cursos:
                        allCursosFragment = new AllCursosFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_Admincurso, allCursosFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;*/

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
