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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.EjercicioG2;
import com.example.asus.dconfo_app.domain.model.EjercicioG2HasImagen;
import com.example.asus.dconfo_app.domain.model.Imagen;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;
import com.example.asus.dconfo_app.presentation.view.adapter.ImagenUrlAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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
public class Tipo2FonicoFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>, View.OnClickListener {
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

    private CircleImageView cv_c1f1;
    private CircleImageView cv_c1f2;
    private CircleImageView cv_c1f3;
    private CircleImageView cv_c1f4;

    private RadioButton rb_letraInicial;
    private RadioButton rb_letraFinal;

    private boolean flag_iv_c1f1;
    private boolean flag_iv_c1f2;
    private boolean flag_iv_c1f3;
    private boolean flag_iv_c1f4;
    private boolean estado_RbletraInicial;
    private boolean estado_RbletraFinal;

    private TextView txt_name_c1f1;
    private TextView txt_name_c1f2;
    private TextView txt_name_c1f3;
    private TextView txt_name_c1f4;


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

    private EjercicioG2HasImagen ejercicioG2HasImagen;

    private RecyclerView rv_imagenesBancoDatos;

    private Button btn_enviar;

    ArrayList<Imagen> listaImagenes;
    ArrayList<EjercicioG2> listaEjerciciosG2;

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
        final View view = inflater.inflate(R.layout.fragment_tipo2_fonico, container, false);

        nameDocente = getArguments().getString("namedocente");
        idDocente = getArguments().getInt("iddocente");

        listaImagenes = new ArrayList<>();

        rv_imagenesBancoDatos = (RecyclerView) view.findViewById(R.id.rv_docente_fon2_imgs);
        rv_imagenesBancoDatos.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_imagenesBancoDatos.setHasFixedSize(true);
        //rv_imagenesBancoDatos.setVisibility(View.INVISIBLE);

        rb_letraInicial = (RadioButton) view.findViewById(R.id.rb_letraInicial);
        rb_letraFinal = (RadioButton) view.findViewById(R.id.rb_letraFinal);

        //verificaRadioButton();

        ll_c1f1 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_c1_f1);
        ll_c1f2 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_c1_f2);
        ll_c1f3 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_c1_f3);
        ll_c1f4 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_c1_f4);

        ll_letra1 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_l1);
        ll_letra2 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_l2);
        ll_letra3 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_l3);
        ll_letra4 = (LinearLayout) view.findViewById(R.id.ll_docente_fon2_l4);

        txt_name_c1f1 = (TextView) view.findViewById(R.id.txt_docente_fon2_nom_c1f1);
        txt_name_c1f2 = (TextView) view.findViewById(R.id.txt_docente_fon2_nom_c1f2);
        txt_name_c1f3 = (TextView) view.findViewById(R.id.txt_docente_fon2_nom_c1f3);
        txt_name_c1f4 = (TextView) view.findViewById(R.id.txt_docente_fon2_nom_c1f4);


        cv_c1f1 = (CircleImageView) view.findViewById(R.id.iv_docente_fon2_c1_f1);
        cv_c1f2 = (CircleImageView) view.findViewById(R.id.iv_docente_fon2_c1_f2);
        cv_c1f3 = (CircleImageView) view.findViewById(R.id.iv_docente_fon2_c1_f3);
        cv_c1f4 = (CircleImageView) view.findViewById(R.id.iv_docente_fon2_c1_f4);

        /*iv_c1f1 = (ImageView) view.findViewById(R.id.iv_docente_fon2_c1_f1);
        iv_c1f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogOpciones();
            }
        });*/



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

