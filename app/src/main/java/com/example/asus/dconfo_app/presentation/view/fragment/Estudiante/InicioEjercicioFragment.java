package com.example.asus.dconfo_app.presentation.view.fragment.Estudiante;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.EjercicioG1;
import com.example.asus.dconfo_app.domain.model.EjercicioG2;
import com.example.asus.dconfo_app.domain.model.EjercicioG2HasImagen;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;
import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.fonico.Tipo1FonicoFragment;
import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.fonico.Tipo2FonicoFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InicioEjercicioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InicioEjercicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioEjercicioFragment extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int idEjercicio = 0;
    private int idEjercicioG2 = 0;

    private String grupo;
    private String letraInicial;

    StringRequest stringRequest;
    JsonObjectRequest jsonObjectRequest;

    private TextView txt_nameEjercicio;
    private Button btn_iniciarEjercicio;

    private EjercicioG1 ejercicioG1;
    private EjercicioG2 ejercicioG2;
    private EjercicioG2HasImagen ejercicioG2HasImagen;


    private OnFragmentInteractionListener mListener;

    public InicioEjercicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioEjercicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioEjercicioFragment newInstance(String param1, String param2) {
        InicioEjercicioFragment fragment = new InicioEjercicioFragment();
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
        View view = inflater.inflate(R.layout.fragment_inicio_ejercicio, container, false);

        idEjercicio = getArguments().getInt("idejercicio");
        grupo = getArguments().getString("grupo");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("id Ejercicio: " + idEjercicio + " - " + grupo);

        txt_nameEjercicio = (TextView) view.findViewById(R.id.txt_estudiante_nombreEjercicio);
        txt_nameEjercicio.setText("id Ejercicio: " + idEjercicio);

        btn_iniciarEjercicio = (Button) view.findViewById(R.id.btn_estudiante_iniciar_ejer);
        btn_iniciarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });

        return view;
    }

    public void cargarWebService() {

        String url_lh = Globals.url;
        // String ip = getString(R.string.ip);

        //String url = "http://192.168.0.13/proyecto_dconfo/wsJSONConsultarListaCursos.php";
        if (grupo.equals("g1")) {
            String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarEjercicio.php?idEjercicioG1=" + idEjercicio;
            url = url.replace(" ", "%20");
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);//p21
        } else if (grupo.equals("g2")) {
            String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarEjercicioFonico1.php?idEjercicioG2=" + idEjercicio;
            url = url.replace(" ", "%20");
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);//p21
        } else if (grupo.equals("g3")) {
            String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarListaEjerG2_DET.php?idejercicioG2=" + idEjercicio;
            url = url.replace(" ", "%20");
            Toast.makeText(getContext(), "consultar lista det imagenes: ", Toast.LENGTH_LONG).show();
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);//p21
        }

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
        if (grupo.equals("g1")) {//------------------------------------------------------------------------------------g1
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
                    ejercicioG1.setIdTipo(jsonObject.optInt("Tipo_idTipo"));
                    ejercicioG1.setNameEjercicio(jsonObject.optString("nameEjercicioG1"));
                    ejercicioG1.setRutaImagen(jsonObject.optString("rutaImagen_EjercicioG1"));
                    ejercicioG1.setCantidadValida(jsonObject.optInt("cantidadValidaEjercicioG1"));
                    ejercicioG1.setOracion(jsonObject.optString("oracionEjercicioG1"));
                    listaDEjerciciosg1.add(ejercicioG1);

                }
                // int ejerpos1 = listaDEjerciciosg1.get(rv_misDeberes.getChildAdapterPosition(v)).getIdEjercicio();
                System.out.println("lista tipo: " + listaDEjerciciosg1.get(0).getIdTipo());
                System.out.println("lista idEjercicio: " + listaDEjerciciosg1.get(0).getIdEjercicio());
                System.out.println("lista nameEjercicio: " + listaDEjerciciosg1.get(0).getNameEjercicio());

                int tipoEjercicio = ejercicioG1.getIdTipo();
                String oracion = ejercicioG1.getOracion();
                int cantLexemas = ejercicioG1.getCantidadValida();


                System.out.println("el tipoEjercicio: " + tipoEjercicio);

                Tipo1EstudianteFragment tipo1EstudianteFragment = new Tipo1EstudianteFragment();
                Tipo2EstudianteFragment tipo2EstudianteFragment = new Tipo2EstudianteFragment();
                //InicioEjercicioFragment inicioEjercicioFragment = new InicioEjercicioFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("idejercicio", idEjercicio);
                bundle.putInt("cantLexemas", cantLexemas);
                bundle.putString("oracion", oracion);

                if (tipoEjercicio == 3) {
                    tipo1EstudianteFragment.setArguments(bundle);
                    // inicioEjercicioFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, tipo1EstudianteFragment)
                            //getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, inicioEjercicioFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                } else if (tipoEjercicio == 4) {
                    //tipo1EstudianteFragment.setArguments(bundle);
                    tipo2EstudianteFragment.setArguments(bundle);
                    //getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, tipo1EstudianteFragment)
                    getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, tipo2EstudianteFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                }

            } catch (JSONException e) {
                e.printStackTrace();
                //Toast.makeText(getContext(), "No se ha podido establecer conexión: " + response.toString(), Toast.LENGTH_LONG).show();

            }

        } else if (grupo.equals("g2")) {//------------------------------------------------------------------------------------g2

            //Toast.makeText(getContext(), "grupo 2 activo: ", Toast.LENGTH_LONG).show();

            ejercicioG2 = null;
            JSONArray json = response.optJSONArray("ejerciciog2");

            ArrayList<EjercicioG2> listaDEjerciciosg2 = new ArrayList<>();
            listaDEjerciciosg2 = new ArrayList<>();

            try {
                for (int i = 0; i < json.length(); i++) {
                    ejercicioG2 = new EjercicioG2();
                    JSONObject jsonObject = null;
                    jsonObject = json.getJSONObject(i);

                    ejercicioG2.setIdEjercicioG2(jsonObject.optInt("idEjercicioG2"));
                    ejercicioG2.setIdTipo(jsonObject.optInt("Tipo_idTipo"));
                    ejercicioG2.setNameEjercicioG2(jsonObject.optString("nameEjercicioG2"));
                    ejercicioG2.setLetra_inicial_EjercicioG2(jsonObject.optString("letra_inicial_EjercicioG2"));
                    ejercicioG2.setLetra_final_EjercicioG2(jsonObject.optString("letra_final_EjercicioG2"));

                    listaDEjerciciosg2.add(ejercicioG2);

                }
                // int ejerpos1 = listaDEjerciciosg1.get(rv_misDeberes.getChildAdapterPosition(v)).getIdEjercicio();
              /*  System.out.println("lista tipo: " + listaDEjerciciosg1.get(0).getIdTipo());
                System.out.println("lista idEjercicio: " + listaDEjerciciosg1.get(0).getIdEjercicio());
                System.out.println("lista nameEjercicio: " + listaDEjerciciosg1.get(0).getNameEjercicio());*/

                int tipoEjercicioG2 = ejercicioG2.getIdTipo();

                idEjercicioG2 = ejercicioG2.getIdEjercicioG2();


                System.out.println("id ejericiog2: " + idEjercicioG2);

                Tipo1FonicoFragment tipo1FonicoFragment = new Tipo1FonicoFragment();
                letraInicial = ejercicioG2.getLetra_inicial_EjercicioG2();

                grupo = "g3";
                cargarWebService();


            /*    Bundle bundle = new Bundle();
                bundle.putInt("idejercicio", idEjercicio);
                bundle.putString("letrainicial", letraInicial);

                if (tipoEjercicioG2 == 1) {
                    tipo1FonicoFragment.setArguments(bundle);
                    // inicioEjercicioFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, tipo1FonicoFragment)
                            //getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, inicioEjercicioFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                }*/


            } catch (JSONException e) {
                e.printStackTrace();
                //Toast.makeText(getContext(), "No se ha podido establecer conexión: " + response.toString(), Toast.LENGTH_LONG).show();

            }


        } else if (grupo.equals("g3")) {//------------------------------------------------------------------------------------g3

            Toast.makeText(getContext(), "grupo g3 activo: ", Toast.LENGTH_LONG).show();

            ejercicioG2HasImagen = null;
            JSONArray json = response.optJSONArray("ejerg2hasimagen");

            ArrayList<EjercicioG2HasImagen> listaDEjerciciosg2HI = new ArrayList<>();
            listaDEjerciciosg2HI = new ArrayList<>();

            try {
                for (int i = 0; i < json.length(); i++) {
                    ejercicioG2HasImagen = new EjercicioG2HasImagen();
                    JSONObject jsonObject = null;
                    jsonObject = json.getJSONObject(i);

                    ejercicioG2HasImagen.setIdEjercicioG2(jsonObject.optInt("EjercicioG2_idEjercicioG2"));
                    ejercicioG2HasImagen.setIdImagen(jsonObject.optInt("Imagen_idImagen_Ejercicio"));
                    ejercicioG2HasImagen.setColumnaImagen(jsonObject.optInt("columna_E_H_I"));
                    ejercicioG2HasImagen.setFilaImagen(jsonObject.optInt("fila_E_h_I"));

                    listaDEjerciciosg2HI.add(ejercicioG2HasImagen);

                }
                int tipoEjercicioG2 = ejercicioG2.getIdTipo();
                Tipo1FonicoFragment tipo1FonicoFragment = new Tipo1FonicoFragment();
                Tipo2FonicoFragment tipo2FonicoFragment = new Tipo2FonicoFragment();

                // ArrayList<Integer> listaIDimagenes = new ArrayList<>();
                Bundle bundle = new Bundle();
                int[] listaidImagenes = new int[listaDEjerciciosg2HI.size()];
                for (int i = 0; i < listaDEjerciciosg2HI.size(); i++) {
                    // listaIDimagenes.add(listaDEjerciciosg2HI.get(i).getIdImagen());
                    listaidImagenes[i] = listaDEjerciciosg2HI.get(i).getIdImagen();
                    bundle.putInt("idejercicio" + (i + 1) + "", listaDEjerciciosg2HI.get(i).getIdImagen());
                    bundle.putInt("colejercicio" + (i + 1) + "", listaDEjerciciosg2HI.get(i).getColumnaImagen());
                    System.out.println("columna imagen: " + listaDEjerciciosg2HI.get(i).getColumnaImagen());
                    bundle.putInt("filejercicio" + (i + 1) + "", listaDEjerciciosg2HI.get(i).getFilaImagen());
                }

               /* System.out.println("cadena imagenes: " + listaidImagenes.toString());
                System.out.println("bundle1: " + bundle.get("idejercicio1"));
                System.out.println("bundle2: " + bundle.get("idejercicio2"));
                System.out.println("bundle3: " + bundle.get("idejercicio3"));
                System.out.println("bundle4: " + bundle.get("idejercicio4"));*/


                bundle.putInt("idejercicio", idEjercicio);
                bundle.putString("letrainicial", letraInicial);
                //bundle.putIntArray("cadenaidimagenes", listaidImagenes);

                if (tipoEjercicioG2 == 1) {
                    tipo1FonicoFragment.setArguments(bundle);
                    // inicioEjercicioFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, tipo1FonicoFragment)
                            //getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, inicioEjercicioFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                }

                if (tipoEjercicioG2 == 2) {
                    tipo1FonicoFragment.setArguments(bundle);
                    // inicioEjercicioFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, tipo2FonicoFragment)
                            //getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, inicioEjercicioFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                }


            } catch (JSONException e) {
                e.printStackTrace();
                //Toast.makeText(getContext(), "No se ha podido establecer conexión: " + response.toString(), Toast.LENGTH_LONG).show();

            }


        }


    }//onResponse

    //**********************************************************************************************


    //**********************************************************************************************


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
