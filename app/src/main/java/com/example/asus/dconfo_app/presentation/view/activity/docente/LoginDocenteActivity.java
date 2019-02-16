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
import com.example.asus.dconfo_app.domain.model.Login;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginDocenteActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    private EditText edt_email;
    private EditText edt_pass;
    private Button btn_ingresar;
    private int iddconte_bundle=0;
    ProgressDialog progreso;

    //******** CONEXIÓN CON WEBSERVICE
    //RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = (EditText) findViewById(R.id.edt_login_doc_email);
        edt_pass = (EditText) findViewById(R.id.edt_login_doc_password);
        btn_ingresar = (Button) findViewById(R.id.btn_Login_Docente);
        progreso = new ProgressDialog(this);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });
    }

    private void cargarWebService() {

        progreso.setMessage("Cargando...");
        progreso.show();
        String url =
                "http://192.168.0.13/proyecto_dconfo/wsJSONLogin.php?";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//recibe respuesta del webservice,cuando esta correcto
                progreso.hide();
                if (response.trim().equalsIgnoreCase("registra")) {
                //if (response.contentEquals("registra")) {
                    edt_email.setText("");
                    edt_pass.setText("");
                    //Login miLogin = new Login();

                    Bundle parmetros = new Bundle();
                    parmetros.putInt("iddocente", iddconte_bundle);

                    Intent intent = new Intent(LoginDocenteActivity.this, HomeDocenteActivity.class);
                   // intent.putExtras(parmetros);
                    startActivity(intent);


                    Toast.makeText(getApplicationContext(), "Se ha cargado con éxito", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "iddocente: "+iddconte_bundle, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No se ha cargado con éxito", Toast.LENGTH_LONG).show();
                    Log.i("ERROR", "RESPONSE" + response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No se ha podido conectar", Toast.LENGTH_LONG).show();
                // progreso.hide();
            }
        }) {//enviar para metros a webservice, mediante post
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String email = edt_email.getText().toString();
                String password = edt_pass.getText().toString();

                Map<String, String> parametros = new HashMap<>();
                parametros.put("email", email);
                parametros.put("password", password);

                return parametros;
            }
        };
        //request.add(stringRequest);
        //p25 duplicar tiempo x defecto
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);//p21
    }

    public void enviarIdDocente(View view){

      /* tv1.setText("1");

        String datos = tv1.getText().toString();

        Bundle parmetros = new Bundle();
        parmetros.putString("datos", datos);

        Intent i = new Intent(this, MainActivity.class);
        i.putExtras(parmetros);
        startActivity(i);*/
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        //Toast.makeText(getContext(), "Mensaje: " + response.toString(), Toast.LENGTH_SHORT).show();
        Login login = new Login();
        JSONArray json = response.optJSONArray("login");
        JSONObject jsonObject = null;
        try {
            //objeto como tal en posicion 0
            jsonObject = json.getJSONObject(0);
            login.setIddocente(jsonObject.optInt("iddocente"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        iddconte_bundle=login.getIddocente();
        Toast.makeText(getApplicationContext(), "Login: "+login.getIddocente(), Toast.LENGTH_LONG).show();
        Log.e("info","info: "+login.getIddocente());
    }
}
