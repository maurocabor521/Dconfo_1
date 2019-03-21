package com.example.asus.dconfo_app.presentation.view.fragment.Estudiante;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.EjercicioG1;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;
import com.example.asus.dconfo_app.presentation.view.activity.docente.AsignarEstudianteDeberActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tipo1EstudianteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tipo1EstudianteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tipo1EstudianteFragment extends Fragment
        implements Response.Listener<JSONObject>,
        Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int idEjercicio = 0;

    private ImageView iv_imagen;
    private Button btn_bell;
    private Button mButtonSpeak;
    private Button btn_b1;
    private Button btn_b2;
    private Button btn_b3;
    private Button btn_b4;
    private Button btn_b5;
    private TextToSpeech mTTS;

    private SeekBar mSeekBarPitch;
    private SeekBar mSeekBarSpeed;

    private String textOracion;

    String urlImagen;

    ArrayList<EjercicioG1> listaEjerciciosg1;
    Context context;
    View view;
    Integer idejercicio;
    List<String> listaNombreEjerciciog1;
    List<Integer> listaidEjerciciog1;
    EjercicioG1 ejercicioG1;

    StringRequest stringRequest;
    JsonObjectRequest jsonObjectRequest;

    private OnFragmentInteractionListener mListener;

    public Tipo1EstudianteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tipo1EstudianteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tipo1EstudianteFragment newInstance(String param1, String param2) {
        Tipo1EstudianteFragment fragment = new Tipo1EstudianteFragment();
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
        View view = inflater.inflate(R.layout.fragment_tipo1_estudiante, container, false);
        idEjercicio = getArguments().getInt("idejercicio");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("id Ejercicio: " + idEjercicio);

        iv_imagen = (ImageView)view.findViewById(R.id.iv_estudiante_tipo1);
        mSeekBarPitch = (SeekBar) view.findViewById(R.id.seek_bar_pitch_estudiante);
        mSeekBarSpeed = (SeekBar) view.findViewById(R.id.seek_bar_speed_estudiante);

        mButtonSpeak = (Button) view.findViewById(R.id.btn_estudiante_tipo1_textToS);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        mTTS = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    //int result = mTTS.setLanguage(Locale.getDefault());
                    int result = mTTS.setLanguage(new Locale("spa", "ESP"));

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        // mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        iv_imagen = (ImageView) view.findViewById(R.id.iv_estudiante_tipo1);

        btn_bell = (Button) view.findViewById(R.id.btn_estudiante_bell);
        btn_b1 = (Button) view.findViewById(R.id.btn_estudiante_b1);
        btn_b2 = (Button) view.findViewById(R.id.btn_estudiante_b2);
        btn_b3 = (Button) view.findViewById(R.id.btn_estudiante_b3);
        btn_b4 = (Button) view.findViewById(R.id.btn_estudiante_b4);
        btn_b5 = (Button) view.findViewById(R.id.btn_estudiante_b5);

        idEjercicio = getArguments().getInt("idejercicio");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("id Ejercicio: " + idEjercicio);

        cargarWebService();

        return view;
    }


    private void speak() {
        // String text = edt_OrtacionEjercicio.getText().toString();
        //String text = edt_OrtacionEjercicio.getText().toString();
        float pitch = (float) mSeekBarPitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) mSeekBarSpeed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(textOracion, TextToSpeech.QUEUE_FLUSH, null);
        System.out.println("oración: " + textOracion);
    }


    public void cargarWebService() {

        String url_lh = Globals.url;
        // String ip = getString(R.string.ip);

        //String url = "http://192.168.0.13/proyecto_dconfo/wsJSONConsultarListaCursos.php";
        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarEjercicio.php?idEjercicioG1=" + idEjercicio;
        //String url = ip+"ejemploBDRemota/wsJSONConsultarLista.php";
        //reemplazar espacios en blanco del nombre por %20
        url = url.replace(" ", "%20");
        //hace el llamado a la url
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(jsonObjectRequest);//p21
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
        ejercicioG1 = null;
        JSONArray json = response.optJSONArray("ejerciciog1");

        ArrayList<EjercicioG1> listaDEjerciciosg1 = new ArrayList<>();
        listaDEjerciciosg1 = new ArrayList<>();

        try {
            for (int i = 0; i < json.length(); i++) {
                ejercicioG1 = new EjercicioG1();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                ejercicioG1.setIdEjercicio(jsonObject.optInt("idEjercicioG1"));
                ejercicioG1.setNameEjercicio(jsonObject.optString("nameEjercicioG1"));
                ejercicioG1.setRutaImagen(jsonObject.optString("rutaImagen_EjercicioG1"));
                ejercicioG1.setCantidadValida(jsonObject.optInt("cantidadValidaEjercicioG1"));
                ejercicioG1.setOracion(jsonObject.optString("oracionEjercicioG1"));
                listaDEjerciciosg1.add(ejercicioG1);

            }
            textOracion = ejercicioG1.getOracion();

            String url_lh = Globals.url;
            final String rutaImagen = ejercicioG1.getRutaImagen();

            urlImagen = "http://" +url_lh + "/proyecto_dconfo/" + rutaImagen;
            urlImagen = urlImagen.replace(" ", "%20");

            ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    //holder.imagen.setImageBitmap(response);
                    iv_imagen.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                    System.out.println("ruta imagen: "+urlImagen);
                }
            });
            VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(imageRequest);
            //iv_imagen.setBackground();

         /*   Spinner spinner = (Spinner) this.view.findViewById(R.id.sp_Ejercicios_asignar);
            listaNombreEjerciciog1 = new ArrayList<>();
            listaidEjerciciog1 = new ArrayList<>();*/
            //listaNombreEjerciciog1.add(" ");

       /*     for (int i = 0; i < listaDEjerciciosg1.size(); i++) {

                listaNombreEjerciciog1.add(String.valueOf(listaDEjerciciosg1.get(i).getNameEjercicio()));
                listaidEjerciciog1.add(listaDEjerciciosg1.get(i).getIdEjercicio());

                System.out.println("Ejercicio:" + i + listaDEjerciciosg1.get(i).getIdEjercicio());
            }*/
         /*   ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listaNombreEjerciciog1);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("ejericio seleccionado: "+listaNombreEjerciciog1.get(position));
                    AsignarEstudianteDeberActivity asignarEstudianteDeberActivity=new AsignarEstudianteDeberActivity();
                    //asignarEstudianteDeberActivity.
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/


            // System.out.println("la lista Ejercicios:" + listaNombreEjerciciog1.size());

        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText(getContext(), "No se ha podido establecer conexión: " + response.toString(), Toast.LENGTH_LONG).show();

        }
    }//onResponse


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
