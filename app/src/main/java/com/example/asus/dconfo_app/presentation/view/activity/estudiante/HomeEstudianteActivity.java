package com.example.asus.dconfo_app.presentation.view.activity.estudiante;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.asus.dconfo_app.LoginMainEstudianteActivity;
import com.example.asus.dconfo_app.R;
import com.roughike.bottombar.BottomBar;

public class HomeEstudianteActivity extends AppCompatActivity {
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_estudiante);
        bottomBar = findViewById(R.id.bottombar_estudiante);
        showToolbar("Mis deberes ", true);
    }


  /*  public void showNotification() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

// Podrás mostrar el icono de la notificación, en este caso una alerta
        Notification notification = new Notification(android.R.drawable.stat_sys_warning,
                "Notificación", System.currentTimeMillis());

        CharSequence titulo = "Alerta";

// Clase de Notification
        Intent notificationIntent = new Intent(this, LoginMainEstudianteActivity.class);
        PendingIntent contIntent = PendingIntent.getActivity(this, , notificationIntent, );
        notification.getSettingsText(this, "Aviso de notificación", "Esto es un ejemplo de notificación", contIntent);

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

//importante
        int not_id = 1;
        notificationManager.notify(not_id, notification);
    }*/

    public void showToolbar(String tittle, boolean upButton) {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_ejercicio);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        //getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
