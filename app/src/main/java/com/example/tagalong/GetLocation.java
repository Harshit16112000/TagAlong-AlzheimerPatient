package com.example.tagalong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetLocation extends AppCompatActivity {
    Button btLocation, btSMS;

    TextView textView1, textView2, textView3, textView4, textView5;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);

        btSMS = findViewById(R.id.sendsms);
        btLocation = findViewById(R.id.bt_location);
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
        textView4 = findViewById(R.id.text_view4);
        textView5 = findViewById(R.id.text_view5);

      fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

      btLocation.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(ActivityCompat.checkSelfPermission(GetLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
              {
                  getLocation();
              }else
              {
                  ActivityCompat.requestPermissions(GetLocation.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 44);
              }
          }
      });
    }
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                 Location location = task.getResult();
                 if(location != null)
                 {
                     try {
                         Geocoder geocoder = new Geocoder(GetLocation.this, Locale.getDefault());

                         List<Address> addresses = geocoder.getFromLocation(
                                 location.getLatitude(), location.getLongitude(),1
                         );
                         textView1.setText(Html.fromHtml(
                               "<b> Latitude : </b><br>" + addresses.get(0).getLatitude()
                         ));
                         textView2.setText(Html.fromHtml(
                                 "<b> Longitude : </b><br>" + addresses.get(0).getLongitude()
                         ));

                         textView3.setText(Html.fromHtml(
                                 "<b> Country : </b><br>" + addresses.get(0).getCountryName()
                         ));

                         textView4.setText(Html.fromHtml(
                                 "<b> Locality : </b><br>" + addresses.get(0).getLocality()
                         ));

                         textView2.setText(Html.fromHtml(
                                 "<b> Address : </b><br>" + addresses.get(0).getAddressLine(0)
                         ));


                     } catch (IOException e) {
                         e.printStackTrace();
                     }


                 }
            }
        });
    }
}

