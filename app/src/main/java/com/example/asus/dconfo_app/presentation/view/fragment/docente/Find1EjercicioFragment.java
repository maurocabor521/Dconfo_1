package com.example.asus.dconfo_app.presentation.view.fragment.docente;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.EjercicioG1;
import com.example.asus.dconfo_app.presentation.view.adapter.SpinnerEjerciciosAdapter;
import com.example.asus.dconfo_app.presentation.view.contract.CategoriaEjerciciosContract;
import com.example.asus.dconfo_app.presentation.view.presenter.CategoriaEjerciciosPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Find1EjercicioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Find1EjercicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Find1EjercicioFragment extends Fragment implements CategoriaEjerciciosContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CategoriaEjerciciosContract.Presenter presenter;
    SpinnerEjerciciosAdapter spinnerEjerciciosAdapter;
    private Spinner sp_lista_ejercicios;
    private EditText edt_lista_ejercicios;
    private Button btn_find;
    private ProgressBar pb_find;
    private List<String> lst_NomEjercicios;
    private List<EjercicioG1> lst_Ejercicios;
    MediaPlayer mp;

    private OnFragmentInteractionListener mListener;

    public Find1EjercicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Find1EjercicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Find1EjercicioFragment newInstance(String param1, String param2) {
        Find1EjercicioFragment fragment = new Find1EjercicioFragment();
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
        View view = inflater.inflate(R.layout.fragment_find1_ejercicio, container, false);

        presenter = new CategoriaEjerciciosPresenter(this, getContext());
        presenter.loadListaEjercicios();

        edt_lista_ejercicios = (EditText) view.findViewById(R.id.edt_F1_docente_FE);
        sp_lista_ejercicios = (Spinner) view.findViewById(R.id.sp_F1_docente_FE);
        mp = MediaPlayer.create(getContext(), R.raw.sound_button);

        btn_find = (Button) view.findViewById(R.id.btn_F1_docente_FE);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });

        lst_Ejercicios = new ArrayList<EjercicioG1>();
        lst_NomEjercicios = new ArrayList<String>();

        return view;
    }

    @Override
    public void showListaEjercicios() {
        lst_NomEjercicios=presenter.getLstNombreEjercicios();
        lst_Ejercicios=presenter.getListaEjercicios();
        spinnerEjerciciosAdapter = new SpinnerEjerciciosAdapter(getContext(), lst_NomEjercicios, getView(), sp_lista_ejercicios);
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
