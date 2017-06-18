package com.example.asus.actividadbd;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActMunicipios2 extends AppCompatActivity {
Spinner spnMuni2;
    int idSeleccion;
    ListView lvDatosMuni;
    ArrayAdapter <String>adapDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_municipios2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
spnMuni2=(Spinner) findViewById(R.id.spMunicipios);
        lvDatosMuni=(ListView) findViewById(R.id.lvDatamuni);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        try {
            ArrayAdapter adapDatos;
            CLListar objMuni = new CLListar();
            List<String> ListaAsignatura = new ArrayList<String>();
            ListaAsignatura = objMuni.mtdListarMunicipios(this);
            adapDatos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ListaAsignatura);
            adapDatos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnMuni2.setAdapter(adapDatos);
        }catch (Exception e)
        {
            Toast.makeText(ActMunicipios2.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        spnMuni2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Obtengo el Id de la opcion seleccionada en el spinner
                String[]idPaso = spnMuni2.getSelectedItem().toString().split(" ");
                idSeleccion= Integer.parseInt(idPaso[0]);

                try {
                    mtdLlenarListViw();
                }catch (Exception e){
                    Toast.makeText(ActMunicipios2.this, "error descrip "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    public void mtdLlenarListViw(){

        CLListar objListarSitio =new CLListar();
        objListarSitio.idMuni = idSeleccion;
        List<String> Lista=new ArrayList<String>();
        Lista=objListarSitio.mtdListarSitios(this);
        adapDatos= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Lista);

        lvDatosMuni.setAdapter(adapDatos);

    }
}
