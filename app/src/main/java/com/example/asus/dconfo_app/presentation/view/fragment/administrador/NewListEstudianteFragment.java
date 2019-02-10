package com.example.asus.dconfo_app.presentation.view.fragment.administrador;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.dconfo_app.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewListEstudianteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewListEstudianteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewListEstudianteFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS =0 ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btn_New_L_Estudiantes;
    private Button btn_Write_New_L_Estudiantes;
    private ListView lv_lista_est;
    private TextView txt_mensaje;
    private Context context;
    boolean sdDisponible = false;
    boolean sdAccesoEscritura = false;
    File ruta_sd;
    File f;

    public static NewListEstudianteFragment getInstance() {
        return new NewListEstudianteFragment();
    }

    private OnFragmentInteractionListener mListener;

    public NewListEstudianteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewListEstudianteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewListEstudianteFragment newInstance(String param1, String param2) {
        NewListEstudianteFragment fragment = new NewListEstudianteFragment();
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
        View view = inflater.inflate(R.layout.fragment_new_list_estudiante, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.new_list_estudiante);

        btn_New_L_Estudiantes = (Button) view.findViewById(R.id.btn_New_listEstudiantes);
        btn_Write_New_L_Estudiantes = (Button) view.findViewById(R.id.btn_New_Write_listEstudiantes);

        btn_New_L_Estudiantes.setOnClickListener(this);
        btn_Write_New_L_Estudiantes.setOnClickListener(this);

        lv_lista_est = (ListView) view.findViewById(R.id.lv_NewListEstudiantes);
        txt_mensaje = (TextView) view.findViewById(R.id.txt_mensaje_list_est);
        context = getContext();
        // Código que me comprueba si existe SD y si puedo escribir o no
        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponible = true;
            sdAccesoEscritura = false;
        } else {
            sdDisponible = false;
            sdAccesoEscritura = false;
        }

        //permison en tiempo de ejecución
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

        } else {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0
                    );

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn_New_listEstudiantes):

                if (sdDisponible) {

                    try {
                        ruta_sd= Environment.getExternalStorageDirectory();

                        File f = new File(ruta_sd.getAbsolutePath(), "ficherosd.txt");

                        BufferedReader fin =
                                new BufferedReader(
                                        new InputStreamReader(
                                                new FileInputStream(f)));

                        String texto = fin.readLine();
                        txt_mensaje.setText(texto);
                        fin.close();
                    } catch (Exception ex) {
                        Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
                        txt_mensaje.setText("No hay fichero, sdDisponible: "+sdDisponible+" ," +
                                "sdAccesoEscritura: "+sdAccesoEscritura+" ,RUTA: "+ruta_sd.getAbsolutePath());
                    }

                }
                break;
            case (R.id.btn_New_Write_listEstudiantes):
                if(sdAccesoEscritura && sdDisponible){
                    try
                    {
                        File ruta_sd = Environment.getExternalStorageDirectory();

                         f= new File(ruta_sd.getAbsolutePath(), "ficherosd.txt");

                        OutputStreamWriter fout =
                                new OutputStreamWriter(
                                        new FileOutputStream(f));

                        fout.write("Contenido del fichero de la SD");
                        fout.close();
                    }
                    catch (Exception ex)
                    {
                        Log.e("Ficheros", "Error al escribir fichero en la tarjeta SD");
                        txt_mensaje.setText("Error al escribir fichero en la tarjeta SD, f :"+f);
                    }
                }

                break;
        }//sw
    }//onclick

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
