package com.example.asus.actividadbd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 8/6/2017.
 */

public class CLListar {
    int idMuni;

    public int getIdMuni() {
        return idMuni;
    }

    public void setIdMuni(int idMuni) {
        this.idMuni = idMuni;
    }

    public List<String> mtdListarMunicipios (Context context){
        CLBaseDatos objBD= new CLBaseDatos(context,"BDActividad",null,1);
        SQLiteDatabase base= objBD.getReadableDatabase();
        Cursor cr= base.rawQuery("select IdMunicipio, Nombre from Municipio", null);
        List<String> Listado = new ArrayList<String>();

        while (cr.moveToNext()){
            Listado.add(cr.getString(0)+ " " + cr.getString(1));

        }
        return Listado;
    }



    public List<String> mtdListarMunicipiosUno (Context context){
        CLBaseDatos objBD= new CLBaseDatos(context,"BDActividad",null,1);
        SQLiteDatabase base= objBD.getReadableDatabase();
        Cursor cr= base.rawQuery("select Descripcion, Poblacion, Latitud, Longitud, Limites, Economia from Municipio where IdMunicipio="+idMuni+"", null);
        List<String> Listado = new ArrayList<String>();

        while (cr.moveToNext()){
            Listado.add(cr.getString(0)+ "@" + cr.getString(1)+ "@" + cr.getString(2)+ "@" + cr.getString(3)+ "@" + cr.getString(4)+ "@" + cr.getString(5));

        }
        return Listado;
    }


    public List<String> mtdListarSitios (Context context){
        CLBaseDatos objBD= new CLBaseDatos(context,"BDActividad",null,1);
        SQLiteDatabase base= objBD.getReadableDatabase();
        Cursor cr= base.rawQuery("select * from Sitio where IdMunicipio="+idMuni+"", null);
        List<String> Listado = new ArrayList<String>();
        Toast.makeText(context, ""+idMuni, Toast.LENGTH_SHORT).show();
        while (cr.moveToNext()){
            Listado.add(cr.getString(1)+ " " + cr.getString(2)+ " " + cr.getString(3));

        }
        return Listado;
    }

    public List<String> mtdListarCoordenadas (Context context){
        CLBaseDatos objBD= new CLBaseDatos(context,"BDActividad",null,1);
        SQLiteDatabase base= objBD.getReadableDatabase();
        Cursor cr= base.rawQuery("select * from Municipio", null);
        List<String> Listado = new ArrayList<String>();

                while (cr.moveToNext()){
            Listado.add(cr.getString(1)+ "@" + cr.getString(4)+ "@" + cr.getString(5));

        }
        return Listado;
    }

    public List<String> mtdListarBanderas (Context context){
        CLBaseDatos objBD= new CLBaseDatos(context,"BDActividad",null,1);
        SQLiteDatabase base= objBD.getReadableDatabase();
        Cursor cr= base.rawQuery("select * from Municipio", null);
        List<String> Listado = new ArrayList<String>();

        while (cr.moveToNext()){
            Listado.add(cr.getString(1)+ "@" + cr.getString(3));

        }
        return Listado;
    }









}
