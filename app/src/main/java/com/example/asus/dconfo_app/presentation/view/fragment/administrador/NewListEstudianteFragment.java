package com.example.asus.dconfo_app.presentation.view.fragment.administrador;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.example.asus.dconfo_app.helpers.POIFSFileSystem;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

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
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private static final int COD_SELECCIONA = 10;
    private static final String Downloads = "Downloads";

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
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0
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
                        ruta_sd = Environment.getExternalStorageDirectory();

                        //File f = new File(ruta_sd.getAbsolutePath(), "ficherosd.txt");
                        File f = new File(ruta_sd.getAbsolutePath(), "archivo_csv.csv");


                        BufferedReader fin =
                                new BufferedReader(
                                        new InputStreamReader(
                                                new FileInputStream(f)));

                        String texto;
                        int cont = 0;
                        if ((texto = fin.readLine()) != null) {
                            cont++;
                            System.out.println("f: " + f.getAbsolutePath());
                            System.out.println("fin.readline: " + texto);
                            txt_mensaje.setText(texto);
                            Toast.makeText(getContext(), "f: " + f.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        }
                        while ((texto = fin.readLine()) == ";") {
                            cont++;
                        }

                        System.out.println("cont: " + cont);
                        String nextLine;
                        while ((nextLine = fin.readLine()) != null) {
                            // nextLine[] is an array of values from the line
                            //System.out.println(nextLine[0] + nextLine[1] + "etc...");
                            // nextLines.add(nextLine);
                            System.out.println("next lines: " + nextLine);
                        }
                        fin.close();
                        mostrarDialogOpciones();

                    } catch (Exception ex) {
                        Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
                        txt_mensaje.setText("No hay fichero, sdDisponible: " + sdDisponible + " ," +
                                "sdAccesoEscritura: " + sdAccesoEscritura + " ,RUTA: " + ruta_sd.getAbsolutePath());
                    }

                }
                break;
            case (R.id.btn_New_Write_listEstudiantes):
                if (sdAccesoEscritura && sdDisponible) {
                    try {
                        File ruta_sd = Environment.getExternalStorageDirectory();

                        f = new File(ruta_sd.getAbsolutePath(), "archivo_csv.csv");

                        OutputStreamWriter fout =
                                new OutputStreamWriter(
                                        new FileOutputStream(f));

                        fout.write("andres;");
                        fout.write("cabal;");
                        fout.write("943252561");
                        fout.write("maria;");
                        fout.write("lopez;");
                        fout.write("66757782");
                        fout.close();

                        String cadena_f = f.toString();
                        //leerArchivo(cadena_f);

                    } catch (Exception ex) {
                        Log.e("Ficheros", "Error al escribir fichero en la tarjeta SD");
                        txt_mensaje.setText("Error al escribir fichero en la tarjeta SD, f :" + f);
                    }
                }

                break;
        }//sw
    }//onclick

    private void mostrarDialogOpciones() {
        //final boolean flag1=false;
        final CharSequence[] opciones = {"Mis archivos", "Elegir de Galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Mis archivos")) {
                    //irCarpeta();
                    //insertContact("andres", "correo@correo");
                    Toast.makeText(getContext(), "RUTA" + Environment.getExternalStorageDirectory().getPath(), Toast.LENGTH_LONG).show();
                    Log.e("RUTAS", "RUTA" + Environment.getExternalStorageDirectory().getPath());

                    Intent chooser = new Intent(Intent.ACTION_GET_CONTENT);
                    Uri uri = Uri.parse(Environment.getDownloadCacheDirectory().getPath().toString());
                    chooser.addCategory(Intent.CATEGORY_OPENABLE);
                    chooser.setDataAndType(uri, "*/*");
                    startActivity(chooser);
                    try {
                        startActivityForResult(chooser, 0);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (opciones[i].equals("Elegir de Galeria")) {
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent, "Seleccione"), COD_SELECCIONA);
                        //cont = 1;
                    } else {
                        dialogInterface.dismiss();
                    }
                }
            }
        });

        builder.show();
        //return flag1;
    }

    public void insertContact(String name, String email) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void irCarpeta() {
        File root = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Download/");
        Uri uri = Uri.fromFile(root);

        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setData(uri);
        // startActivityForResult(intent, 1);
    }


    public void leerArchivo(String archivo) {
        //String csvFile = "archivo.csv";
        String csvFile = "archivo";
        BufferedReader br = null;
        String line = "";
//Se define separador ","
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                //Imprime datos.
                System.out.println(datos[0] + ", " + datos[1] + ", " + datos[2] + ", " + datos[3] + ", " + datos[4] + ", " + datos[5]);
                Toast.makeText(getContext(), "Datos: " + datos, Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
