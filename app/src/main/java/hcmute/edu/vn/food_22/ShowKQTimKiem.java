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
    int province_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kq_tim_kiem);
        anhXa();

        Intent receive=getIntent();
        String keyword=receive.getExtras().getString("key");
        String province_name=receive.getExtras().getString("province_name");
        province_id=get_province_id(province_name);
        edt_find.setText(keyword);
        txt_tinh.setText(province_name);
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
                loadDATA2();
            }
        });
    }

    private int get_province_id(String str)
    {
        Database database=new Database(this,"foody.db",null,1);
        Cursor dataFlag = database.GetData("SELECT * FROM Province WHERE name = '" + str + "'");
        dataFlag.moveToFirst();
        return dataFlag.getInt(0);
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

        Cursor cursor=get_by_food(text_input_by_user,province_id);
        if(cursor.getCount()<1)
        {
            cursor=get_by_name(text_input_by_user,province_id);
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

    private void loadDATA2()
    {
        arrayList.clear();
        Cursor cursor=get_by_food(edt_find.getText().toString(),province_id);
        if(cursor.getCount()<1)
        {
            cursor=get_by_name(edt_find.getText().toString(),province_id);
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

    private Cursor get_by_name(String name, int province)
    {
        Database dt=new Database(this, "foody.db", null, 1);
        Cursor cursor=dt.GetData("SELECT DISTINCT res_id, res_name, res_type, res_address, res_img, province_id FROM Restaurant WHERE province_id="+province+" AND res_name LIKE '%"+name+"%'");
        return cursor;
    }

    private Cursor get_by_food(String str,int province)
    {
        Database dt=new Database(this, "foody.db", null, 1);
        Cursor cursor=dt.GetData("SELECT DISTINCT Restaurant.res_id, res_name, res_type, res_address, res_img, province_id FROM Restaurant LEFT JOIN Food ON Restaurant.res_id=Food.res_id WHERE province_id="+province+" AND (food_name LIKE '%"+str+"%' or res_name LIKE '%"+str+"%')");
        return cursor;
    }
}
