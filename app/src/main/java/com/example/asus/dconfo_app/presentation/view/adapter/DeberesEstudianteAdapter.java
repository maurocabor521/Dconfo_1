package com.example.asus.dconfo_app.presentation.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.DeberEstudiante;
import com.example.asus.dconfo_app.domain.model.Grupo;

import java.util.List;


/**
 * Created by Andrés Cabal on 20/12/2018.
 */

public class DeberesEstudianteAdapter extends RecyclerView.Adapter<DeberesEstudianteAdapter.EjerciciosHolder> implements View.OnClickListener {

    List<DeberEstudiante> listaDeberes;
    private View.OnClickListener listener;

    public DeberesEstudianteAdapter(List<DeberEstudiante> listaDeberes) {
        this.listaDeberes = listaDeberes;
    }


    @NonNull
    @Override
    public EjerciciosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deber_estudiante, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        vista.setOnClickListener(this);
        return new EjerciciosHolder(vista);
    }

    @Override
    public void onBindViewHolder(EjerciciosHolder holder, int position) {
        // holder.txtidGrupo.setText(listaGrupos.get(position).getIdGrupo().toString());
       // Log.i("size", "lista_: " + listaGrupos.size());
        if (listaDeberes.size() != 0) {
            holder.txtidEjercicio.setText(listaDeberes.get(position).getIdEjercicio().toString());
            holder.txtnameDeber.setText(listaDeberes.get(position).getTipoDeber().toString());


        } else {

        }

    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return listaDeberes.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class EjerciciosHolder extends RecyclerView.ViewHolder {
        TextView txtidEjercicio, txtnameDeber, txtidActividad, txtidTipo;

        public EjerciciosHolder(View itemView) {
            super(itemView);
            txtidEjercicio = (TextView) itemView.findViewById(R.id.txt_deber_idejercicio);
            txtnameDeber = (TextView) itemView.findViewById(R.id.txt_deber_tipodeber);


        }
    }
}
