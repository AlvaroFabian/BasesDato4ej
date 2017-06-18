package com.example.asus.actividadbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Asus on 8/6/2017.
 */

public class CLRegistroData {
    public void Registrar(Context context,String sqlComand)
    {
        try {
            //Instanciamos la clase de la base de datos para poder escribir en ella
            CLBaseDatos ObjBD = new CLBaseDatos(context, "BDActividad", null, 1);
            //Abrimos la bd en modo escritura
            SQLiteDatabase base = ObjBD.getWritableDatabase();
            //Escribimos los datos traidos en los campos de la base de datos
           // base.execSQL("delete from Municipio");//Limpieza de tabla
            base.execSQL(sqlComand);


          //  Toast.makeText(context, "Municipio registrado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al registrar "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void clean(Context context)
    {
        CLBaseDatos ObjBD = new CLBaseDatos(context, "BDActividad", null, 1);
        //Abrimos la bd en modo escritura
        SQLiteDatabase base = ObjBD.getWritableDatabase();
        //Escribimos los datos traidos en los campos de la base de datos
        // base.execSQL("delete from Municipio");//Limpieza de tabla
        //base.execSQL( "delete * from Municipio");




    }




}
