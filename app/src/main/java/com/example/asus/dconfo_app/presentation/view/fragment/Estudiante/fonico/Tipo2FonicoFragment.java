package com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.fonico;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.Imagen;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class Tipo2FonicoFragment extends Fragment
        implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener {
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

    private LinearLayout ll_cv_c1f1;
    private LinearLayout ll_cv_c1f2;
    private LinearLayout ll_cv_c1f3;
    private LinearLayout ll_cv_c1f4;

    private LinearLayout ll_txt_c1f1;
    private LinearLayout ll_txt_c1f2;
    private LinearLayout ll_txt_c1f3;
    private LinearLayout ll_txt_c1f4;

    private Button btn_enviar_est_ft2;

    private int idImagen1;
    private int idImagen2;
    private int idImagen3;
    private int idImagen4;

    private int filejercicio1;
    private int filejercicio2;
    private int filejercicio3;
    private int filejercicio4;

    private int colejercicio1;
    private int colejercicio2;
    private int colejercicio3;
    private int colejercicio4;

    private String letrac1f1;
    private String letrac1f2;
    private String letrac1f3;
    private String letrac1f4;

    private boolean cv_c1f1_isactived = false;
    private boolean cv_c1f2_isactived = false;
    private boolean cv_c1f3_isactived = false;
    private boolean cv_c1f4_isactived = false;

    private boolean cv_c1f1_desactivado = false;
    private boolean cv_c1f2_desactivado = false;
    private boolean cv_c1f3_desactivado = false;
    private boolean cv_c1f4_desactivado = false;


    private boolean txt_c1f1_isactived = false;
    private boolean txt_c1f2_isactived = false;
    private boolean txt_c1f3_isactived = false;
    private boolean txt_c1f4_isactived = false;

    private boolean txt_c1f1_desactivado = false;
    private boolean txt_c1f2_desactivado = false;
    private boolean txt_c1f3_desactivado = false;
    private boolean txt_c1f4_desactivado = false;

    private boolean col_imgs = false;
    private boolean col_letras = false;
    private boolean parejaCreada = false;
    private int contadorParejas = 0;

    private int contadorColImgs = 0;
    private int contadorColLetras = 0;

    private boolean pareja1 = false;
    private boolean pareja2 = false;
    private boolean pareja3 = false;
    private boolean pareja4 = false;

    ArrayList<Integer> listaIdImagens;
    ArrayList<Integer> listafilImagenes;
    ArrayList<Integer> listacolImagenes;

    ArrayList<String> listaLetras;
    ArrayList<Integer> listafilLetras;
    ArrayList<Integer> listacolLetras;

    private Imagen imagen;
    String urlImagen;
    int f = -1;

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

        cv_est_ft2_c1f1.setOnClickListener(this);
        cv_est_ft2_c1f2.setOnClickListener(this);
        cv_est_ft2_c1f3.setOnClickListener(this);
        cv_est_ft2_c1f4.setOnClickListener(this);

        txt_name_img_est_ft2_c1f1 = (TextView) view.findViewById(R.id.txt_estudiante_fon2_nom_c1f1);
        txt_name_img_est_ft2_c1f2 = (TextView) view.findViewById(R.id.txt_estudiante_fon2_nom_c1f2);
        txt_name_img_est_ft2_c1f3 = (TextView) view.findViewById(R.id.txt_estudiante_fon2_nom_c1f3);
        txt_name_img_est_ft2_c1f4 = (TextView) view.findViewById(R.id.txt_estudiante_fon2_nom_c1f4);

        txt_letra_est_ft2_c1f1 = (TextView) view.findViewById(R.id.edt_estudiante_fon2_l1);
        txt_letra_est_ft2_c1f2 = (TextView) view.findViewById(R.id.edt_estudiante_fon2_l2);
        txt_letra_est_ft2_c1f3 = (TextView) view.findViewById(R.id.edt_estudiante_fon2_l3);
        txt_letra_est_ft2_c1f4 = (TextView) view.findViewById(R.id.edt_estudiante_fon2_l4);

        txt_letra_est_ft2_c1f1.setOnClickListener(this);
        txt_letra_est_ft2_c1f2.setOnClickListener(this);
        txt_letra_est_ft2_c1f3.setOnClickListener(this);
        txt_letra_est_ft2_c1f4.setOnClickListener(this);

        ll_cv_c1f1 = (LinearLayout) view.findViewById(R.id.ll_estudiante_fon2_c1_f1);
        ll_cv_c1f2 = (LinearLayout) view.findViewById(R.id.ll_estudiante_fon2_c1_f2);
        ll_cv_c1f3 = (LinearLayout) view.findViewById(R.id.ll_estudiante_fon2_c1_f3);
        ll_cv_c1f4 = (LinearLayout) view.findViewById(R.id.ll_estudiante_fon2_c1_f4);

        ll_txt_c1f1 = (LinearLayout) view.findViewById(R.id.ll_estudiante_fon2_l1);
        ll_txt_c1f2 = (LinearLayout) view.findViewById(R.id.ll_estudiante_fon2_l2);
        ll_txt_c1f3 = (LinearLayout) view.findViewById(R.id.ll_estudiante_fon2_l3);
        ll_txt_c1f4 = (LinearLayout) view.findViewById(R.id.ll_estudiante_fon2_l4);

        btn_enviar_est_ft2 = (Button) view.findViewById(R.id.btn_fonico_t2_estudiante_enviar);

        idImagen1 = getArguments().getInt("idejercicio1");
        idImagen2 = getArguments().getInt("idejercicio2");
        idImagen3 = getArguments().getInt("idejercicio3");
        idImagen4 = getArguments().getInt("idejercicio4");

        filejercicio1 = getArguments().getInt("filejercicio1");
        System.out.println("fila1 :" + filejercicio1);
        filejercicio2 = getArguments().getInt("filejercicio2");
        filejercicio3 = getArguments().getInt("filejercicio3");
        filejercicio4 = getArguments().getInt("filejercicio4");

        colejercicio1 = getArguments().getInt("colejercicio1");
        colejercicio2 = getArguments().getInt("colejercicio2");
        colejercicio3 = getArguments().getInt("colejercicio3");
        colejercicio4 = getArguments().getInt("colejercicio4");

        letrac1f1 = getArguments().getString("letra1");
        letrac1f2 = getArguments().getString("letra2");
        letrac1f3 = getArguments().getString("letra3");
        letrac1f4 = getArguments().getString("letra4");

        listaIdImagens = new ArrayList<>();
        listacolImagenes = new ArrayList<>();
        listafilImagenes = new ArrayList<>();

        listaIdImagens.add(idImagen1);
        listaIdImagens.add(idImagen2);
        listaIdImagens.add(idImagen3);
        listaIdImagens.add(idImagen4);

        llamarWebService();
        cargarLetras();

        return view;
    }//on create

    // Implement the OnClickListener callback
    //**********************************************************************************************


    private void verificaParejas() {
        if ((col_imgs && col_letras) == true) {

            if ((contadorColImgs == 1 && contadorColLetras == 1)) {
                System.out.println(" VALIDO --- cont imagenes :" + contadorColImgs + " cont letras :" + contadorColLetras);
                contadorColLetras--;
                contadorColImgs--;
                col_letras = false;
                col_imgs = false;
                System.out.println(" parejas 1" + pareja1);
                crearParejas();

            } else {
                System.out.println("NO VALIDO --- cont imagenes :" + contadorColImgs + " cont letras :" + contadorColLetras);
            }
        } else if (contadorColImgs > 1) {
            System.out.println("ACCIÓN NO VALIDA img");
            contadorColImgs--;
        }
        if (contadorColLetras > 1) {
            System.out.println("ACCIÓN NO VALIDA letra");
            contadorColLetras--;
        }
    }

    //**********************************************************************************************

    private void crearParejas() {
        ArrayList<Integer> pareja_1 = new ArrayList<>();
        ArrayList<Integer> pareja_2 = new ArrayList<>();
        ArrayList<Integer> pareja_3 = new ArrayList<>();
        ArrayList<Integer> pareja_4 = new ArrayList<>();

        if (pareja1 == false) {//---------------------------------------pareja 1
            //**************************************************** c1
            if (cv_c1f1_isactived && txt_c1f1_isactived) {

                pareja_1.add(1);
                pareja1 = true;
                cv_c1f1_desactivado = true;
                txt_c1f1_desactivado = true;

                ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f1_isactived && txt_c1f2_isactived) {
                pareja_1.add(2);
                pareja1 = true;
                cv_c1f1_desactivado = true;
                txt_c1f2_desactivado = true;
                ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f2.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f1_isactived && txt_c1f3_isactived) {
                pareja_1.add(3);
                pareja1 = true;
                cv_c1f1_desactivado = true;
                txt_c1f3_desactivado = true;
                ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f1_isactived && txt_c1f4_isactived) {
                pareja_1.add(4);
                pareja1 = true;
                cv_c1f1_desactivado = true;
                txt_c1f4_desactivado = true;
                ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f4.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f1_isactived) {// **************************************************** c2

                pareja_1.add(5);
                pareja1 = true;
                cv_c1f2_desactivado = true;
                txt_c1f1_desactivado = true;

                ll_cv_c1f2.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f2_isactived) {
                pareja_1.add(6);
                pareja1 = true;
                cv_c1f2_desactivado = true;
                txt_c1f2_desactivado = true;
                ll_cv_c1f2.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f3_isactived) {
                pareja_1.add(7);
                pareja1 = true;
                cv_c1f2_desactivado = true;
                txt_c1f3_desactivado = true;
                ll_cv_c1f2.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f2.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f4_isactived) {
                pareja_1.add(8);
                pareja1 = true;
                cv_c1f2_desactivado = true;
                txt_c1f4_desactivado = true;
                ll_cv_c1f2.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f4.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f3_isactived && txt_c1f1_isactived) {// **************************************************** c3

                pareja_1.add(9);
                pareja1 = true;
                cv_c1f3_desactivado = true;
                txt_c1f1_desactivado = true;

                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f2_isactived) {
                pareja_1.add(10);
                pareja1 = true;
                cv_c1f3_desactivado = true;
                txt_c1f2_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f2.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f3_isactived) {
                pareja_1.add(11);
                pareja1 = true;
                cv_c1f3_desactivado = true;
                txt_c1f3_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f4_isactived) {
                pareja_1.add(12);
                pareja1 = true;
                cv_c1f3_desactivado = true;
                txt_c1f4_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f4.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
            } else if (cv_c1f4_isactived && txt_c1f1_isactived) {// **************************************************** c4

                pareja_1.add(13);
                pareja1 = true;
                cv_c1f4_desactivado = true;
                txt_c1f1_desactivado = true;

                ll_cv_c1f4.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f4_isactived && txt_c1f2_isactived) {
                pareja_1.add(14);
                pareja1 = true;
                cv_c1f4_desactivado = true;
                txt_c1f2_desactivado = true;
                ll_cv_c1f4.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f2.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f3_isactived) {
                pareja_1.add(15);
                pareja1 = true;
                cv_c1f4_desactivado = true;
                txt_c1f3_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f4_isactived) {
                pareja_1.add(16);
                pareja1 = true;
                cv_c1f4_desactivado = true;
                txt_c1f4_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                ll_txt_c1f4.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
            }
            //****************************************************
        }//***************************************************************************if pareja 1 false


        else if (pareja1 == true && pareja2 == false) {//---------------------------------------pareja 2
            //**************************************************** c1
            if (cv_c1f1_isactived && txt_c1f1_isactived) {

                pareja_1.add(1);
                cv_c1f1_desactivado = true;
                txt_c1f1_desactivado = true;

                ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f1_isactived && txt_c1f2_isactived) {
                pareja_1.add(2);

                cv_c1f1_desactivado = true;
                txt_c1f2_desactivado = true;
                ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f1_isactived && txt_c1f3_isactived) {
                pareja_1.add(3);

                cv_c1f1_desactivado = true;
                txt_c1f3_desactivado = true;
                ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f1_isactived && txt_c1f4_isactived) {
                pareja_1.add(4);

                cv_c1f1_desactivado = true;
                txt_c1f4_desactivado = true;
                ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f2_isactived && txt_c1f1_isactived) {// **************************************************** c2

                pareja_1.add(5);
                cv_c1f2_desactivado = true;
                txt_c1f1_desactivado = true;

                ll_cv_c1f2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f2_isactived && txt_c1f2_isactived) {
                pareja_1.add(6);

                cv_c1f2_desactivado = true;
                txt_c1f2_desactivado = true;
                ll_cv_c1f2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f2_isactived && txt_c1f3_isactived) {
                pareja_1.add(7);

                cv_c1f2_desactivado = true;
                txt_c1f3_desactivado = true;
                ll_cv_c1f2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f2_isactived && txt_c1f4_isactived) {
                pareja_1.add(8);

                cv_c1f2_desactivado = true;
                txt_c1f4_desactivado = true;
                ll_cv_c1f2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f3_isactived && txt_c1f1_isactived) {// **************************************************** c3

                pareja_1.add(9);
                cv_c1f3_desactivado = true;
                txt_c1f1_desactivado = true;

                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f2_isactived && txt_c1f2_isactived) {
                pareja_1.add(10);

                cv_c1f3_desactivado = true;
                txt_c1f2_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f2_isactived && txt_c1f3_isactived) {
                pareja_1.add(11);

                cv_c1f3_desactivado = true;
                txt_c1f3_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f3.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));

            } else if (cv_c1f2_isactived && txt_c1f4_isactived) {
                pareja_1.add(12);

                cv_c1f3_desactivado = true;
                txt_c1f4_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            } else if (cv_c1f4_isactived && txt_c1f1_isactived) {// **************************************************** c4

                pareja_1.add(13);
                cv_c1f4_desactivado = true;
                txt_c1f1_desactivado = true;

                ll_cv_c1f4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f4_isactived && txt_c1f2_isactived) {
                pareja_1.add(14);

                cv_c1f4_desactivado = true;
                txt_c1f2_desactivado = true;
                ll_cv_c1f4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f2_isactived && txt_c1f3_isactived) {
                pareja_1.add(15);

                cv_c1f4_desactivado = true;
                txt_c1f3_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (cv_c1f2_isactived && txt_c1f4_isactived) {
                pareja_1.add(16);

                cv_c1f4_desactivado = true;
                txt_c1f4_desactivado = true;
                ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                ll_txt_c1f4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            //****************************************************
        }//***************************************************************************if pareja 2 false


    }//******************crear parejas

    //**********************************************************************************************
    public void onClick(View v) {//f2
        switch (v.getId()) {
            case R.id.iv_estudiante_fon2_c1_f1:
                if (cv_c1f1_isactived == false && cv_c1f1_desactivado == false) {

                    cv_c1f1_isactived = true;
                    col_imgs = true;
                    contadorColImgs++;

                    //ll_cv_c1f1.setBackgroundColor(Color.YELLOW);

                 /*   if (pareja1 == false) {
                        ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                        //ll_cv_c1f1.setBackground(getResources().getDrawable(R.drawable.alarma));
                    } else if (pareja1 == true) {
                        ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.bb_darkBackgroundColor));
                    } else if (pareja2 == true) {
                        ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    } else if (pareja3 == true) {
                        ll_cv_c1f1.setBackgroundColor(getResources().getColor(R.color.editTextColorWhite));
                    }

                    ll_cv_c1f2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    ll_cv_c1f3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    ll_cv_c1f4.setBackgroundColor(getResources().getColor(R.color.colorAccent));*/

                    cv_c1f2_isactived = false;
                    cv_c1f3_isactived = false;
                    cv_c1f4_isactived = false;

                    verificaParejas();

                }
                System.out.println("imagen 1");
                //Drawable drawable=;
                //ll_cv_c1f1.setBackgroundColor(Color.WHITE);
                Toast.makeText(getContext(), "imagen c1f1 :" + cv_c1f1_isactived, Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_estudiante_fon2_c1_f2:

                if (cv_c1f2_isactived == false && cv_c1f2_desactivado == false) {
                    cv_c1f2_isactived = true;
                    col_imgs = true;
                    contadorColImgs++;

                  /*  ll_cv_c1f2.setBackgroundColor(Color.WHITE);

                    ll_cv_c1f1.setBackgroundColor(Color.RED);
                    ll_cv_c1f3.setBackgroundColor(Color.RED);
                    ll_cv_c1f4.setBackgroundColor(Color.RED);*/

                    cv_c1f1_isactived = false;
                    cv_c1f3_isactived = false;
                    cv_c1f4_isactived = false;

                    verificaParejas();

                }
                System.out.println("imagen 2");
                break;
            case R.id.iv_estudiante_fon2_c1_f3:
                if (cv_c1f3_isactived == false && cv_c1f3_desactivado == false) {
                    cv_c1f3_isactived = true;
                    col_imgs = true;
                    contadorColImgs++;

                 /*   ll_cv_c1f3.setBackgroundColor(Color.WHITE);

                    ll_cv_c1f1.setBackgroundColor(Color.RED);
                    ll_cv_c1f2.setBackgroundColor(Color.RED);
                    ll_cv_c1f4.setBackgroundColor(Color.RED);*/

                    cv_c1f1_isactived = false;
                    cv_c1f2_isactived = false;
                    cv_c1f4_isactived = false;

                    verificaParejas();
                }
                System.out.println("imagen 3");
                break;
            case R.id.iv_estudiante_fon2_c1_f4:

                if (cv_c1f4_isactived == false && cv_c1f4_desactivado == false) {
                    cv_c1f4_isactived = true;
                    col_imgs = true;
                    contadorColImgs++;

                  /*  ll_cv_c1f4.setBackgroundColor(Color.WHITE);

                    ll_cv_c1f1.setBackgroundColor(Color.RED);
                    ll_cv_c1f3.setBackgroundColor(Color.RED);
                    ll_cv_c1f2.setBackgroundColor(Color.RED);*/

                    cv_c1f1_isactived = false;
                    cv_c1f3_isactived = false;
                    cv_c1f2_isactived = false;

                    verificaParejas();
                }
                System.out.println("imagen 4");
                break;
            case R.id.edt_estudiante_fon2_l1:
                if (txt_c1f1_isactived == false && txt_c1f1_desactivado == false) {
                    txt_c1f1_isactived = true;
                    col_letras = true;
                    contadorColLetras++;

                    txt_c1f2_isactived = false;
                    txt_c1f3_isactived = false;
                    txt_c1f4_isactived = false;
                    //col_imgs = false;

                    verificaParejas();
                    System.out.println("letra 1");
                }
                break;
            case R.id.edt_estudiante_fon2_l2:
                if (txt_c1f2_isactived == false && txt_c1f2_desactivado == false) {
                    txt_c1f2_isactived = true;
                    col_letras = true;
                    contadorColLetras++;

                    txt_c1f1_isactived = false;
                    txt_c1f3_isactived = false;
                    txt_c1f4_isactived = false;
                    //col_imgs = false;
                    System.out.println("letra 2");

                    verificaParejas();
                }
                break;
            case R.id.edt_estudiante_fon2_l3:
                if (txt_c1f3_isactived == false && txt_c1f3_desactivado == false) {
                    txt_c1f3_isactived = true;
                    col_letras = true;
                    contadorColLetras++;

                    txt_c1f1_isactived = false;
                    txt_c1f2_isactived = false;
                    txt_c1f4_isactived = false;
                    //col_imgs = false;
                    System.out.println("letra 3");

                    verificaParejas();
                }
                break;
            case R.id.edt_estudiante_fon2_l4:
                if (txt_c1f4_isactived == false && txt_c1f4_desactivado == false) {
                    txt_c1f4_isactived = true;
                    col_letras = true;
                    contadorColLetras++;

                    txt_c1f1_isactived = false;
                    txt_c1f2_isactived = false;
                    txt_c1f3_isactived = false;
                    //col_imgs = false;
                    System.out.println("letra 4");
                    System.out.println("col letras" + col_letras);
                    System.out.println("col imagenes" + col_imgs);

                    verificaParejas();
                }
                break;
        }
    }

    //----------------------------------------------------------------------------------------------
    private void cargarLetras() {
        txt_letra_est_ft2_c1f1.setText(letrac1f1);
        txt_letra_est_ft2_c1f2.setText(letrac1f2);
        txt_letra_est_ft2_c1f3.setText(letrac1f3);
        txt_letra_est_ft2_c1f4.setText(letrac1f4);
    }

    //----------------------------------------------------------------------------------------------

    public void llamarWebService() {
        //for (int i = 0; i < listaIdImagens.size(); i++) {
        if (f < 3) {
            f++;
            System.out.println("valor f: " + f);
            cargarWebService(listaIdImagens.get(f));
        }


        //}
    }

    //----------------------------------------------------------------------------------------------

    public void cargarWebService(int idejercicio) {

        String url_lh = Globals.url;
        // System.out.println("f: " + f);
        // String ip = getString(R.string.ip);

        //String url = "http://192.168.0.13/proyecto_dconfo/wsJSONConsultarListaCursos.php";
        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarImagen.php?idImagen_Ejercicio=" + idejercicio;
        //String url = ip+"ejemploBDRemota/wsJSONConsultarLista.php";
        //reemplazar espacios en blanco del nombre por %20
        url = url.replace(" ", "%20");
        //hace el llamado a la url
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);//p21
        //Toast.makeText(getContext(), "web service", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        // Toast.makeText(getContext(), "No se puede conectar exitosamente" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR", error.toString());

    }

    // si esta bien el llamado a la url entonces entra a este metodo
    @Override
    public void onResponse(JSONObject response) {
        // public ArrayList<Curso> onResponse(JSONObject response) {
        //lectura del Json

        //Toast.makeText(getContext(), "onResponse: " + response.toString(), Toast.LENGTH_SHORT).show();
        imagen = null;
        JSONArray json = response.optJSONArray("imagen");

        ArrayList<Imagen> listaDEimagenes = new ArrayList<>();
        listaDEimagenes = new ArrayList<>();

        try {
            for (int i = 0; i < json.length(); i++) {
                imagen = new Imagen();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                imagen.setIdImagen(jsonObject.optInt("idImagen_Ejercicio"));
                imagen.setNameImagen(jsonObject.optString("name_Imagen_Ejercicio"));
                imagen.setRutaImagen(jsonObject.optString("ruta_Imagen_Ejercicio"));
                imagen.setLetraInicialImagen(jsonObject.optString("letra_inicial_Imagen"));
                imagen.setLetraFinalImagen(jsonObject.optString("letra_final_Imagen"));
                imagen.setCantSilabasImagen(jsonObject.optInt("cant_silabas_Imagen"));
                listaDEimagenes.add(imagen);

            }
           /* textOracion = ejercicioG1.getOracion();
            cantLexemas = ejercicioG1.getCantidadValida();*/

            String url_lh = Globals.url;

            final String rutaImagen = imagen.getRutaImagen();

            urlImagen = "http://" + url_lh + "/proyecto_dconfo/" + rutaImagen;
            urlImagen = urlImagen.replace(" ", "%20");

            ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    //holder.imagen.setImageBitmap(response);


                    if (f == 0) {
                        //letraf1_c1 = imagen.getLetraInicialImagen();
                        //System.out.println("  letraf1_c1: " + letraf1_c1);
                        cv_est_ft2_c1f1.setBackground(null);
                        cv_est_ft2_c1f1.setImageBitmap(response);
                    } else if (f == 1) {
                        //letraf1_c2 = imagen.getLetraInicialImagen();
                        cv_est_ft2_c1f2.setBackground(null);
                        cv_est_ft2_c1f2.setImageBitmap(response);
                    } else if (f == 2) {
                        //letraf1_c3 = imagen.getLetraInicialImagen();
                        cv_est_ft2_c1f3.setBackground(null);
                        cv_est_ft2_c1f3.setImageBitmap(response);
                    } else if (f == 3) {
                        //letraf1_c4 = imagen.getLetraInicialImagen();
                        cv_est_ft2_c1f4.setBackground(null);
                        cv_est_ft2_c1f4.setImageBitmap(response);
                    }
                    llamarWebService();

                }
            }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                    System.out.println("ruta imagen: " + urlImagen);
                }
            });
            VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(imageRequest);


        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText(getContext(), "No se ha podido establecer conexión: " + response.toString(), Toast.LENGTH_LONG).show();

        }
    }//onResponse


    //----------------------------------------------------------------------------------------------

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
