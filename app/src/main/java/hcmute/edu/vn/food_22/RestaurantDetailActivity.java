package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.util.Timer;

public class RestaurantDetailActivity extends AppCompatActivity {

    private ImageView img_back;
    private TextView txtTenQuan, txtTinh, txtTrangThai, txtGio;
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

        Intent intent = getIntent();
        res_id = intent.getIntExtra("res_id", 0);

        GetRestaurant();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
}

    private void GetRestaurant(){
        Cursor dataRes = database.GetData("SELECT Province.name, res_name, res_open, res_close FROM Province INNER JOIN Restaurant ON Province.province_id = Restaurant.province_id WHERE " +
                "Restaurant.res_id = " + res_id);
        dataRes.moveToFirst();
        txtTenQuan.setText(dataRes.getString(1));
        txtTinh.setText(dataRes.getString(0));
        txtTrangThai.setText(CheckStatus(dataRes.getString(2), dataRes.getString(3)));
        String temp = dataRes.getString(2) + " - " + dataRes.getString(3);
        txtGio.setText(temp);
    }

    private String CheckStatus(String flag1, String flag2){
        Time now = new Time();
        now.setToNow();
        String[] temp1 = flag1.split(":");
        String[] temp2 = flag2.split(":");
        if((Integer.parseInt(temp1[0]) <= now.hour) && (Integer.parseInt(temp2[0]) >= now.hour)){
            return "ĐANG MỞ CỬA";
        } else {
            return "CHƯA MỞ CỬA";
        }
    }
}
