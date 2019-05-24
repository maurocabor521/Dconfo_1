package com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.silabico;

import android.content.Context;
import android.graphics.Bitmap;
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
 * {@link Tipo2silabicoEstudianteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tipo2silabicoEstudianteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tipo2silabicoEstudianteFragment extends Fragment
        implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CircleImageView cv_est_st2_c1f1;
    private CircleImageView cv_est_st2_c1f2;
    private CircleImageView cv_est_st2_c1f3;
    private CircleImageView cv_est_st2_c1f4;

    private CircleImageView cv_est_st2_c2f1;
    private CircleImageView cv_est_st2_c2f2;
    private CircleImageView cv_est_st2_c2f3;
    private CircleImageView cv_est_st2_c2f4;


    private TextView txt_resultado;

    private LinearLayout ll_cv_c1f1;
    private LinearLayout ll_cv_c1f2;
    private LinearLayout ll_cv_c1f3;
    private LinearLayout ll_cv_c1f4;

    private LinearLayout ll_cv_c2f1;
    private LinearLayout ll_cv_c2f2;
    private LinearLayout ll_cv_c2f3;
    private LinearLayout ll_cv_c2f4;

    private Button btn_enviar_est_st2;

    private int idImagen1;
    private int idImagen2;
    private int idImagen3;
    private int idImagen4;
    private int idImagen5;
    private int idImagen6;
    private int idImagen7;
    private int idImagen8;

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


    private boolean cv_c2f1_isactived = false;
    private boolean cv_c2f2_isactived = false;
    private boolean cv_c2f3_isactived = false;
    private boolean cv_c2f4_isactived = false;

    private boolean cv_c2f1_desactivado = false;
    private boolean cv_c2f2_desactivado = false;
    private boolean cv_c2f3_desactivado = false;
    private boolean cv_c2f4_desactivado = false;

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

    private boolean resPareja1 = false;
    private boolean resPareja2 = false;
    private boolean resPareja3 = false;
    private boolean resPareja4 = false;

    ArrayList<Integer> listaIdImagens;
    ArrayList<Integer> listafilImagenes;
    ArrayList<Integer> listacolImagenes;

    ArrayList<Integer> listaIdImagenes;

    ArrayList<Integer> pareja_1;
    ArrayList<Integer> pareja_2;
    ArrayList<Integer> pareja_3;
    ArrayList<Integer> pareja_4;

    private String letraInicialc1f1;
    private String letraFinalc1f1;

    private String letraInicialc1f2;
    private String letraFinalc1f2;

    private String letraInicialc1f3;
    private String letraFinalc1f3;

    private String letraInicialc1f4;
    private String letraFinalc1f4;

    private String letraInicial;
    private String letraFinal;


    ArrayList<String> listaLetras;
    ArrayList<Integer> listafilLetras;
    ArrayList<Integer> listacolLetras;

    private Imagen imagen;
    String urlImagen;
    int f = -1;

    StringRequest stringRequest;
    JsonObjectRequest jsonObjectRequest;

    private OnFragmentInteractionListener mListener;

    public Tipo2silabicoEstudianteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tipo2silabicoEstudianteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tipo2silabicoEstudianteFragment newInstance(String param1, String param2) {
        Tipo2silabicoEstudianteFragment fragment = new Tipo2silabicoEstudianteFragment();
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
        View view = inflater.inflate(R.layout.fragment_tipo2silabico_estudiante, container, false);

        cv_est_st2_c1f1 = (CircleImageView) view.findViewById(R.id.iv_estudiante_sil2_c1_f1);
        cv_est_st2_c1f2 = (CircleImageView) view.findViewById(R.id.iv_estudiante_sil2_c1_f2);
        cv_est_st2_c1f3 = (CircleImageView) view.findViewById(R.id.iv_estudiante_sil2_c1_f3);
        cv_est_st2_c1f4 = (CircleImageView) view.findViewById(R.id.iv_estudiante_sil2_c1_f4);

        cv_est_st2_c2f1 = (CircleImageView) view.findViewById(R.id.iv_estudiante_sil2_c2_f1);
        cv_est_st2_c2f2 = (CircleImageView) view.findViewById(R.id.iv_estudiante_sil2_c2_f2);
        cv_est_st2_c2f3 = (CircleImageView) view.findViewById(R.id.iv_estudiante_sil2_c2_f3);
        cv_est_st2_c2f4 = (CircleImageView) view.findViewById(R.id.iv_estudiante_sil2_c2_f4);

        ll_cv_c1f1 = (LinearLayout) view.findViewById(R.id.ll_estudiante_sil2_c1_f1);
        ll_cv_c1f2 = (LinearLayout) view.findViewById(R.id.ll_estudiante_sil2_c1_f2);
        ll_cv_c1f3 = (LinearLayout) view.findViewById(R.id.ll_estudiante_sil2_c1_f3);
        ll_cv_c1f4 = (LinearLayout) view.findViewById(R.id.ll_estudiante_sil2_c1_f4);

        ll_cv_c2f1 = (LinearLayout) view.findViewById(R.id.ll_estudiante_sil2_c2_f1);
        ll_cv_c2f2 = (LinearLayout) view.findViewById(R.id.ll_estudiante_sil2_c2_f2);
        ll_cv_c2f3 = (LinearLayout) view.findViewById(R.id.ll_estudiante_sil2_c2_f3);
        ll_cv_c2f4 = (LinearLayout) view.findViewById(R.id.ll_estudiante_sil2_c2_f4);

        btn_enviar_est_st2 = (Button) view.findViewById(R.id.btn_estudiante_sil2_enviar);
        btn_enviar_est_st2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pareja_1 = new ArrayList<>();
        pareja_2 = new ArrayList<>();
        pareja_3 = new ArrayList<>();
        pareja_4 = new ArrayList<>();

        listaIdImagenes = new ArrayList<>();

        idImagen1 = getArguments().getInt("idejercicio1");
        listaIdImagenes.add(idImagen1);
        System.out.println("id imagen 1: " + idImagen1);
        idImagen2 = getArguments().getInt("idejercicio2");
        listaIdImagenes.add(idImagen2);
        System.out.println("id imagen 2: " + idImagen2);
        idImagen3 = getArguments().getInt("idejercicio3");
        listaIdImagenes.add(idImagen3);
        System.out.println("id imagen 3: " + idImagen3);
        idImagen4 = getArguments().getInt("idejercicio4");
        listaIdImagenes.add(idImagen4);
        System.out.println("id imagen 4: " + idImagen4);
        idImagen5 = getArguments().getInt("idejercicio5");
        listaIdImagenes.add(idImagen5);
        System.out.println("id imagen 5: " + idImagen5);
        idImagen6 = getArguments().getInt("idejercicio6");
        listaIdImagenes.add(idImagen6);
        System.out.println("id imagen 6: " + idImagen6);
        idImagen7 = getArguments().getInt("idejercicio7");
        listaIdImagenes.add(idImagen7);
        System.out.println("id imagen 7: " + idImagen7);
        idImagen8 = getArguments().getInt("idejercicio8");
        listaIdImagenes.add(idImagen8);
        System.out.println("id imagen 8: " + idImagen8);

       // listaIdImagens
        listaIdImagens = new ArrayList<>();
        listacolImagenes = new ArrayList<>();
        listafilImagenes = new ArrayList<>();

        listaIdImagens.add(idImagen1);
        listaIdImagens.add(idImagen2);
        listaIdImagens.add(idImagen3);
        listaIdImagens.add(idImagen4);
        listaIdImagens.add(idImagen5);
        listaIdImagens.add(idImagen6);
        listaIdImagens.add(idImagen7);
        listaIdImagens.add(idImagen8);

        llamarWebService();

        return view;
    }

    //----------------------------------------------------------------------------------------------

    public void cargarWebService(int idejercicio) {

        //if ()

        String url_lh = Globals.url;
        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarImagen.php?idImagen_Ejercicio=" + idejercicio;
        url = url.replace(" ", "%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);//p21

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
            letraInicial = imagen.getLetraInicialImagen();
            letraFinal = imagen.getLetraFinalImagen();

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
                        cv_est_st2_c1f1.setBackground(null);
                        cv_est_st2_c1f1.setImageBitmap(response);


                    } else if (f == 1) {
                        //letraf1_c2 = imagen.getLetraInicialImagen();
                        cv_est_st2_c1f2.setBackground(null);
                        cv_est_st2_c1f2.setImageBitmap(response);

                    } else if (f == 2) {
                        //letraf1_c3 = imagen.getLetraInicialImagen();
                        cv_est_st2_c1f3.setBackground(null);
                        cv_est_st2_c1f3.setImageBitmap(response);

                    } else if (f == 3) {
                        //letraf1_c4 = imagen.getLetraInicialImagen();
                        cv_est_st2_c1f4.setBackground(null);
                        cv_est_st2_c1f4.setImageBitmap(response);

                    } else if (f == 4) {
                        //letraf1_c1 = imagen.getLetraInicialImagen();
                        //System.out.println("  letraf1_c1: " + letraf1_c1);
                        cv_est_st2_c2f1.setBackground(null);
                        cv_est_st2_c2f1.setImageBitmap(response);


                    } else if (f == 5) {
                        //letraf1_c2 = imagen.getLetraInicialImagen();
                        cv_est_st2_c2f2.setBackground(null);
                        cv_est_st2_c2f2.setImageBitmap(response);

                    } else if (f == 6) {
                        //letraf1_c3 = imagen.getLetraInicialImagen();
                        cv_est_st2_c2f3.setBackground(null);
                        cv_est_st2_c2f3.setImageBitmap(response);

                    } else if (f == 7) {
                        //letraf1_c4 = imagen.getLetraInicialImagen();
                        cv_est_st2_c2f4.setBackground(null);
                        cv_est_st2_c2f4.setImageBitmap(response);

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
    public void llamarWebService() {
        //for (int i = 0; i < listaIdImagens.size(); i++) {
        if (f < 7) {
            f++;
            System.out.println("valor f: " + f);
            cargarWebService(listaIdImagens.get(f));
        }


        //}
    }

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

    @Override
    public void onClick(View v) {

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
