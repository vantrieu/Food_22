package hcmute.edu.vn.food_22;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private RecyclerView recyclerView;
    private ImageView img_back, imgWifi, imgDelivery;
    private TextView txtTenQuan, txtTinh, txtTrangThai, txtGio, txtStart,
            txtKhoangcach, txtLoaiHinh, txtGia, txtAddWifi, txtWifi, txtMenu;
    private String phone;
    private int res_id;
    private Button btnLienhe;
    private Database database = new Database(this, "foody.db", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        txtTenQuan = (TextView) findViewById(R.id.txtTenQuan);
        txtTinh = (TextView) findViewById(R.id.txttinh);
        txtTrangThai = (TextView) findViewById(R.id.txtTrangThai);
        txtGio = (TextView) findViewById(R.id.txtGio);
        img_back = (ImageView) findViewById(R.id.img_back);
        txtStart = (TextView) findViewById(R.id.txtStart);
        txtKhoangcach = (TextView) findViewById(R.id.txtKhoangCach);
        txtLoaiHinh = (TextView) findViewById(R.id.txtLoaiHinh);
        txtGia = (TextView) findViewById(R.id.txtGia);
        txtAddWifi = (TextView) findViewById(R.id.txtAddWifi);
        txtWifi = (TextView) findViewById(R.id.txtWifi);
        txtMenu = (TextView) findViewById(R.id.txtMenu);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_img);
        btnLienhe = (Button) findViewById(R.id.btnLienHe);
        imgWifi = (ImageView) findViewById(R.id.imgWifi);
        imgDelivery = (ImageView) findViewById(R.id.imgDelivery);

        Intent intent = getIntent();
        res_id = intent.getIntExtra("res_id", 0);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GetRestaurant();
        GetImage();
        PopulateWifi();

        txtAddWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(RestaurantDetailActivity.this);
                dialog.setContentView(R.layout.wifi);
                final EditText edt_wifi = (EditText) dialog.findViewById(R.id.wifi);
                final EditText edt_pass = (EditText) dialog.findViewById(R.id.pass);
                Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
                dialog.show();

                final Cursor dataWifi = database.GetData("SELECT wifi_id, wifi_name, wifi_pass FROM Wifi WHERE Wifi.res_id = " + res_id);
                dataWifi.moveToFirst();
                if (dataWifi.getCount() > 0) {
                    edt_wifi.setText(dataWifi.getString(1));
                    edt_pass.setText(dataWifi.getString(2));
                }

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataWifi.moveToFirst();
                        if(dataWifi.getCount() == 0){
                            if (!edt_wifi.getText().equals("") || !edt_pass.getText().equals("")) {
                                String sql = "INSERT INTO Wifi VALUES(null, '" + edt_wifi.getText().toString() + "', '" + edt_pass.getText().toString()
                                        + "', " + res_id + ")";
                                database.QueryData(sql);
                            }
                            Cursor dataWifi = database.GetData("SELECT wifi_id, wifi_name, wifi_pass FROM Wifi WHERE Wifi.res_id = " + res_id);
                            dataWifi.moveToFirst();
                            txtAddWifi.setTextColor(Color.BLUE);
                            txtAddWifi.setText(dataWifi.getString(1));
                            txtWifi.setText(dataWifi.getString(2));
                            imgWifi.setImageResource(R.drawable.wifi_gray);
                        } else {
                            if (!edt_wifi.getText().equals(dataWifi.getString(1)) || !edt_pass.getText().equals(dataWifi.getString(2))) {
                                database.QueryData("UPDATE Wifi SET wifi_name = '" + edt_wifi.getText() + "', wifi_pass = '" + edt_pass.getText() +
                                        "' WHERE wifi_id = " + dataWifi.getInt(0));
                            }
                            Cursor dataWifi = database.GetData("SELECT wifi_id, wifi_name, wifi_pass FROM Wifi WHERE Wifi.res_id = " + res_id);
                            dataWifi.moveToFirst();
                            txtAddWifi.setTextColor(Color.BLUE);
                            txtAddWifi.setText(dataWifi.getString(1));
                            txtWifi.setText(dataWifi.getString(2));
                            imgWifi.setImageResource(R.drawable.gray_wifi_icon);
                        }
                        dialog.dismiss();
                    }
                });
            }
        });

        txtWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor dataWifi = database.GetData("SELECT wifi_id, wifi_name, wifi_pass FROM Wifi WHERE Wifi.res_id = " + res_id);
                if(dataWifi.getCount() == 0){
                    txtAddWifi.callOnClick();
                } else {
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intent);
                }

            }
        });

        txtMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantDetailActivity.this, MenuRestaurantActivity.class);
                intent.putExtra("res_id", res_id);
                startActivity(intent);
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.MyMap);
        mapFragment.getMapAsync(this);

        btnLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(RestaurantDetailActivity.this);
                dialog.setContentView(R.layout.lien_he);
                final TextView txtSdt = (TextView) dialog.findViewById(R.id.txtSdt);
                final TextView txtErr = (TextView) dialog.findViewById(R.id.txtError);
                if(phone.length() > 0)
                    txtSdt.setText(phone);
                else
                    txtSdt.setText("Không có số điện thoại!");
                dialog.show();

                txtSdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(phone.length() > 0)
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)));
                        else
                            Toast.makeText(RestaurantDetailActivity.this, "Nhà hàng không sử dụng điện thoại!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                txtErr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RestaurantDetailActivity.this, "Báo cáo của bạn đã được lưu lại!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void GetImage() {
        List<Food> lstFood = new ArrayList<>();
        Cursor dataFood = database.GetData("SELECT * FROM Food WHERE Food.res_id = " + res_id);
        while (dataFood.moveToNext()) {
            lstFood.add(new Food(dataFood.getInt(0), dataFood.getString(1), dataFood.getInt(2), dataFood.getString(3),
                    dataFood.getString(4), dataFood.getInt(5), dataFood.getInt(6)));
        }
        RecyclerviewFoodAdapter myAdapter = new RecyclerviewFoodAdapter(this, lstFood);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(myAdapter);
    }

    private void PopulateWifi() {
        Cursor dataWifi = database.GetData("SELECT wifi_id, wifi_name, wifi_pass FROM Wifi WHERE Wifi.res_id = " + res_id);
        dataWifi.moveToFirst();
        if (dataWifi.getCount() > 0) {
            imgWifi.setImageResource(R.drawable.green_wifi_icon);
            txtAddWifi.setTextColor(Color.BLUE);
            txtAddWifi.setText(dataWifi.getString(1));
            txtWifi.setText(dataWifi.getString(2));
        } else {
            imgWifi.setImageResource(R.drawable.wifi_gray);
        }
    }

    private void GetRestaurant() {
        GeocodingLocation g = new GeocodingLocation();
        Cursor dataRes = database.GetData("SELECT Province.name, res_name, res_open, res_close, res_address, res_type, phone_number, has_delivery FROM Province INNER JOIN " +
                "Restaurant ON Province.province_id = Restaurant.province_id WHERE Restaurant.res_id = " + res_id);
        dataRes.moveToFirst();
        txtTenQuan.setText(dataRes.getString(1));
        txtTinh.setText(dataRes.getString(0));
        txtTrangThai.setText(CheckStatus(dataRes.getString(2), dataRes.getString(3)));
        if (txtTrangThai.getText().equals("ĐANG MỞ CỬA"))
            txtTrangThai.setTextColor(Color.BLACK);
        String temp = dataRes.getString(2) + " - " + dataRes.getString(3);
        txtGio.setText(temp);
        txtStart.setText(dataRes.getString(4));
        Location t = g.getLatLngFromAddress(txtStart.getText().toString(), RestaurantDetailActivity.this);
        double distance = g.Calculate(t.getLatitude(), t.getLongitude(), MainActivity.mLastLocation.getLatitude(), MainActivity.mLastLocation.getLongitude());
        if(MainActivity.isGPSEnabled&&MainActivity.isWifiEnabled)
            txtKhoangcach.setText(String.valueOf((int) distance) + String.valueOf(((distance - (int) distance))).substring(1, 3) + " km");
        else txtKhoangcach.setText("(undefine)");
        txtLoaiHinh.setText(dataRes.getString(5));
        Cursor dataFood = database.GetData("SELECT price FROM Food WHERE res_id = " + res_id);
        int small = 999999999;
        int big = 0;
        while (dataFood.moveToNext()) {
            if (dataFood.getInt(0) < small)
                small = dataFood.getInt(0);
            if (dataFood.getInt(0) > big)
                big = dataFood.getInt(0);
        }
        txtGia.setText(ConvertString(small) + " - " + ConvertString(big));
        phone = dataRes.getString(6);
        if(dataRes.getInt(7) == 0){
            imgDelivery.setImageResource(R.drawable.delivery_gray);
        } else {
            imgDelivery.setImageResource(R.drawable.delivery_green);
        }
    }

    private String CheckStatus(String flag1, String flag2) {
        Time now = new Time();
        now.setToNow();
        String[] temp1 = flag1.split(":");
        String[] temp2 = flag2.split(":");
        long timeStrat = Integer.parseInt(temp1[0]) * 3600 + Integer.parseInt(temp1[1]) * 60;
        long timeStop = Integer.parseInt(temp2[0]) * 3600 + Integer.parseInt(temp2[1]) * 60;
        long flag = now.hour * 3600 + now.minute * 60;
        if (timeStrat <= flag && flag <= timeStop) {
            return "ĐANG MỞ CỬA";
        } else {
            return "CHƯA MỞ CỬA";
        }
    }

    private String ConvertString(int temp) {
        String s = String.valueOf(temp);
        StringBuilder str = new StringBuilder(s);
        int count = s.length();
        int flag = 0;
        for (int i = (count - 1); i > 0; i--) {
            flag += 1;
            if (flag % 3 == 0) {
                str.insert(i, ".");
            }
        }
        return str + "đ";
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng temp = getLocationFromAddress(this, txtStart.getText().toString());
        try {
            googleMap.addMarker(new MarkerOptions().position(temp).title(txtTenQuan.getText().toString()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temp, 12));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        RestaurantDetailActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1
                );
            }
            googleMap.setMyLocationEnabled(true);
        }
        catch (IllegalArgumentException e)
        {
            Toast.makeText(this, "Vui lòng kết nối internet", Toast.LENGTH_SHORT).show();
        }
    }
    public LatLng getLocationFromAddress(Context context,String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;
        try {
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
