package hcmute.edu.vn.food_22;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Button btnAddress;
    private static final int REQUEST_CODE = 123;
    List<Store> lstStore;
    RecyclerView list_store_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAddress = (Button) findViewById(R.id.btnTinh);
        list_store_recyclerview = (RecyclerView) findViewById(R.id.recyclerview_store);

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddressActivity.class);
                //startActivity(intent);
                intent.putExtra("Tinh", btnAddress.getText());
                startActivityForResult(intent, REQUEST_CODE);
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
            btnAddress.setText(data.getStringExtra("Tinh"));
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
