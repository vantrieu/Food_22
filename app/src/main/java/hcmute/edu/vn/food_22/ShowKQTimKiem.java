package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowKQTimKiem extends AppCompatActivity {

    LinearLayout ln2;
    EditText edt_find;
    TextView txt_tinh;
    ImageView img_back;
    ArrayList<InfoQuan> arrayList;
    ShowKQAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kq_tim_kiem);
        anhXa();

        Intent receive=getIntent();
        String keyword=receive.getExtras().getString("key");
        String tvtinh=receive.getExtras().getString("tvtinh");

        edt_find.setText(keyword);
        txt_tinh.setText(tvtinh);

        setEvent();

        loadDATA();
    }

    private void setEvent()
    {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void anhXa()
    {
        edt_find=findViewById(R.id.editText);
        txt_tinh=findViewById(R.id.txtTinh);
        listView=findViewById(R.id.listView_id);
        img_back=findViewById(R.id.img_back);
        ln2 =findViewById(R.id.linearLayout2);
    }

    private void loadDATA()
    {
        arrayList=new ArrayList<>();
        adapter=new ShowKQAdapter(this,R.layout.ketqua_timkiem_item,arrayList);
        listView.setAdapter(adapter);
        arrayList.add(new InfoQuan("Quán nhậu Tới Bến","121 Võ Văn Ngân Thủ Đức",3,"Quán nhậu"));
        arrayList.add(new InfoQuan("Quán thịt nướng Bò Vui Vẻ","206 Lê Văn Chí Thủ Đức",1.9,"Quán ăn",R.drawable.thitnuong));
        adapter.notifyDataSetChanged();
    }
}
