package com.example.asus.actividadbd;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Act4Muicipios extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
public Object[] datosMuni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act4_muicipios);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        int verificar = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(verificar== ConnectionResult.SUCCESS)
        {
            SupportMapFragment fragMap=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
            fragMap.getMapAsync(this);
        }else {
            Toast.makeText(this, "Error al verificar servicios", Toast.LENGTH_SHORT).show();

        }

        //Lectura coordenadas desde base de datos
        CLListar objListarMuni =new CLListar();
        List<String> Lista=new ArrayList<String>();
        Lista=objListarMuni.mtdListarCoordenadas(this);
        datosMuni= Lista.toArray();

        //Verificacion de permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }



    }
    //Iniciamos los servicios de localizacion
    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(Act4Muicipios.this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        //verificamos los permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
    }
    //Comsulta permisos nadie lo llama
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }
    //Obtenemos la direccion
    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                   // mensaje2.setText("Mi direccion es: \n"
                     ///       + DirCalle.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        Act4Muicipios mainActivity;
        boolean miPos=false;
        public Act4Muicipios getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(Act4Muicipios mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();


            //mensaje1.setText(Text);
            Act4Muicipios.this.setLocation(loc);

            LatLng iPosition = new LatLng(loc.getLatitude(), loc.getLongitude());

            if(!miPos)
            {
                mMap.addMarker(new MarkerOptions().position(iPosition).title("Mi ubicacion").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_person)));
             miPos=true;
                Toast.makeText(mainActivity, "Mi ubicacion actual es: " + "\n Lat = " + loc.getLatitude() + "\n Long = " + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            }

           // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iPosition,zoom));




        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
          //  mensaje1.setText("GPS Desactivado");
            Toast.makeText(mainActivity, "GPS desactivado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            //mensaje1.setText("GPS Activado");
            Toast.makeText(Act4Muicipios.this, "GPS Activo", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

try{


            mMap = googleMap;

            //Seleccionamos el tipode mapa
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            //habilitar utilidades google maps
            UiSettings utilidades = mMap.getUiSettings();
            utilidades.setZoomControlsEnabled(true);
            //utilidades.setMyLocationButtonEnabled(true);
            utilidades.setMyLocationButtonEnabled(true);
    utilidades.isMyLocationButtonEnabled();

            float zoom = 10;
    if(datosMuni.length==0)
    {
        Toast.makeText(this, "No hay datos registrados", Toast.LENGTH_SHORT).show();
    }else{


            for(int i=0; i<datosMuni.length; i++) {

                String munLatLon[]=datosMuni[i].toString().split("@");
                // Add a marker in Sydney and move the camera
                LatLng indumil = new LatLng(Double.parseDouble(munLatLon[1]), Double.parseDouble(munLatLon[2]));
                mMap.addMarker(new MarkerOptions().position(indumil).title(munLatLon[0]));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(indumil, zoom));



            }}



    }catch (Exception E){
    Toast.makeText(this, ""+E.getMessage(), Toast.LENGTH_SHORT).show();
}
}

    @Override
    public void onBackPressed()
    {
        // Añade más funciones si fuese necesario
        super.onBackPressed();  // Invoca al método
        finish();
        Toast.makeText(this, "atras", Toast.LENGTH_SHORT).show();
    }




}
