package com.example.asus.dconfo_app.presentation.view.fragment.Estudiante;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.DeberEstudiante;
import com.example.asus.dconfo_app.domain.model.EjercicioG1;
import com.example.asus.dconfo_app.domain.model.Estudiante;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;
import com.example.asus.dconfo_app.presentation.view.adapter.DeberesEstudianteAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CasaHomeEstudianteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CasaHomeEstudianteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CasaHomeEstudianteFragment extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rv_misDeberes;
    String nameestudiante = "";
    int idestudiante = 0;
    JsonObjectRequest jsonObjectRequest;
    ArrayList<DeberEstudiante> listaDeberes;
    ArrayList<EjercicioG1> listaEjercicios;

    private int idEjercicio;

    JSONArray jsonArray1;

    private OnFragmentInteractionListener mListener;

    public CasaHomeEstudianteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CasaHomeEstudianteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CasaHomeEstudianteFragment newInstance(String param1, String param2) {
        CasaHomeEstudianteFragment fragment = new CasaHomeEstudianteFragment();
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
        View view = inflater.inflate(R.layout.fragment_casa_home_estudiante, container, false);
        listaDeberes = new ArrayList<>();

        rv_misDeberes = (RecyclerView) view.findViewById(R.id.rcv_EstudianteListaDeberes_CHE);
        rv_misDeberes.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_misDeberes.setHasFixedSize(true);

        nameestudiante = getArguments().getString("nameEstudiante");
        idestudiante = getArguments().getInt("idEstudiante");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Estudiante Home: " + nameestudiante + "id: " + idestudiante);
        cargarWebService();
       // cargarWebService1();

        return view;
    }

    private void cargarWebService() {

        String url_lh = Globals.url;

        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarListaDeberesEst.php?estudiante_idestudiante="
                + idestudiante;
     /*   String url1 = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarEjercicio.php?estudiante_idestudiante="
                + idEjercicio;*/
        // + idestudiante + "&docente_iddocente=" + 220;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        //jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, this, this);

        final int MY_DEFAULT_TIMEOUT = 15000;
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);//p21
        Toast.makeText(getContext(), "LISTA EJERCICIOS DOC.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // progreso.hide();
        // Toast.makeText(getContext(), "No se puede cone , grupo doc" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR", error.toString());
        //progreso.hide();
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(getContext(), "Mensaje: " + response.toString(), Toast.LENGTH_SHORT).show();

        DeberEstudiante deberEstudiante = null;
        EjercicioG1 ejercicioG1 = null;

        JSONArray json = response.optJSONArray("deber");

        jsonArray1 = response.optJSONArray("ejerciciog1");


        try {
            for (int i = 0; i < json.length(); i++) {
                deberEstudiante = new DeberEstudiante();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                // jsonObject = new JSONObject(response);
                deberEstudiante.setIdEjercicio(jsonObject.optInt("EjercicioG1_idEjercicioG1"));
                deberEstudiante.setIdEjercicio2(jsonObject.optInt("EjercicioG1_idEjercicioG2"));
                deberEstudiante.setTipoDeber(jsonObject.optString("tipoDeber"));
                listaDeberes.add(deberEstudiante);
            }
          /* for (int i = 0; i < jsonArray1.length(); i++) {
                ejercicioG1 = new EjercicioG1();
                JSONObject jsonObject = null;
                jsonObject = jsonArray1.getJSONObject(i);
                // jsonObject = new JSONObject(response);
                ejercicioG1.setIdTipo(jsonObject.optInt("Tipo_idTipo"));

                listaEjercicios.add(ejercicioG1);
            }*/

           // System.out.println("id tipo: "+ejercicioG1.getIdTipo());


            DeberesEstudianteAdapter deberesEstudianteAdapter = new DeberesEstudianteAdapter(listaDeberes);
            deberesEstudianteAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Log.i("size", "lista: " + listaDeberes.size());
                    int ejerpos1 = listaDeberes.get(rv_misDeberes.getChildAdapterPosition(v)).getIdEjercicio();
                    int ejerpos2 = listaDeberes.get(rv_misDeberes.getChildAdapterPosition(v)).getIdEjercicio2();

                    idEjercicio = listaDeberes.get(rv_misDeberes.getChildAdapterPosition(v)).getIdEjercicio();
                   // cargarWebService1();


               /*     try {

                        for (int i = 0; i < jsonArray1.length(); i++) {
                            ejercicioG1 = new EjercicioG1();
                            JSONObject jsonObject = null;
                            jsonObject = jsonArray1.getJSONObject(i);
                            // jsonObject = new JSONObject(response);
                            ejercicioG1.setIdTipo(jsonObject.optInt("Tipo_idTipo"));

                            listaEjercicios.add(ejercicioG1);
                        }
                    } catch (JSONException e1) {

                    }*/


                    String tipo1 = String.valueOf(ejerpos1);
                    String tipo2 = String.valueOf(ejerpos2);

                    int ejertipo = listaDeberes.get(rv_misDeberes.getChildAdapterPosition(v)).getIdEjercicio();
                    Tipo1EstudianteFragment tipo1EstudianteFragment = new Tipo1EstudianteFragment();

                    Bundle bundle = new Bundle();
                    if (!tipo1.equals("NULL")) {
                        bundle.putInt("idejercicio", ejerpos1);
                        Log.i("tipo1 :", tipo1);
                    } else if (!tipo2.equals("NULL")) {
                        bundle.putInt("idejercicio", ejerpos1);
                        Log.i("tipo2 :", tipo2);
                    }


                    // if (ejertipo == "tipo1") {
                    tipo1EstudianteFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, tipo1EstudianteFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                   /* } else {
                        Toast.makeText(getContext(), "no es tipo1"+ejertipo, Toast.LENGTH_LONG).show();
                    }*/
                }
            });

            rv_misDeberes.setAdapter(deberesEstudianteAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }//catch


    }//onResponse


    private void cargarWebService1() {

        String url_lh = Globals.url;
        idEjercicio=27;
        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarEjercicio.php?idEjercicioG1="
                + idEjercicio;
        // + idestudiante + "&docente_iddocente=" + 220;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        final int MY_DEFAULT_TIMEOUT = 15000;
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);//p21
        Toast.makeText(getContext(), "ejercicio a buscar", Toast.LENGTH_LONG).show();
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
