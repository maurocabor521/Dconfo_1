package com.example.asus.dconfo_app.presentation.view.activity.docente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class AsignarEstudianteDeberActivity extends AppCompatActivity {
    EditText edt_idEstudiante;
    EditText edt_idEjercicio;
    Button btn_asignar;
    ProgressDialog progreso;

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
        Toast.makeText(getApplicationContext(), "id docente: " + iddocente, Toast.LENGTH_LONG).show();
        //toolbar.setLabelFor();
        this.setTitle("id docente:" + iddocente);


        progreso = new ProgressDialog(getApplicationContext());
        edt_idEstudiante = findViewById(R.id.edt_id_estudiate_deber);
        edt_idEjercicio = findViewById(R.id.edt_id_ejercicio_deber);
        btn_asignar = findViewById(R.id.btn_asignarDeber_deber);
        btn_asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();

            }
        });

    }

    //***********************************
    public void btnAceptar(View view) {
        // final String usuario = etUsuario.getText().toString();
        // final String password = etPass.getText().toString();

        // INICIAR LA CONEXION CON VOLLEY
        String url_lh = Globals.url;
        String url =
                //"http://192.168.0.13/proyecto_dconfo/wsJSONCrearCurso.php?";
                "http://" + url_lh + "/proyecto_dconfo/wsJSONAsignarDeberEstudiante.php?";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // SE EJECUTA CUANDO LA CONSULTA SALIO BIEN, SIN ERRORES
                        if (response.equals("0")) {
                            // Toast.makeText(MainActivity.this, "Datos de usuario incorrecto...", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                System.out.println("RESPUESTA DE SERVIDOR : " + response);
                                // Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                                JSONArray jsonArray = new JSONArray(response);
                                // OBTENEMOS LOS DATOS QUE DEVUELVE EL SERVIDOR
                                String nombre = jsonArray.getJSONObject(0).getString("nombre");
                                String DNI = jsonArray.getJSONObject(0).getString("dni");
                                String sexo = jsonArray.getJSONObject(0).getString("sexo");

                                // ABRIMOS UNA NUEVA ACTIVITY Y MANDAMOS LOS DATOS QUE SE VAN A MOSTRAR
                              /*  Intent intent = new Intent(MainActivity.this, PresentacionLayout.class);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("dni", DNI);
                                intent.putExtra("sexo", sexo);
                                startActivity(intent);*/
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // SE EJECUTA CUANDO ALGO SALIO MAL AL INTENTAR HACER LA CONEXION
                        //Toast.makeText(MainActivity.this, "ERROR DE CONEXION...", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // AQUI SE ENVIARAN LOS DATOS EMPAQUETADOS EN UN OBJETO MAP<clave, valor>
                Map<String, String> parametros = new HashMap<>();
                //  parametros.put("usuario", usuario);
                //  parametros.put("pass", password);
                return parametros;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);//p21
    }
    //***********************************

    private void cargarWebService() {

        //     progreso.setMessage("Cargando...");
        //     progreso.show();
        String url_lh = Globals.url;
        String url =
                //"http://192.168.0.13/proyecto_dconfo/wsJSONCrearCurso.php?";
                "http://" + url_lh + "/proyecto_dconfo/wsJSONAsignarDeberEstudiante.php?";
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
