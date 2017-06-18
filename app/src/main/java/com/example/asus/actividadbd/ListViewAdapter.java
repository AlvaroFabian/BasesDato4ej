package com.example.asus.actividadbd;

/**
 * Created by Asus on 9/6/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    String[] municipios;
    String[] poblacion;
    int[] banderas;
    int[] escudos;

    LayoutInflater inflater;

    public ListViewAdapter(Context context, String[] titulos, int[] imagenes, int[]imagenes2, String[] txPoblacion) {
        this.context = context;
        this.municipios = titulos;
        this.banderas = imagenes;
        this.escudos= imagenes2;
        this.poblacion = txPoblacion;
    }

    @Override
    public int getCount() {
        return municipios.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView txtTitle;
        TextView txtPoblacion;
        ImageView bandera;
        ImageView escudo;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row, parent, false);

        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title);
        bandera = (ImageView) itemView.findViewById(R.id.list_row_bandera);
        txtPoblacion=(TextView) itemView.findViewById(R.id.list_row_poblacion);
        escudo=(ImageView) itemView.findViewById(R.id.list_row_escudo);

        // Capture position and set to the TextViews
        txtTitle.setText(municipios[position]);
        bandera.setImageResource(banderas[position]);
        txtPoblacion.setText(poblacion[position]);
        escudo.setImageResource(escudos[position]);


        return itemView;
    }
}