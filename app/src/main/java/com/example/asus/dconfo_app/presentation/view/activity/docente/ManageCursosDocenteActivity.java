package com.example.asus.dconfo_app.presentation.view.activity.docente;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.Curso;
import com.example.asus.dconfo_app.domain.model.Grupo;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.presentation.view.adapter.CursosAdapter;
import com.example.asus.dconfo_app.presentation.view.adapter.GruposDocenteAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageCursosDocenteActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    private RecyclerView rvListaCursos;
    ArrayList<Grupo> listaGrupos;
    ProgressDialog progreso;
    TextView txtiddoc;
    //******** CONEXIÓN CON WEBSERVICE
    //RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cursos_docente);
        listaGrupos = new ArrayList<>();
        rvListaCursos = (RecyclerView) findViewById(R.id.rv_misCursosDocente_MCD);
        rvListaCursos.setLayoutManager(new LinearLayoutManager(this));
        rvListaCursos.setHasFixedSize(true);
        progreso = new ProgressDialog(this);
        txtiddoc = findViewById(R.id.txt_iddocente);

        cargarWebService();
    }

    private void cargarWebService() {

        progreso.setMessage("Cargando...");
        progreso.show();
        // String ip = getString(R.string.ip);
        //int iddoc=20181;
        String iddoc = "20181";

        String url = "http://192.168.0.13/proyecto_dconfo/wsJSONConsultarListaCursosDocente.php?iddocente=" + txtiddoc.getText().toString();
///wsJSONConsultarEstudiante.php?documento=" + edt_codigo.getText().toString();
        url = url.replace(" ", "%20");
        //hace el llamado a la url
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);//p21
        Toast.makeText(getApplicationContext(), "web service", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "No se puede conectar exitosamente" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR", error.toString());
        progreso.hide();
    }

    // si esta bien el llamado a la url entonces entra a este metodo
    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "Mensaje: " + response.toString(), Toast.LENGTH_SHORT).show();
        Grupo grupo = null;
        JSONArray json = response.optJSONArray("grupo");

        try {
            for (int i = 0; i < json.length(); i++) {
                grupo = new Grupo();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                // jsonObject = new JSONObject(response);
                grupo.setIdGrupo(jsonObject.optInt("idgrupo"));
                grupo.setNameGrupo(jsonObject.optString("namegrupo"));
                grupo.setCurso_idCurso(jsonObject.optInt("curso_idcurso"));
                grupo.setIdInstituto(jsonObject.optInt("curso_Instituto_idInstituto"));
                listaGrupos.add(grupo);

//idgrupo,namegrupo,curso_idcurso,curso_Instituto_idInstituto
            }
            Toast.makeText(getApplicationContext(), "listagrupos: " + listaGrupos.size(), Toast.LENGTH_LONG).show();
            Log.i("size", "lista: " + listaGrupos.size());
            GruposDocenteAdapter gruposDocenteAdapter = new GruposDocenteAdapter(listaGrupos);

            gruposDocenteAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Seleccion: " +
                            listaGrupos.get(rvListaCursos.
                                    getChildAdapterPosition(view)).getNameGrupo(), Toast.LENGTH_SHORT).show();//video p1

                  /*  interfaceComunicaFragments.
                            enviarEjercicio
                                    (listaEjercicios.get(recyclerView.getChildAdapterPosition(view)));//video p2 detalle envia el objeto completo
                                    */

                }
            });
            rvListaCursos.setAdapter(gruposDocenteAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("error", response.toString());

            Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión: " + response.toString(), Toast.LENGTH_LONG).show();

            progreso.hide();
        }
    }


}
