package com.example.asus.dconfo_app.presentation.view.activity.docente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.Grupo;

import java.util.ArrayList;

public class HomeDocenteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_docente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle datos = this.getIntent().getExtras();
        int idgrupo = datos.getInt("idgrupo");
        int idcurso = datos.getInt("idcurso");
        String namegrupo = datos.getString("namegrupo");
        int iddocente = datos.getInt("idDoc");
        //toolbar.setLabelFor();
        this.setTitle("Id Docente: "+iddocente+" - Id Curso: "+idcurso+" - "+namegrupo);

        Toast.makeText(getApplicationContext(), "idgrupo: " + idgrupo, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "idcurso: " + idcurso, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_docente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view1);
        Menu m = nv.getMenu();

        if (id == R.id.nav_misgrupos) {
            Intent intent = new Intent(HomeDocenteActivity.this, ManageCursosDocenteActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_actividades) {

        } else if (id == R.id.nav_misejercicios) {

        } else if (id == R.id.nav_asignardeber) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_con_fonica) {
            // Handle the camera action
            boolean b = !m.findItem(R.id.nav_con_fonica_actividades).isVisible();
            //setting submenus visible state
            m.findItem(R.id.nav_con_fonica_actividades).setVisible(b);
            m.findItem(R.id.nav_con_fonica_ejercicios).setVisible(b);
            m.findItem(R.id.nav_con_fonica_asignar).setVisible(b);
            m.findItem(R.id.nav_con_fonica_estudiantes).setVisible(b);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }//menuitem


}
