package com.example.asus.actividadbd;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.actividadbd.Constant;

import java.util.ArrayList;
import java.util.List;

public class Act1MunicipiosB extends AppCompatActivity {

    private ImageView image_scrolling_top;
    Spinner spnMunicipios;
    int idSeleccion;
    TextView txtDataMuni;
    private static Context mContext;

   // public static String[] munLatLon = new String[13];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        spnMunicipios=(Spinner) findViewById(R.id.idSpinnerMuni);
        txtDataMuni=(TextView) findViewById(R.id.idTxtDataMuni);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_scrolling);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

        image_scrolling_top = (ImageView) findViewById(R.id.image_scrolling_top);
        Glide.with(this).load(R.drawable.material_design_3).fitCenter().into(image_scrolling_top);

        //------------------------------------------------------------------------------------------
        try {
            ArrayAdapter adapDatos;
            CLListar objMuni = new CLListar();
            List<String> ListaAsignatura = new ArrayList<String>();
            ListaAsignatura = objMuni.mtdListarMunicipios(this);
            adapDatos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ListaAsignatura);
            adapDatos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnMunicipios.setAdapter(adapDatos);
        }catch (Exception e)
        {
            Toast.makeText(Act1MunicipiosB.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //-------------------------------------------------------------------------------------------
        spnMunicipios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Obtengo el Id de la opcion seleccionada en el spinner
                String[]idPaso = spnMunicipios.getSelectedItem().toString().split(" ");
                idSeleccion= Integer.parseInt(idPaso[0]);

                try {
                    mtdLlenarTxt();
                }catch (Exception e){
                    Toast.makeText(Act1MunicipiosB.this, "error descrip "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


   public void mtdLlenarTxt(){

        CLListar objListarMuni =new CLListar();
        objListarMuni.idMuni = idSeleccion;
        List<String> Lista=new ArrayList<String>();
        Lista=objListarMuni.mtdListarMunicipiosUno(this);
        //adapDatos= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Lista);
        Object[]datosMuni= Lista.toArray();
        //String infoMuni= datosMuni.toString();
        String[]infoMuni2= datosMuni[0].toString().split("@");
        txtDataMuni.setText("Descripción:"+"\n"+infoMuni2[0].toString()+"\n"+"\n"+"Población: "+"\n"+infoMuni2[1].toString()+" \n"+"\n"+"Latitud: "+"\n"+infoMuni2[2].toString()+" \n"+"\n"+"Longitud: "+"\n"+infoMuni2[3].toString()+" \n"+"\n"+"Límites: "+"\n"+infoMuni2[4].toString()+" \n"+"\n"+"Economía: "+"\n"+infoMuni2[5].toString());

    }

    /*public void mtdEscribirDataMunicipios() {
            CLRegistroData objRegistro = new CLRegistroData();
            String dataMunicipios = getString(R.string.DataMunicipios);
            String[] Municipios = dataMunicipios.split("/");
            Toast.makeText(this, "leido", Toast.LENGTH_SHORT).show();


            String[] municipioAlone;


            for (int i = 0; i < Municipios.length; i++) {

                municipioAlone = Municipios[i].split("@");

                String sqlComand = ("INSERT INTO Municipio(Nombre,Descripcion,Poblacion,Latitud,Longitud,Limites,Economia,Bandera,Escudo)" +
                        "values('" + municipioAlone[0] + "','" +
                        municipioAlone[1] + "','" +
                        municipioAlone[2] + "','" +
                        municipioAlone[3] + "','" +
                        municipioAlone[4] + "','" +
                        municipioAlone[5] + "','" +
                        municipioAlone[6] + "','" +
                        municipioAlone[7] + "','" +
                        municipioAlone[8] + "')");
                //Autoriza la escritura en DB
                if(CLBaseDatos.SQLVER==true)
                { objRegistro.Registrar(Act1MunicipiosB.this, sqlComand);}

                munLatLon[i]=(municipioAlone[0]+"@"+municipioAlone[3]+"@"+municipioAlone[4]);
            }

        CLBaseDatos.SQLVER=false;
    }*/

    }




