package com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.fonico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.dconfo_app.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tipo2FonicoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tipo2FonicoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tipo2FonicoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CircleImageView cv_est_ft2_c1f1;
    private CircleImageView cv_est_ft2_c1f2;
    private CircleImageView cv_est_ft2_c1f3;
    private CircleImageView cv_est_ft2_c1f4;

    private TextView txt_name_img_est_ft2_c1f1;
    private TextView txt_name_img_est_ft2_c1f2;
    private TextView txt_name_img_est_ft2_c1f3;
    private TextView txt_name_img_est_ft2_c1f4;

    private TextView txt_letra_est_ft2_c1f1;
    private TextView txt_letra_est_ft2_c1f2;
    private TextView txt_letra_est_ft2_c1f3;
    private TextView txt_letra_est_ft2_c1f4;

    private Button btn_enviar_est_ft2;

    ArrayList<Integer> listaIdImagens;
    ArrayList<Integer> listafilImagenes;
    ArrayList<Integer> listacolImagenes;

    StringRequest stringRequest;
    JsonObjectRequest jsonObjectRequest;

    private OnFragmentInteractionListener mListener;

    public Tipo2FonicoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tipo2FonicoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tipo2FonicoFragment newInstance(String param1, String param2) {
        Tipo2FonicoFragment fragment = new Tipo2FonicoFragment();
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
        View view = inflater.inflate(R.layout.fragment_tipo2_fonico2, container, false);

        cv_est_ft2_c1f1 = (CircleImageView) view.findViewById(R.id.iv_estudiante_fon2_c1_f1);
        cv_est_ft2_c1f2 = (CircleImageView) view.findViewById(R.id.iv_estudiante_fon2_c1_f2);
        cv_est_ft2_c1f3 = (CircleImageView) view.findViewById(R.id.iv_estudiante_fon2_c1_f3);
        cv_est_ft2_c1f4 = (CircleImageView) view.findViewById(R.id.iv_estudiante_fon2_c1_f4);

        txt_name_img_est_ft2_c1f1 = (TextView) view.findViewById(R.id.txt_estudiante_fon2_nom_c1f1);
        txt_name_img_est_ft2_c1f2 = (TextView) view.findViewById(R.id.txt_estudiante_fon2_nom_c1f2);
        txt_name_img_est_ft2_c1f3 = (TextView) view.findViewById(R.id.txt_estudiante_fon2_nom_c1f3);
        txt_name_img_est_ft2_c1f4 = (TextView) view.findViewById(R.id.txt_estudiante_fon2_nom_c1f4);

        txt_letra_est_ft2_c1f1 = (TextView) view.findViewById(R.id.edt_estudiante_fon2_l1);
        txt_letra_est_ft2_c1f2 = (TextView) view.findViewById(R.id.edt_estudiante_fon2_l2);
        txt_letra_est_ft2_c1f3 = (TextView) view.findViewById(R.id.edt_estudiante_fon2_l3);
        txt_letra_est_ft2_c1f4 = (TextView) view.findViewById(R.id.edt_estudiante_fon2_l4);

        btn_enviar_est_ft2 = (Button) view.findViewById(R.id.btn_fonico_t2_estudiante_enviar);


        return view;
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
