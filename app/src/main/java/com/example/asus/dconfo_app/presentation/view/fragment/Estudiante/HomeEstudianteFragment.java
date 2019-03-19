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
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;
import com.example.asus.dconfo_app.presentation.view.adapter.DeberesEstudianteAdapter;
import com.example.asus.dconfo_app.presentation.view.adapter.TipoEjerciciosDocenteAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeEstudianteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeEstudianteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeEstudianteFragment extends Fragment implements Response.Listener<JSONObject>,
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

    private OnFragmentInteractionListener mListener;

    public HomeEstudianteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeEstudianteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeEstudianteFragment newInstance(String param1, String param2) {
        HomeEstudianteFragment fragment = new HomeEstudianteFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_estudiante, container, false);
        listaDeberes = new ArrayList<>();

        rv_misDeberes = (RecyclerView) view.findViewById(R.id.rcv_EstudianteListaDeberes);
        rv_misDeberes.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_misDeberes.setHasFixedSize(true);

        nameestudiante = getArguments().getString("nameEstudiante");
        idestudiante = getArguments().getInt("idEstudiante");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Estudiante Home: " + nameestudiante + "id: " + idestudiante);
        cargarWebService();
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

    private void cargarWebService() {

        String url_lh = Globals.url;
        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarListaDeberesEst.php?estudiante_idestudiante="
                + idestudiante + "&docente_iddocente=" + 220;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

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

    // si esta bien el llamado a la url entonces entra a este metodo
    @Override
    public void onResponse(JSONObject response) {
//        progreso.hide();
        //Toast.makeText(getApplicationContext(), "Mensaje: " + response.toString(), Toast.LENGTH_SHORT).show();
        DeberEstudiante deberEstudiante = null;
        JSONArray json = response.optJSONArray("deber");

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

//idgrupo,namegrupo,curso_idcurso,curso_Instituto_idInstituto
            }
            //Toast.makeText(getApplicationContext(), "listagrupos: " + listaGrupos.size(), Toast.LENGTH_LONG).show();
            Log.i("size", "lista: " + deberEstudiante.toString());
            DeberesEstudianteAdapter deberesEstudianteAdapter = new DeberesEstudianteAdapter(listaDeberes);
            deberesEstudianteAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ejerpos=listaDeberes.get(rv_misDeberes.getChildAdapterPosition(v)).getIdEjercicio();
                    Tipo1EstudianteFragment tipo1EstudianteFragment = new Tipo1EstudianteFragment();

                    Bundle bundle=new Bundle();
                    bundle.putInt("idejercicio",ejerpos);
                    tipo1EstudianteFragment.setArguments(bundle);

                    getFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, tipo1EstudianteFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                }
            });

            rv_misDeberes.setAdapter(deberesEstudianteAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("error", response.toString());

            //Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión: " + response.toString(), Toast.LENGTH_LONG).show();

            //progreso.hide();
        }
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
