package hcmute.edu.vn.food_22;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView txtAddress;
    private static final int REQUEST_CODE = 123;
    private static final int REQUEST_CODE_1 = 456;
    private List<Store> lstStore;
    RecyclerView list_store_recyclerview;
    EditText edt_address;
    int province_id;
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtAddress = (TextView) findViewById(R.id.txtTinh);
        edt_address=findViewById(R.id.editText);
        list_store_recyclerview = (RecyclerView) findViewById(R.id.recyclerview_store);
        database = new Database(this, "foody.db", null, 1);


        txtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddressActivity.class);
                intent.putExtra("province_id", GetProvinceId());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        edt_address.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    // After enter content to edittext box, click ok to start new Activity
                    Intent i = new Intent(HomeActivity.this, ShowKQTimKiem.class);
                    i.putExtra("key",edt_address.getText().toString().trim());
                    i.putExtra("province_id", province_id);
                    i.putExtra("province_name",txtAddress.getText());
                    i.putExtra("input",edt_address.getText().toString().trim());
                    startActivityForResult(i, REQUEST_CODE_1);
                    return true;
                }
                return false;
            }
        });

        SetupListStore();
        //Toast.makeText(this, ""+MainActivity.isWifiEnabled, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            province_id = data.getIntExtra("province_id", 0);
            txtAddress.setText(GetProvinceName(province_id));
        }
        if(requestCode==REQUEST_CODE_1){
            edt_address.setText("");
        }
        SetupListStore();
    }

    private void SetupListStore(){
        lstStore = new ArrayList<>();
        Cursor dataFlag = database.GetData("SELECT * FROM Province WHERE name = '" + txtAddress.getText() + "'");
        dataFlag.moveToFirst();
        //Toast.makeText(this, String.valueOf(province_id), Toast.LENGTH_SHORT).show();
        Cursor dataRestaurant = database.GetData("SELECT * FROM Restaurant WHERE province_id = " + dataFlag.getInt(0));
        //Toast.makeText(this, String.valueOf(dataRestaurant.getCount()), Toast.LENGTH_SHORT).show();
        while (dataRestaurant.moveToNext()) {
            lstStore.add( new Store(dataRestaurant.getInt(0), dataRestaurant.getString(1),  dataRestaurant.getString(2),dataRestaurant.getString(3), 0,
                    dataRestaurant.getString(4), dataRestaurant.getString(5), dataRestaurant.getString(6), dataRestaurant.getString(7),
                    dataRestaurant.getInt(8), dataRestaurant.getInt(9), dataRestaurant.getInt(10)));
        }
        RecyclerviewAdapter myAdapter = new RecyclerviewAdapter(this, lstStore);
        list_store_recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        list_store_recyclerview.setAdapter(myAdapter);
    }

    private int GetProvinceId(){
        Cursor dataFlag = database.GetData("SELECT * FROM Province WHERE name = '" + txtAddress.getText() + "'");
        dataFlag.moveToFirst();
        return dataFlag.getInt(0);
    }

    private String GetProvinceName(int province_id){
        Cursor dataFlag = database.GetData("SELECT * FROM Province WHERE province_id = " + province_id);
        dataFlag.moveToFirst();
        return dataFlag.getString(1);
    }

}
