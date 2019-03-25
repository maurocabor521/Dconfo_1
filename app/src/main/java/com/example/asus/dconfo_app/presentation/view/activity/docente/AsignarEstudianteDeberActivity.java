package com.example.asus.dconfo_app.presentation.view.activity.docente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.Curso;
import com.example.asus.dconfo_app.domain.model.EjercicioG1;
import com.example.asus.dconfo_app.domain.model.Estudiante;
import com.example.asus.dconfo_app.domain.model.GrupoHasEstudiante;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsignarEstudianteDeberActivity extends AppCompatActivity {
    EditText edt_idEstudiante;
    EditText edt_idEjercicio;
    Button btn_asignar;
    ProgressDialog progreso;

    ArrayList<Estudiante> listaEstudiantes;
    ArrayList<EjercicioG1> listaEjercicios;

    Spinner spinner;
    Spinner spinnerEjercicios;
    Integer idgrupo;

    //******** CONEXIÓN CON WEBSERVICE
    //RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    StringRequest stringRequest;

    public int iddocente = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_estudiante_deber);

        Bundle datos = this.getIntent().getExtras();

        iddocente = datos.getInt("iddocente");
        idgrupo = datos.getInt("idgrupo");
        //Toast.makeText(getApplicationContext(), "id docente: " + iddocente, Toast.LENGTH_LONG).show();
        //toolbar.setLabelFor();
        this.setTitle("id docente:" + iddocente);


        progreso = new ProgressDialog(getApplicationContext());
        edt_idEstudiante = findViewById(R.id.edt_id_estudiate_deber);
        edt_idEjercicio = findViewById(R.id.edt_id_ejercicio_deber);
        btn_asignar = findViewById(R.id.btn_asignarDeber_deber);

        spinner = (Spinner) findViewById(R.id.sp_docente_AED_estudiantes);
        spinnerEjercicios = (Spinner) findViewById(R.id.sp_docente_AED_ejercicios);

        btn_asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();

            }
        });


        listarEjerciciosDocente();
        listarEstudiantes();
        //cargarEjercicios();
    }

    //***********************************
    public void listarEstudiantes() {
        // final String usuario = etUsuario.getText().toString();
        // final String password = etPass.getText().toString();

        // INICIAR LA CONEXION CON VOLLEY

        String url_lh = Globals.url;

        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarListaEstudiantesGrupoDocente.php?idgrupo=" + idgrupo;
        //String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarListaCursos.php";

        //jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, this, this);
        //VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);//p21

        //RequestQueu requestQueue = Volley.newRequestQueue(mContext);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        Curso curso = null;
                        Estudiante estudiante = null;


                        ArrayList<Curso> listaDCursos = new ArrayList<>();
                        //listaCursos1 = new ArrayList<>();

                        listaEstudiantes = new ArrayList<>();

                        // Process the JSON
                        try {
                            // Get the JSON array
                            //JSONArray array = response.getJSONArray("students");
                            JSONArray array = response.optJSONArray("grupo_h_e");

                            // Loop through the array elements
                            for (int i = 0; i < array.length(); i++) {
                                // curso = new Curso();
                                // JSONObject jsonObject = null;
                                // jsonObject = json.getJSONObject(i);

                                // Get current json object
                                JSONObject student = array.getJSONObject(i);
                                estudiante = new Estudiante();
                                JSONObject jsonObject = null;
                                jsonObject = array.getJSONObject(i);
                                estudiante.setIdestudiante(jsonObject.optInt("estudiante_idestudiante"));

                                listaEstudiantes.add(estudiante);
                            }

                            final List<String> listaStringEstudiantes = new ArrayList<>();
                            listaStringEstudiantes.add("Seleccione Id Estudiante");
                            for (int i = 0; i < listaEstudiantes.size(); i++) {
                                listaStringEstudiantes.add(listaEstudiantes.get(i).getIdestudiante().toString());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, listaStringEstudiantes);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);
                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position != 0) {
                                        edt_idEstudiante.setText(listaStringEstudiantes.get(position));
                                    } else {

                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                            Toast.makeText(getApplicationContext(), "lista estudiantes" + listaStringEstudiantes, Toast.LENGTH_LONG).show();
                            System.out.println("estudiantes size: " + listaEstudiantes.size());
                            System.out.println("estudiantes: " + listaEstudiantes.get(0).getIdestudiante());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        System.out.println();
                        Log.d("ERROR", error.toString());
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);//p21
    }
    //***********************************

    //***********************************
    public void listarEjerciciosDocente() {
        // final String usuario = etUsuario.getText().toString();
        // final String password = etPass.getText().toString();

        // INICIAR LA CONEXION CON VOLLEY

        String url_lh = Globals.url;

        //String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarListaEjerciciosDocente.php?docente_iddocente=" + iddocente;
        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSON1ConsultarListaEjercicios.php";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response

                        listaEjercicios = new ArrayList<>();

                        EjercicioG1 ejercicioG1 = null;


                        try {
                            JSONArray json = response.optJSONArray("ejerciciog1");
                            for (int i = 0; i < json.length(); i++) {
                                ejercicioG1 = new EjercicioG1();
                                JSONObject jsonObject = null;
                                jsonObject = json.getJSONObject(i);
                                ejercicioG1.setIdEjercicio(jsonObject.optInt("idEjercicioG1"));
                                ejercicioG1.setNameEjercicio(jsonObject.optString("nameEjercicioG1"));
                                ejercicioG1.setIdDocente(jsonObject.optInt("docente_iddocente"));
                                ejercicioG1.setIdTipo(jsonObject.optInt("Tipo_idTipo"));
                                ejercicioG1.setIdActividad(jsonObject.optInt("Tipo_Actividad_idActividad"));

                                listaEjercicios.add(ejercicioG1);
                            }

                            final List<String> listaStringEjercicios = new ArrayList<>();
                            listaStringEjercicios.add("Seleccione Id Ejercicio");
                            for (int i = 0; i < listaEjercicios.size(); i++) {
                                listaStringEjercicios.add(listaEjercicios.get(i).getIdEjercicio().toString());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, listaStringEjercicios);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerEjercicios.setAdapter(adapter);
                            spinnerEjercicios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position != 0) {
                                        edt_idEjercicio.setText(listaStringEjercicios.get(position));
                                    } else {

                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                           /* Toast.makeText(getApplicationContext(), "lista estudiantes" + listaStringEstudiantes, Toast.LENGTH_LONG).show();
                            System.out.println("estudiantes size: " + listaEstudiantes.size());
                            System.out.println("estudiantes: " + listaEstudiantes.get(0).getIdestudiante());*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        System.out.println();
                        Log.d("ERROR Ejercicios: ", error.toString());
                    }
                }
        );
        final int MY_DEFAULT_TIMEOUT = 15000;
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonObjectRequest);
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);//p21
    }
    //***********************************


    //***********************************
    private void cargarEjercicios() {

        String url_lh = Globals.url;

        //String url = "http://" + url_lh + "/proyecto_dconfo/wsJSONConsultarListaEjerciciosDocente.php?docente_iddocente=" + iddocente;
        //String url = "http://" + url_lh + "/proyecto_dconfo/wsJSON_Con_Ejerc_Doc.php?docente_iddocente=" + iddocente;
        String url = "http://" + url_lh + "/proyecto_dconfo/wsJSON1ConsultarListaEjercicios.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //Toast.makeText(getContext(), "Mensaje: " + response.toString(), Toast.LENGTH_SHORT).show();
                EjercicioG1 ejercicioG1 = new EjercicioG1();
                JSONArray json = response.optJSONArray("ejerciciog1");
                JSONObject jsonObject = null;
                try {
                    //objeto como tal en posicion 0
                    jsonObject = json.getJSONObject(0);
                    ejercicioG1.setIdEjercicio(jsonObject.optInt("idEjercicioG1"));
                    System.out.println("id ejercicio "+ejercicioG1.getIdEjercicio());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                edt_idEjercicio.setText(ejercicioG1.getIdEjercicio().toString());


                /*
                if (miUsuario.getImagen()!=null) {
                    campoImagen.setImageBitmap(miUsuario.getImagen());
                }else {
                    campoImagen.setImageResource(R.drawable.imagen_no_disponible);
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getContext(), "No se puede conectar" + error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                progreso.hide();
                Log.d("ERROR CE", error.toString());
            }
        });
        //request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);//p21
    }
    //***********************************


    private void cargarWebService() {

        //     progreso.setMessage("Cargando...");
        //     progreso.show();
        String url_lh = Globals.url;
        String url =
                //"http://192.168.0.13/proyecto_dconfo/wsJSONCrearCurso.php?";
                "http://" + url_lh + "/proyecto_dconfo/wsJSONAsignarDeberEstudiante.php";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//recibe respuesta del webservice,cuando esta correcto
                // progreso.hide();
                if (response.trim().equalsIgnoreCase("registra")) {
                    edt_idEstudiante.setText("");
                    edt_idEjercicio.setText("");

                    // Toast.makeText(getContext(), "Se ha cargado con éxito", Toast.LENGTH_LONG).show();
                } else {
                    // Toast.makeText(getContext(), "No se ha cargado con éxito", Toast.LENGTH_LONG).show();
                    Log.i("ERROR", "RESPONSE" + response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(getContext(), "No se ha podido conectar", Toast.LENGTH_LONG).show();
                //  progreso.hide();
            }
        }) {//enviar para metros a webservice, mediante post
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String idestudiante = edt_idEstudiante.getText().toString();
                String idejercicio = edt_idEjercicio.getText().toString();
                String iddocente1 = String.valueOf(iddocente);


                Map<String, String> parametros = new HashMap<>();
                parametros.put("estudiante_idestudiante", idestudiante);
                parametros.put("docente_iddocente", iddocente1);
                parametros.put("EjercicioG1_idEjercicioG1", idejercicio);


                return parametros;
            }
        };
        //request.add(stringRequest);
        //p25 duplicar tiempo x defecto
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);//p21

        //reemplazar espacios en blanco del nombre por %20
        // url = url.replace(" ", "%20");

        //hace el llamado a la url,no usa en p12
        /*jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);*/
    }
}
