package com.example.asus.actividadbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Asus on 8/6/2017.
 */

public class CLBaseDatos extends SQLiteOpenHelper  {
    public static boolean SQLVER;
    public CLBaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // Act1MunicipiosB.mtdEscribirDataMunicipios();
        //CREAMOS TABLA TIPOSISTIO
        SQLVER=true;
        db.execSQL("CREATE TABLE TipoSitio (\n" +
                "    IdTipoSitio INTEGER       PRIMARY KEY AUTOINCREMENT,\n" +
                "    TipoSitio   VARCHAR (30),\n" +
                "    Descripcion VARCHAR (200) \n" +
                ")");

        //CARGAMOS LA TABLA TIPOSITIO
        db.execSQL("Insert into TipoSitio(TipoSitio, Descripcion)\n"+
                "values('Turistico','Sitio representativo de la ciudad')");
        db.execSQL("Insert into TipoSitio(TipoSitio, Descripcion)\n"+
                "values('Restaurante','Sitio para comidas')");
        db.execSQL("Insert into TipoSitio(TipoSitio, Descripcion)\n"+
                "values('Hotel','Sitio para hospedaje')");
        db.execSQL("Insert into TipoSitio(TipoSitio, Descripcion)\n"+
                "values('Cultural','Sitio para distraccones')");

        //CREAMOS TABLA SITIO
        db.execSQL("CREATE TABLE Sitio (\n" +
                "    IdSitio     INTEGER      PRIMARY KEY AUTOINCREMENT,\n" +
                "    Nombre      VARCHAR (50),\n" +
                "    Direccion   VARCHAR (50),\n" +
                "    Telefono    VARCHAR (50),\n" +
                "    IdTipoSitio INTEGER      REFERENCES TipoSitio (IdTipoSitio),\n" +
                "    IdMunicipio INTEGER      REFERENCES Municipio (IdMunicipio) \n" +
                ")");

        //CARGAMOS LA TABLA SITIO
        db.execSQL("Insert into Sitio(Nombre, Direccion, Telefono, IdTipoSitio, IdMunicipio)\n"+
                "values('Hotel donde Martha','Carrera 11 # 6 - 35','3053006739',3,2)");
        db.execSQL("Insert into Sitio(Nombre, Direccion, Telefono, IdTipoSitio, IdMunicipio)\n"+
                "values('Hotel la Reina','Carrera 11 # 6 - 30','3133814875',2,1)");
        db.execSQL("Insert into Sitio(Nombre, Direccion, Telefono, IdTipoSitio, IdMunicipio)\n"+
                "values('Monte Zinaí','Casco urbano','7777777',1,2)");
        db.execSQL("Insert into Sitio(Nombre, Direccion, Telefono, IdTipoSitio, IdMunicipio)\n"+
                "values('Cancha de tejo la Juana','Carrera 1 # 6 - 15','3136666666',4,2)");
        db.execSQL("Insert into Sitio(Nombre, Direccion, Telefono, IdTipoSitio, IdMunicipio)\n"+
                "values('Parque principal','Calle 10 # 10 - 10','0000000',1,3)");


        //CREAMOS TABLA MUNICIPIO
        db.execSQL("CREATE TABLE Municipio (\n" +
                "    IdMunicipio INTEGER       PRIMARY KEY AUTOINCREMENT,\n" +
                "    Nombre      VARCHAR (50),\n" +
                "    Descripcion VARCHAR (200),\n" +
                "    Poblacion   VARCHAR (10),\n" +
                "    Latitud     VARCHAR (50),\n" +
                "    Longitud     VARCHAR (50),\n" +
                "    Limites     VARCHAR (20),\n" +
                "    Economia    VARCHAR (20),\n" +
                "    Bandera     VARCHAR (100),\n" +
                "    Escudo      VARCHAR (100) \n" +
                ")");

        //CARGAMOS LA TABLA MUNICIPIO
      //  db.execSQL("Insert into Municipio(Nombre, Descripcion, Poblacion, Latitud, Longitud, Limites, Economia, Bandera, Escudo)\n"+
              //  "values('Sogamoso','Ciudad del Sol y del Acero','112287','5.715833º','-72.933333º','Nobsa','Agroindultrial','null','null')")


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    }


