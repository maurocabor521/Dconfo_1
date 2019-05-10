package com.example.asus.dconfo_app.presentation.view.fragment.docente.fonico;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.dconfo_app.R;

import java.io.File;

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

    private ImageView iv_c1f1;
    private ImageView iv_c1f2;
    private ImageView iv_c1f3;
    private ImageView iv_c1f4;

    private EditText edt_l1;
    private EditText edt_l2;
    private EditText edt_l3;
    private EditText edt_l4;

    private LinearLayout ll_c1f1;
    private LinearLayout ll_c1f2;
    private LinearLayout ll_c1f3;
    private LinearLayout ll_c1f4;

    private LinearLayout ll_letra1;
    private LinearLayout ll_letra2;
    private LinearLayout ll_letra3;
    private LinearLayout ll_letra4;

    private Button btn_enviar;

    private String nameDocente;
    private int idDocente;

    private final int MIS_PERMISOS = 100;
    private static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;

    private static final String CARPETA_PRINCIPAL = "misImagenesApp/";//directorio principal
    private static final String CARPETA_IMAGEN = "imagenes";//carpeta donde se guardan las fotos
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;//ruta carpeta de directorios
    private String path;//almacena la ruta de la imagen

    //******** CONEXIÓN CON WEBSERVICE
    //RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    StringRequest stringRequest;

    ProgressDialog progreso;
    ImageView imgFoto;
    File fileImagen;
    Bitmap bitmap;

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
        View view = inflater.inflate(R.layout.fragment_tipo2_fonico, container, false);

        nameDocente = getArguments().getString("namedocente");
        idDocente = getArguments().getInt("iddocente");

        ll_c1f1 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_c1_f1);
        ll_c1f2 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_c1_f2);
        ll_c1f3 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_c1_f3);
        ll_c1f4 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_c1_f4);

        ll_letra1 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_l1);
        ll_letra2 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_l2);
        ll_letra3 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_l3);
        ll_letra4 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_l4);

        iv_c1f1 = (ImageView) view.findViewById(R.id.iv_docente_fon2_c1_f1);
        iv_c1f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_c1f2 = (ImageView) view.findViewById(R.id.iv_docente_fon2_c1_f2);
        iv_c1f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_c1f3 = (ImageView) view.findViewById(R.id.iv_docente_fon2_c1_f3);
        iv_c1f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_c1f4 = (ImageView) view.findViewById(R.id.iv_docente_fon2_c1_f4);
        iv_c1f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       /* InputFilter[] editFilters = <EditText >.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = <YOUR_FILTER >; <EditText >.setFilters(newFilters);*/

        edt_l1 = (EditText) view.findViewById(R.id.edt_docente_fon2_l1);
        edt_l1.setFilters(new InputFilter[]
                {new InputFilter.AllCaps(),
                        new InputFilter.LengthFilter(1)}
        );

        edt_l2 = (EditText) view.findViewById(R.id.edt_docente_fon2_l2);
        edt_l2.setFilters(new InputFilter[]
                {new InputFilter.AllCaps(),
                        new InputFilter.LengthFilter(1)}
        );
        edt_l3 = (EditText) view.findViewById(R.id.edt_docente_fon2_l3);
        edt_l3.setFilters(new InputFilter[]
                {new InputFilter.AllCaps(),
                        new InputFilter.LengthFilter(1)}
        );
        edt_l4 = (EditText) view.findViewById(R.id.edt_docente_fon2_l4);
        edt_l4.setFilters(new InputFilter[]
                {new InputFilter.AllCaps(),
                        new InputFilter.LengthFilter(1)}
        );

        btn_enviar = (Button) view.findViewById(R.id.btn_docente_fon2_enviar);

        return view;

    }

    //**********************************************************************************************

    private void mostrarDialogOpciones() {//part 9
        final CharSequence[] opciones = {"Elegir de Banco de Imágenes", "Elegir de Galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Elegir de Banco de Imágenes")) {
                /*    Intent intent = new Intent(getActivity(), BankImagesActivity.class);

                    //intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setAction(Intent.ACTION_PICK);
                    //intent.addCategory(Intent.CATEGORY_OPENABLE);
                    //intent.setType("image/*");

                    startActivityForResult(intent.createChooser(intent, "Seleccione"), COD_SELECCIONA);
                    System.out.println("info: " + intent);
                    //abriCamara();//part 10 tomar foto
                    //Toast.makeText(getContext(), "Cargar Cámara", Toast.LENGTH_LONG).show();
                    */
                    //cargarWebService_1();
                    //rv_tipo1Fonico.setVisibility(View.VISIBLE);
                } else {
                    if (opciones[i].equals("Elegir de Galeria")) {
                        /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);*/
                        //directamente de galeria
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent, "Seleccione"), COD_SELECCIONA);
                    } else {
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        builder.show();

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
