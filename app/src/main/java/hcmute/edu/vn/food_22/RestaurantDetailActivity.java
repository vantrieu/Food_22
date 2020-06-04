package hcmute.edu.vn.food_22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class RestaurantDetailActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private Location location;
    private GoogleApiClient gac;

    private ImageView img_back;
    private TextView txtTenQuan, txtTinh, txtTrangThai, txtGio, txtStart, txtKhoangcach, txtLoaiHinh, txtGia;
    private Button btnLienHe;
    private int res_id;
    private Database database = new Database(this, "foody.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        txtTenQuan = (TextView) findViewById(R.id.txtTenQuan);
        txtTinh = (TextView) findViewById(R.id.txttinh);
        txtTrangThai = (TextView) findViewById(R.id.txtTrangThai);
        txtGio = (TextView) findViewById(R.id.txtGio);
        img_back = (ImageView) findViewById(R.id.img_back);
        txtStart = (TextView) findViewById(R.id.txtStart);
        txtKhoangcach = (TextView) findViewById(R.id.txtKhoangCach);
        txtLoaiHinh = (TextView) findViewById(R.id.txtLoaiHinh);
        txtGia = (TextView) findViewById(R.id.txtGia);

        Intent intent = getIntent();
        res_id = intent.getIntExtra("res_id", 0);

        GetRestaurant();
        if (checkPlayServices()) {
            buildGoogleApiClient();
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
}

    private void GetRestaurant(){
        Cursor dataRes = database.GetData("SELECT Province.name, res_name, res_open, res_close, res_address, res_type FROM Province INNER JOIN " +
                "Restaurant ON Province.province_id = Restaurant.province_id WHERE Restaurant.res_id = " + res_id);
        dataRes.moveToFirst();
        txtTenQuan.setText(dataRes.getString(1));
        txtTinh.setText(dataRes.getString(0));
        txtTrangThai.setText(CheckStatus(dataRes.getString(2), dataRes.getString(3)));
        if(txtTrangThai.getText().equals("ĐANG MỞ CỬA"))
            txtTrangThai.setTextColor(Color.BLACK);
        String temp = dataRes.getString(2) + " - " + dataRes.getString(3);
        txtGio.setText(temp);
        txtStart.setText(dataRes.getString(4));
        txtLoaiHinh.setText(dataRes.getString(5));
        Cursor dataFood = database.GetData("SELECT price FROM Food WHERE res_id = " + res_id);
        int small = 999999999;
        int big = 0;
        while(dataFood.moveToNext()){
            if(dataFood.getInt(0) < small)
                small = dataFood.getInt(0);
            if(dataFood.getInt(0) > big)
                big = dataFood.getInt(0);
        }
        txtGia.setText(String.valueOf(small) +"đ - " + String.valueOf(big) + "đ");
    }

    private String CheckStatus(String flag1, String flag2){
        Time now = new Time();
        now.setToNow();
        String[] temp1 = flag1.split(":");
        String[] temp2 = flag2.split(":");
        long timeStrat = Integer.parseInt(temp1[0]) * 3600 + Integer.parseInt(temp1[1]) * 60;
        long timeStop = Integer.parseInt(temp2[0]) * 3600 + Integer.parseInt(temp2[1]) * 60;
        long flag = now.hour * 3600 + now.minute * 60;
        if(timeStrat <= flag && flag <= timeStop){
            return "ĐANG MỞ CỬA";
        } else {
            return "CHƯA MỞ CỬA";
        }
    }

//region Code tính toán khoảng cách.
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        gac.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Lỗi kết nối: " + connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    public void dispLocation(View view) {
        getLocation();
    }
    /**
     * Phương thức này dùng để hiển thị trên UI
     * */
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Kiểm tra quyền hạn
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        } else {
            location = LocationServices.FusedLocationApi.getLastLocation(gac);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                // Hiển thị
                //tvLocation.setText(latitude + ", " + longitude);
                float results[] = new float[10];
                Cursor dataRes = database.GetData("SELECT res_address FROM Restaurant WHERE Restaurant.res_id = " + res_id);
                dataRes.moveToFirst();
                LatLng temp = getLocationFromAddress(this, dataRes.getString(0));
                Location.distanceBetween(latitude, longitude, temp.latitude, temp.longitude, results);
                txtKhoangcach.setText(String.valueOf(Math.round(results[0]) / 1000.0).substring(0, 4) + " km");
            } else {
                //tvLocation.setText("(Không thể hiển thị vị trí. " +
                        //"Bạn đã kích hoạt location trên thiết bị chưa?)");
            }
        }
    }
    /**
     * Tạo đối tượng google api client
     * */
    protected synchronized void buildGoogleApiClient() {
        if (gac == null) {
            gac = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
    }
    /**
     * Phương thức kiểm chứng google play services trên thiết bị
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1000).show();
            } else {
                Toast.makeText(this, "Thiết bị này không hỗ trợ.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }
    protected void onStart() {
        gac.connect();
        super.onStart();
    }
    protected void onStop() {
        gac.disconnect();
        super.onStop();
    }
//endregion

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }
}
