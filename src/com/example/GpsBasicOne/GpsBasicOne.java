package com.example.GpsBasicOne;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class GpsBasicOne extends Activity implements LocationListener {
  private LocationManager locationManager;
  private Button button;
  private TextView text;
  private String maString;
  private int monCpt;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    addListenerOnButton();
    monCpt = 0;
    text = (TextView) findViewById(R.id.textOne);
            /********** get Gps location service LocationManager object ***********/
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            /* CAL METHOD requestLocationUpdates */

              // Parameters :
              //   First(provider)    :  the name of the provider with which to register
              //   Second(minTime)    :  the minimum time interval for notifications,
              //                         in milliseconds. This field is only used as a hint
              //                         to conserve power, and actual time between location
              //                         updates may be greater or lesser than this value.
              //   Third(minDistance) :  the minimum distance interval for notifications, in meters
              //   Fourth(listener)   :  a {#link LocationListener} whose onLocationChanged(Location)
              //                         method will be called for each location update
       locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
         3000,   // 3 sec
         10, this);
            /********* After registration onLocationChanged method  ********/
            /********* called periodically after each 3 sec ***********/
  }

        /************* Called after each 3 sec **********/
  @Override
  public void onLocationChanged(Location location) {
          String str = "Lat: "+location.getLatitude()+" Long: "+location.getLongitude();
          text.setText(str);
   }

  @Override
  public void onProviderDisabled(String provider) {
  /******** Called when User off Gps *********/
  String str = "Eteint";
  text.setText(str);
  }

  @Override
  public void onProviderEnabled(String provider) {
    /******** Called when User on Gps  *********/
    String str = "Allume";
    text.setText(str);
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {
    String str = "Change";
    text.setText(str);
  }

  public void addListenerOnButton() {
    button = (Button) findViewById(R.id.buttonOne);
    button.setOnClickListener(new OnClickListener() {

    @Override
    public void onClick(View arg0) {
      monCpt++;
      maString = "Clic numero : " + Integer.toString(monCpt);
      text.setText(maString);
    }
    });
  }
}

