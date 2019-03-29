package com.example.asus.dconfo_app.presentation.view.fragment.docente;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.EjercicioG1;
import com.example.asus.dconfo_app.presentation.view.adapter.SpinnerEjerciciosAdapter;
import com.example.asus.dconfo_app.repository.Callback;
import com.example.asus.dconfo_app.repository.ImpEjercicio;
import com.example.asus.dconfo_app.repository.ImpListEjercicios;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FindEjercicioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindEjercicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindEjercicioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Spinner sp_lista_ejercicios;
    private EditText edt_lista_ejercicios;
    private Button btn_find;
    List<String> lstEjercicios_frag;
    List<String> lstNombreEjercicios;
    ImpEjercicio impEjercicio;
    ImpListEjercicios impListEjercicios;
    SpinnerEjerciciosAdapter spinnerEjerciciosAdapter;
    Integer idDocente;

    private OnFragmentInteractionListener mListener;

    public FindEjercicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindEjercicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindEjercicioFragment newInstance(String param1, String param2) {
        FindEjercicioFragment fragment = new FindEjercicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_ejercicio, container, false);

        idDocente = getArguments().getInt("iddocente");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Docente id: " + idDocente);

        sp_lista_ejercicios = (Spinner) view.findViewById(R.id.sp_docente_FE);
        edt_lista_ejercicios = (EditText) view.findViewById(R.id.edt_docente_FE);

        impListEjercicios = new ImpListEjercicios(getContext(), view, idDocente);


        //impEjercicio = new ImpEjercicio(getContext(), view, 20);
        lstEjercicios_frag = new ArrayList<>();

        lstEjercicios_frag = new ImpListEjercicios(getContext(), view, idDocente).getListaEjercicios();

        //lstEjercicios_frag = impListEjercicios.getListaEjercicios();

        //System.out.println("la lista: "+impListEjercicios.getListaEjercicios());
       // cargarLista1();
        //final Callback<List<String>> listCallback

        if (lstEjercicios_frag == null) {
            //cargarLista();
            System.out.println("la lista: "+true);

        } else {
             cargarSpinner();
        }


        btn_find = (Button) view.findViewById(R.id.btn_docente_FE);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstEjercicios_frag = impListEjercicios.getListaEjercicios();
                cargarSpinner();
                Log.i("Mis ejercicios:", lstEjercicios_frag.toString());
            }
        });
        return view;
    }

    public void cargarLista() {
        lstEjercicios_frag = impListEjercicios.getListaEjercicios();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        // System.out.println("lista llenando");
        // cargarSpinner();
    }

    public void cargarLista1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lstEjercicios_frag = impListEjercicios.getListaEjercicios();
                cargarLista();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //cargarSpinner();
                        lstEjercicios_frag = impListEjercicios.getListaEjercicios();
                        Toast.makeText(getContext(), "cargando", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }


    public void cargarSpinner() {
        spinnerEjerciciosAdapter = new SpinnerEjerciciosAdapter(getContext(), lstEjercicios_frag, getView(), sp_lista_ejercicios);
        sp_lista_ejercicios = spinnerEjerciciosAdapter.getSpinner();


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

   /* @Override
    public void success(Object result) {
        lstEjercicios_frag = impListEjercicios.getListaEjercicios();
    }

    @Override
    public void error(Exception error) {

    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
