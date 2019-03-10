package com.example.asus.dconfo_app.presentation.view.fragment.docente.tipoFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import com.example.asus.dconfo_app.domain.model.Curso;
import com.example.asus.dconfo_app.domain.model.EjercicioG1;
import com.example.asus.dconfo_app.domain.model.Grupo;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;
import com.example.asus.dconfo_app.presentation.view.activity.docente.HomeDocenteActivity;
import com.example.asus.dconfo_app.presentation.view.activity.docente.ManageCursosDocenteActivity;
import com.example.asus.dconfo_app.presentation.view.adapter.GruposDocenteAdapter;
import com.example.asus.dconfo_app.presentation.view.adapter.TipoEjerciciosDocenteAdapter;
import com.example.asus.dconfo_app.presentation.view.fragment.administrador.NewCursoFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeTiposFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeTiposFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTiposFragment extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rv_tipos;
    ArrayList<EjercicioG1> listaEjercicios;
    ProgressDialog progreso;
    //******** CONEXIÓN CON WEBSERVICE
    //RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    private OnFragmentInteractionListener mListener;

    public static HomeTiposFragment getInstance() {
        return new HomeTiposFragment();
    }

    public HomeTiposFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeTiposFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeTiposFragment newInstance(String param1, String param2) {
        HomeTiposFragment fragment = new HomeTiposFragment();
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

        View view=inflater.inflate(R.layout.fragment_home_tipos, container, false);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.home_tipo);
        rv_tipos=(RecyclerView)view.findViewById(R.id.rv_tipos_docente);
        return view;
    }



    private void cargarWebService() {

        progreso.setMessage("Cargando...");
        progreso.show();
        // String ip = getString(R.string.ip);
        //int iddoc=20181;
        String iddoc = "20181";
        String url_lh= Globals.url;

        //String url = "http://192.168.0.13/proyecto_dconfo/wsJSONConsultarListaCursosDocente.php?iddocente=" + txtiddoc.getText().toString();

        // String url = "http://"+url_lh+"/proyecto_dconfo/wsJSONConsultarListaCursosDocente.php?iddocente=" + txtiddoc.getText().toString();
        //String url = "http://"+url_lh+"/proyecto_dconfo/wsJSONConsultarListaCursosDocente.php?iddocente=" + idDocente;
        // http://localhost/proyecto_dconfo/
///wsJSONConsultarEstudiante.php?documento=" + edt_codigo.getText().toString();
       // url = url.replace(" ", "%20");
        //hace el llamado a la url
       // jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        final int MY_DEFAULT_TIMEOUT = 15000;
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);//p21
       // Toast.makeText(getContext(), "web service 1111", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
       // Toast.makeText(getContext(), "No se puede cone , grupo doc" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR", error.toString());
        progreso.hide();
    }

    // si esta bien el llamado a la url entonces entra a este metodo
    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        //Toast.makeText(getApplicationContext(), "Mensaje: " + response.toString(), Toast.LENGTH_SHORT).show();
        EjercicioG1 ejercicioG1 = null;
        JSONArray json = response.optJSONArray("ejercicioG1");

        try {
            for (int i = 0; i < json.length(); i++) {
                ejercicioG1 = new EjercicioG1();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                // jsonObject = new JSONObject(response);
                ejercicioG1.setIdEjercicio(jsonObject.optInt("idejercicio"));
                ejercicioG1.setNameEjercicio(jsonObject.optString("nameejercicio"));
                ejercicioG1.setIdDocente(jsonObject.optInt("iddocente"));
                ejercicioG1.setIdActividad(jsonObject.optInt("idactividad"));
                ejercicioG1.setIdTipo(jsonObject.optInt("idtipo"));
                listaEjercicios.add(ejercicioG1);

//idgrupo,namegrupo,curso_idcurso,curso_Instituto_idInstituto
            }
            //Toast.makeText(getApplicationContext(), "listagrupos: " + listaGrupos.size(), Toast.LENGTH_LONG).show();
            // Log.i("size", "lista: " + listaGrupos.size());
            TipoEjerciciosDocenteAdapter tipoEjerciciosDocenteAdapter = new TipoEjerciciosDocenteAdapter(listaEjercicios);

        /*    tipoEjerciciosDocenteAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getApplicationContext(), "Seleccion: " +
                            listaEjercicios.get(rv_tipos.
                                    getChildAdapterPosition(view)).getNameGrupo(), Toast.LENGTH_SHORT).show();//video p1

                    Bundle parametros = new Bundle();
                    int idgrupo = listaGrupos.get(rvListaCursos.
                            getChildAdapterPosition(view)).getIdGrupo();
                    parametros.putInt("idgrupo", idgrupo);

                    Toast.makeText(getApplicationContext(), "idgrupos: " + idgrupo, Toast.LENGTH_LONG).show();

                    int idcurso = listaGrupos.get(rvListaCursos.
                            getChildAdapterPosition(view)).getCurso_idCurso();
                    parametros.putInt("idcurso", idcurso);

                    String namegrupo = listaGrupos.get(rvListaCursos.
                            getChildAdapterPosition(view)).getNameGrupo();


                    parametros.putInt("idcurso", idcurso);

                    String idDocente1 = String.valueOf(listaGrupos.get(rvListaCursos.
                            getChildAdapterPosition(view)).getIdDocente());
                    parametros.putInt("idcurso", idcurso);

                    Intent intent = new Intent(ManageCursosDocenteActivity.this, HomeDocenteActivity.class);
                    //intent.putExtras(parametros);
                    intent.putExtra("idgrupo", idgrupo);
                    intent.putExtra("idcurso", idcurso);
                    intent.putExtra("idDoc", idDocente);
                    intent.putExtra("nameDoc", nameDocente);
                    Toast.makeText(getApplicationContext(), "id Doc MCDA: " + idDocente, Toast.LENGTH_LONG).show();
                    intent.putExtra("namegrupo", namegrupo);
                    startActivity(intent);
                  /*  interfaceComunicaFragments.
                            enviarEjercicio
                                    (listaEjercicios.get(recyclerView.getChildAdapterPosition(view)));//video p2 detalle envia el objeto completo
                                    */

               // }
          //  });
            rv_tipos.setAdapter(tipoEjerciciosDocenteAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("error", response.toString());

            //Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión: " + response.toString(), Toast.LENGTH_LONG).show();

            progreso.hide();
        }
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
