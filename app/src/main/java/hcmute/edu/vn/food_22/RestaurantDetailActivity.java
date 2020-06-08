package hcmute.edu.vn.food_22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RestaurantDetailActivity extends AppCompatActivity{

    private Location location;
    private GoogleApiClient gac;

    private ImageView img_back;
    private TextView txtTenQuan, txtTinh, txtTrangThai, txtGio, txtStart,
            txtKhoangcach, txtLoaiHinh, txtGia, txtAddWifi;
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
        txtAddWifi = (TextView) findViewById(R.id.txtAddWifi);

        Intent intent = getIntent();
        res_id = intent.getIntExtra("res_id", 0);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GetRestaurant();

        txtAddWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(RestaurantDetailActivity.this);
                dialog.setContentView(R.layout.wifi);
                EditText edt_wifi=(EditText) dialog.findViewById(R.id.wifi);
                EditText edt_pass=(EditText) dialog.findViewById(R.id.pass);
                Button btnSubmit=(Button) dialog.findViewById(R.id.btnSubmit);
                dialog.show();

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //code thêm dữ liệu wifi

                        dialog.dismiss();
                    }
                });
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
}
