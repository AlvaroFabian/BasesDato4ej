package com.example.asus.actividadbd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Act3Municipios extends AppCompatActivity {

    ListViewAdapter adapter;
    public Object[] datosMuni;

    String[] titulo = new String[13];

    int[] imagenes = {
            R.drawable.aquitaniabandera,
            R.drawable.cuitivabandera,
            R.drawable.firavitobabandera,
            R.drawable.gamezabandera,
            R.drawable.gamezabandera,
            R.drawable.monguabandera,
            R.drawable.monguibandera,
            R.drawable.nobsabandera,
            R.drawable.pescabandera,
            R.drawable.sogamosobandera,
            R.drawable.tibasosabandera,
            R.drawable.topagabandera,
            R.drawable.totabandera
    };

    int[] imagenes2={
            R.drawable.aquitaniabandera,
            R.drawable.cuitivabandera,
            R.drawable.firavitobabandera,
            R.drawable.gamezabandera,
            R.drawable.izabandera,
            R.drawable.monguabandera,
            R.drawable.monguibandera,
            R.drawable.nobsabandera,
            R.drawable.pescabandera,
            R.drawable.sogamosobandera,
            R.drawable.tibasosabandera,
            R.drawable.topagabandera,
            R.drawable.totabandera
    };
    String[] poblacion = new String[13];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act3_municipios);
//Lectura de datos---------------------------------------------------------
        CLListar objListarMuni =new CLListar();
        List<String> Lista=new ArrayList<String>();
        Lista=objListarMuni.mtdListarBanderas(this);
        datosMuni= Lista.toArray();

        for(int i=0; i<datosMuni.length; i++)
        {
            String munLatLon[]=datosMuni[i].toString().split("@");
            titulo[i]=munLatLon[0];
            poblacion[i]=munLatLon[1];

        }



        final ListView lista = (ListView) findViewById(R.id.lvBanderaEscudo);
        adapter = new ListViewAdapter(this, titulo, imagenes, imagenes2,poblacion);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "presiono " + i, Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
