package hcmute.edu.vn.food_22;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView txtAddress;
    private static final int REQUEST_CODE = 123;
    List<Store> lstStore;
    RecyclerView list_store_recyclerview;
    EditText edt_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtAddress = (TextView) findViewById(R.id.txtTinh);
        edt_address=findViewById(R.id.editText);
        list_store_recyclerview = (RecyclerView) findViewById(R.id.recyclerview_store);

        txtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddressActivity.class);
                //startActivity(intent);
                intent.putExtra("Tinh", txtAddress.getText());
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
                    i.putExtra("tvtinh",txtAddress.getText().toString().trim());
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });

        SetupListStore();
        RecyclerviewAdapter myAdapter = new RecyclerviewAdapter(this, lstStore);
        list_store_recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        list_store_recyclerview.setAdapter(myAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            txtAddress.setText(data.getStringExtra("Tinh"));
        }
    }

    private void SetupListStore(){
        lstStore = new ArrayList<>();
        lstStore.add( new Store("https://deloy.tech/dongnai/xoiaho/xoigaxe.jpg",
                "Bún đậu Cô Ba", "Nhân viên thân thiện, làm hơi lâu nhưng đồ ăn ngon!"));
        lstStore.add( new Store("https://deloy.tech/dongnai/xoiaho/xoigaxe.jpg",
                "Bún đậu Cô Ba", "Nhân viên thân thiện, làm hơi lâu nhưng đồ ăn ngon!"));
        lstStore.add( new Store("https://deloy.tech/dongnai/xoiaho/xoigaxe.jpg",
                "Bún đậu Cô Ba", "Nhân viên thân thiện, làm hơi lâu nhưng đồ ăn ngon!"));
        lstStore.add( new Store("https://deloy.tech/dongnai/xoiaho/xoigaxe.jpg",
                "Bún đậu Cô Ba", "Nhân viên thân thiện, làm hơi lâu nhưng đồ ăn ngon!"));
        lstStore.add( new Store("https://deloy.tech/dongnai/xoiaho/xoigaxe.jpg",
                "Bún đậu Cô Ba", "Nhân viên thân thiện, làm hơi lâu nhưng đồ ăn ngon!"));
        lstStore.add( new Store("https://deloy.tech/dongnai/xoiaho/xoigaxe.jpg",
                "Bún đậu Cô Ba", "Nhân viên thân thiện, làm hơi lâu nhưng đồ ăn ngon!"));
        lstStore.add( new Store("https://deloy.tech/dongnai/xoiaho/xoigaxe.jpg",
                "Bún đậu Cô Ba", "Nhân viên thân thiện, làm hơi lâu nhưng đồ ăn ngon!"));
        lstStore.add( new Store("https://deloy.tech/dongnai/xoiaho/xoigaxe.jpg",
                "Bún đậu Cô Ba", "Nhân viên thân thiện, làm hơi lâu nhưng đồ ăn ngon!"));
    }
}
