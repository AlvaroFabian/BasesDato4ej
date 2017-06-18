package com.example.asus.actividadbd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
Button btnAct1,btnAct2,btnAct3,btnAct4;
   // public static String[] munLatLon = new String[13];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        btnAct1=(Button)findViewById(R.id.idBtnAct1);
        btnAct2=(Button)findViewById(R.id.idBtnAct2);
        btnAct3=(Button)findViewById(R.id.idBtnAct3);
        btnAct4=(Button)findViewById(R.id.idBtnAct4);

        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this,Act1MunicipiosB.class));
            }
        });

        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this,ActMunicipios2.class));
            }
        });
        btnAct4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CLBaseDatos.SQLVER==false)
                {
                try {
                    startActivity(new Intent(MainMenu.this, Act4Muicipios.class));
                }catch (Exception e)
                {
                    Toast.makeText(MainMenu.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                }
                else{
                    Toast.makeText(MainMenu.this, "No hay municipios registrados", Toast.LENGTH_LONG).show();

                }
            }
        });
        btnAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this,Act3Municipios.class));
            }
        });

        //-----------------------------------------------------------------------------------------
        try{
            CLBaseDatos objBD= new CLBaseDatos(this,"BDActividad",null,1);
            SQLiteDatabase base= objBD.getWritableDatabase();
            //Toast.makeText(this, "base de datos creada",Toast.LENGTH_SHORT).show();
            //  mtdEscribirDataMunicipios();
        }catch (Exception e){
            Toast.makeText(this, "error "+e.getMessage(),Toast.LENGTH_LONG).show();

        }

mtdEscribirDataMunicipios();
    }


    public void mtdEscribirDataMunicipios() {
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
            { objRegistro.Registrar(MainMenu.this, sqlComand);}

        //    munLatLon[i]=(municipioAlone[0]+"@"+municipioAlone[3]+"@"+municipioAlone[4]);
        }

        CLBaseDatos.SQLVER=false;
    }


}
