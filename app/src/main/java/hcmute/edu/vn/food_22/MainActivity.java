package hcmute.edu.vn.food_22;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import static android.location.GpsStatus.GPS_EVENT_STARTED;
import static android.location.GpsStatus.GPS_EVENT_STOPPED;

public class MainActivity extends AppCompatActivity {
    private TextView txtVersion;
    Database database;
    static Context main;
    public static boolean isWifiEnabled;
    public static boolean isGPSEnabled;
    public static Location mLastLocation;
    private static final int REQUEST_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isWifiEnabled = false;
        isGPSEnabled = false;
        database = new Database(this, "foody.db", null, 1);
        txtVersion = (TextView) findViewById(R.id.textViewVersion);
        main = MainActivity.this;
        //Tạo các bảng và chèn dữ liệu
        database.CreateTableProvince();
        database.CreateTableRestaurant();
        database.CreateTableTypeFood();
        database.CreateTableFood();
        database.CreateTableWifi();
        mLastLocation = new Location("ABC");


        //LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1
            );
        }
        else
        {
            getCurrentLocation();
//            lm.addGpsStatusListener(new android.location.GpsStatus.Listener()
//            {
//                public void onGpsStatusChanged(int event)
//                {
//                    switch (event) {
//                        case GPS_EVENT_STARTED:
//                            //Toast.makeText(MainActivity.this, "GPS ON", Toast.LENGTH_SHORT).show();
//                            //getCurrentLocation();
//                            break;
//                        case GPS_EVENT_STOPPED:
//                            //Toast.makeText(MainActivity.this, "GPS stopped", Toast.LENGTH_SHORT).show();
//                            break;
//                        default:
//                            getCurrentLocation();
//                            Toast.makeText(MainActivity.this, ""+MainActivity.mLastLocation.getLatitude(), Toast.LENGTH_SHORT).show();
//                            break;
//                    }
//                }
//            });
        }
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            txtVersion.setText("Phiên bản: " + packageInfo.versionName);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }, 3000);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                txtVersion.setText("Phiên bản: " + packageInfo.versionName);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                }, 2000);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation()
    {
            final LocationRequest locationRequest=new LocationRequest();
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(3000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.getFusedLocationProviderClient(MainActivity.this)
                    .requestLocationUpdates(locationRequest,new LocationCallback(){
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                    .removeLocationUpdates(this);
                            if(locationResult!=null&&locationResult.getLocations().size()>0)
                            {
                                int latestLocationIndex=locationResult.getLocations().size()-1;
                                mLastLocation.setLatitude(locationResult.getLocations().get(latestLocationIndex).getLatitude());
                                mLastLocation.setLongitude(locationResult.getLocations().get(latestLocationIndex).getLongitude());
                                isGPSEnabled = true;
                            }
                        }
                    }, Looper.getMainLooper());
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1&&grantResults.length>0)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation();
            }
            else
            {
                Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