//        iv_c1f1.setOnClickListener(this);
        cv_c1f1.setOnClickListener(this);
        cv_c1f2.setOnClickListener(this);
        cv_c1f3.setOnClickListener(this);
        cv_c1f4.setOnClickListener(this);


        btn_enviar = (Button) view.findViewById(R.id.btn_docente_fon2_enviar);
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaRadioButton();
            }
        });

        consultarListaImagenes();

        return view;

    }

    public void verificaRadioButton() {
        if (rb_letraInicial.isChecked() == true) {
            estado_RbletraInicial = rb_letraInicial.isChecked();
            System.out.println("Radio letra inicial estado: " + estado_RbletraInicial);
            //System.out.println("Radio letra final estado: "+estado_RbletraFinal);
        } else if (rb_letraFinal.isChecked() == true) {
            //System.out.println("Radio letra inicial estado: "+estado_RbletraInicial);
            estado_RbletraFinal = rb_letraFinal.isChecked();
            System.out.println("Radio letra final estado: " + estado_RbletraFinal);
        }
    }
    //**********************************************************************************************

 /*   Button button = (Button)findViewById(R.id.corky);
        button.setOnClickListener(this);
}*/

    // Implement the OnClickListener callback
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_docente_fon2_c1_f1:
                mostrarDialogOpciones();
                flag_iv_c1f1 = true;
                break;
            case R.id.iv_docente_fon2_c1_f2:
                mostrarDialogOpciones();
                flag_iv_c1f2 = true;
                break;
            case R.id.iv_docente_fon2_c1_f3:
                mostrarDialogOpciones();
                flag_iv_c1f3 = true;
                break;
            case R.id.iv_docente_fon2_c1_f4:
                mostrarDialogOpciones();
                flag_iv_c1f4 = true;
                break;
        }
    }

    //**********************************************************************************************

    private void cargarImagenWebService(String rutaImagen, final String nameImagen, final int idImagen) {

        // String ip = context.getString(R.string.ip);

        String url_lh = Globals.url;

        String urlImagen = "http://" + url_lh + "/proyecto_dconfo/" + rutaImagen;
        urlImagen = urlImagen.replace(" ", "%20");

        ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                if (flag_iv_c1f1) {
                    cv_c1f1.setBackground(null);
                    cv_c1f1.setImageBitmap(response);
                    //iv_c1f1.setBackground(null);
                    //iv_c1f1.setImageBitmap(response);
                    flag_iv_c1f1 = false;
                    rv_imagenesBancoDatos.setVisibility(View.GONE);
                    txt_name_c1f1.setText(nameImagen);

                    int fila = 1;
                    int columna = 1;

                    /*  ejercicioG2HasImagen.setIdImagen(idImagen);
                    ejercicioG2HasImagen.setFilaImagen(fila);
                    ejercicioG2HasImagen.setColumnaImagen(columna);

                    listaidImagenes.add(idImagen);
                    listafilaImagen.add(fila);
                    listacolumnaImagen.add(columna);*/

                }
                if (flag_iv_c1f2) {
                    cv_c1f2.setBackground(null);
                    cv_c1f2.setImageBitmap(response);
                    flag_iv_c1f2 = false;
                    rv_imagenesBancoDatos.setVisibility(View.GONE);
                    txt_name_c1f2.setText(nameImagen);

                    int fila = 1;
                    int columna = 1;

                    /*  ejercicioG2HasImagen.setIdImagen(idImagen);
                    ejercicioG2HasImagen.setFilaImagen(fila);
                    ejercicioG2HasImagen.setColumnaImagen(columna);

                    listaidImagenes.add(idImagen);
                    listafilaImagen.add(fila);
                    listacolumnaImagen.add(columna);*/

                }
                if (flag_iv_c1f3) {
                    cv_c1f3.setBackground(null);
                    cv_c1f3.setImageBitmap(response);
                    flag_iv_c1f3 = false;
                    rv_imagenesBancoDatos.setVisibility(View.GONE);
                    txt_name_c1f3.setText(nameImagen);

                    int fila = 1;
                    int columna = 1;

                    /*  ejercicioG2HasImagen.setIdImagen(idImagen);
                    ejercicioG2HasImagen.setFilaImagen(fila);
                    ejercicioG2HasImagen.setColumnaImagen(columna);

                    listaidImagenes.add(idImagen);
                    listafilaImagen.add(fila);
                    listacolumnaImagen.add(columna);*/

                }
                if (flag_iv_c1f4) {
                    cv_c1f4.setBackground(null);
                    cv_c1f4.setImageBitmap(response);
                    flag_iv_c1f4 = false;
                    rv_imagenesBancoDatos.setVisibility(View.GONE);
                    txt_name_c1f4.setText(nameImagen);

                    int fila = 1;
                    int columna = 1;

                    /*  ejercicioG2HasImagen.setIdImagen(idImagen);
                    ejercicioG2HasImagen.setFilaImagen(fila);
                    ejercicioG2HasImagen.setColumnaImagen(columna);

                    listaidImagenes.add(idImagen);
                    listafilaImagen.add(fila);
                    listacolumnaImagen.add(columna);*/

                }

                // iv_bank_prueba.setBackground(null);
                // iv_bank_prueba.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        });
        //request.add(imageRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(imageRequest);
    }


    //----------------------------------------------------------------------------------------------

    private void consultarListaImagenes() {


        String iddoc = "20181";
        String url_lh = Globals.url;

        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSON1ConsultarListaImagenes.php";

        url = url.replace(" ", "%20");
        //hace el llamado a la url
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        final int MY_DEFAULT_TIMEOUT = 15000;
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);//p21
        //Toast.makeText(getApplicationContext(), "web service 1111", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //progreso.hide();
        Toast.makeText(getContext(), "No se puede cone , grupo doc" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR", error.toString());
        //progreso.hide();
    }

    // si esta bien el llamado a la url entonces entra a este metodo
    @Override
    public void onResponse(final JSONObject response) {
        //progreso.hide();
        //Toast.makeText(getApplicationContext(), "Mensaje: " + response.toString(), Toast.LENGTH_SHORT).show();
        Imagen imagen = null;
        JSONArray json = response.optJSONArray("imagen");

        try {
            for (int i = 0; i < json.length(); i++) {
                imagen = new Imagen();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                // jsonObject = new JSONObject(response);
                imagen.setIdImagen(jsonObject.optInt("idImagen_Ejercicio"));
                imagen.setNameImagen(jsonObject.optString("name_Imagen_Ejercicio"));
                imagen.setRutaImagen(jsonObject.optString("ruta_Imagen_Ejercicio"));
                imagen.setLetraInicialImagen(jsonObject.optString("letra_inicial_Imagen"));
                imagen.setLetraFinalImagen(jsonObject.optString("letra_final_Imagen"));
                imagen.setCantSilabasImagen(jsonObject.optInt("cant_silabas_Imagen"));

                listaImagenes.add(imagen);

//idgrupo,namegrupo,curso_idcurso,curso_Instituto_idInstituto
            }

            ImagenUrlAdapter imagenUrlAdapter = new ImagenUrlAdapter(listaImagenes, getContext());
            imagenUrlAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String rutaImagen = listaImagenes.get(rv_imagenesBancoDatos.
                            getChildAdapterPosition(v)).getRutaImagen();

                    String nameImagen = listaImagenes.get(rv_imagenesBancoDatos.
                            getChildAdapterPosition(v)).getNameImagen();

                    int idImagen = listaImagenes.get(rv_imagenesBancoDatos.
                            getChildAdapterPosition(v)).getIdImagen();

                    cargarImagenWebService(rutaImagen, nameImagen, idImagen);

                    //Toast.makeText(getApplicationContext(), "on click: " + rutaImagen, Toast.LENGTH_LONG).show();
                    System.out.println("on click: " + rutaImagen);
                    //Toast.makeText(getApplicationContext(), "on click: " , Toast.LENGTH_LONG).show();

                }
            });

            rv_imagenesBancoDatos.setAdapter(imagenUrlAdapter);

            //Toast.makeText(getApplicationContext(), "listagrupos: " + listaGrupos.size(), Toast.LENGTH_LONG).show();
            //Log.i("size", "lista Imágenes: " + listaImagenes.get(0).getNameImagen());

            //rv_bankimages.setAdapter(gruposDocenteAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("error", response.toString());

            Toast.makeText(getContext(), "No se ha podido establecer conexión: " + response.toString(), Toast.LENGTH_LONG).show();

            //progreso.hide();
        }
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

                    rv_imagenesBancoDatos.setVisibility(View.VISIBLE);
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
