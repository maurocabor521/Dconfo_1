package com.example.asus.dconfo_app.presentation.view.activity.docente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import com.example.asus.dconfo_app.R;

public class AsignarDeberDocenteActivity extends AppCompatActivity {

    Spinner spinnerEjercicios;
    Spinner spinnerEstudiantes;
    Button btnAsignar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_deber_docente);
        spinnerEjercicios = findViewById(R.id.sp_Ejercicios_asignar);
        spinnerEstudiantes = findViewById(R.id.sp_Estudiantes_asignar);
        btnAsignar = findViewById(R.id.btn_Asignar_asignar);
    }
}
