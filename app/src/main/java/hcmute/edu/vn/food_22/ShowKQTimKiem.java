package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowKQTimKiem extends AppCompatActivity {

    LinearLayout ln2;
    EditText edt_find;
    TextView txt_tinh;
    ImageView img_back;
    ArrayList<InfoQuan> arrayList;
    ShowKQAdapter adapter;
    ListView listView;
    String text_input_by_user;
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
        text_input_by_user=receive.getExtras().getString("input");

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
        edt_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
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

        Cursor cursor=get_by_food(text_input_by_user);
        if(cursor.getCount()<1)
        {
            cursor=get_by_name(text_input_by_user);
            if(cursor.getCount()<1)
            {
                Toast.makeText(this, "Không tìm thấy quán ăn phù hợp", Toast.LENGTH_SHORT).show();
            }
        }
        while (cursor.moveToNext())
        {
            Log.e("DATA",cursor.getString(1));
            arrayList.add(new InfoQuan(cursor.getString(1),cursor.getString(3),0,cursor.getString(2),cursor.getString(4)));
        }

        adapter.notifyDataSetChanged();
    }

    private Cursor get_all()
    {
        Database dt=new Database(this, "foody.db", null, 1);
        //Cursor cursor=dt.GetData("SELECT res_id, res_name, res_type, res_address, res_img, province_id FROM Restaurant");
        Cursor cursor=dt.GetData("SELECT * FROM Type_Food");
        return cursor;
    }

    private Cursor get_by_name(String name)
    {
        Database dt=new Database(this, "foody.db", null, 1);
        Cursor cursor=dt.GetData("SELECT res_id, res_name, res_type, res_address, res_img, province_id FROM Restaurant WHERE res_name LIKE '%"+name+"%'");
        return cursor;
    }

    private Cursor get_by_food(String food_name)
    {
        Database dt=new Database(this, "foody.db", null, 1);
        Cursor cursor=dt.GetData("SELECT Restaurant.res_id, res_name, res_type, res_address, res_img, province_id FROM Restaurant INNER JOIN Food ON Restaurant.res_id=Food.res_id WHERE food_name LIKE '%"+food_name+"%'");
        return cursor;
    }
}
