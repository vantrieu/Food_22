package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import hcmute.edu.vn.food_22.tabslide2.SectionsPagerAdapter;

public class ShowKQTimKiem extends AppCompatActivity {

    public static String[] tabs = {"Đúng nhất", "Gần tôi"};
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    public static int[] resourceIds = {
            R.layout.frag_1_slide2
            ,R.layout.frag_2_slide2
    };

    LinearLayout ln2;
    EditText edt_find;
    TextView txt_tinh;
    ImageView img_back;
    String text_input_by_user;
    SectionsPagerAdapter sectionsPagerAdapter;
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

        sectionsPagerAdapter = new SectionsPagerAdapter(this,text_input_by_user,province_id, getSupportFragmentManager());
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(sectionsPagerAdapter);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setEvent();
    }

    private void setEvent()
    {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edt_find.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                sectionsPagerAdapter = new SectionsPagerAdapter(ShowKQTimKiem.this,edt_find.getText().toString(),province_id, getSupportFragmentManager());
                mViewPager.setAdapter(sectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
                return true;
                }
                return false;
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
        img_back=findViewById(R.id.img_back);
        ln2 =findViewById(R.id.linearLayout2);
    }
}
