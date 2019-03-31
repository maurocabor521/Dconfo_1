package com.example.asus.dconfo_app.presentation.view.presenter;

import android.content.Context;

import com.example.asus.dconfo_app.domain.model.EjercicioG1;
import com.example.asus.dconfo_app.presentation.view.contract.CategoriaEjerciciosContract;

import java.util.List;

public class CategoriaEjerciciosPresenter implements CategoriaEjerciciosContract.Presenter {
    @Override
    public List<EjercicioG1> getListaEjercicios() {
        return null;
    }

    @Override
    public List<String> getLstNombreEjercicios() {
        return null;
    }

    @Override
    public void loadListaEjercicios() {

    }

    @Override
    public void crearReceta(Context context, String nombre, List<String> ejercicios) {

    }
}
