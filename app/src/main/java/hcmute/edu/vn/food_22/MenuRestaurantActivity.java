package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuRestaurantActivity extends AppCompatActivity {

    private Button btnThucDon, btnAnh;
    private TextView txtRes_name;
    private ImageView imgBack;
    public static int Res_id;
    public static List<Food> lstFood;
    private Database database = new Database(this, "foody.db", null, 1);
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);

        Intent intent = getIntent();
        Res_id = intent.getExtras().getInt("res_id");

        btnThucDon = (Button) findViewById(R.id.button_menu_text);
        btnAnh = (Button) findViewById(R.id.button_menu_image);
        imgBack = (ImageView) findViewById(R.id.imgbutton_back);
        txtRes_name = (TextView) findViewById(R.id.txtRes_id);

        mContext = this;
        getData();

        btnThucDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnThucDon.setBackgroundResource(R.drawable.button_menu_active);
                btnAnh.setBackgroundResource(R.drawable.button_menu_normal);
                addFragment(new Fragment1());
            }
        });

        btnAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnThucDon.setBackgroundResource(R.drawable.button_menu_normal);
                btnAnh.setBackgroundResource(R.drawable.button_menu_active);
                addFragment(new Fragment2());

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addFragment(new Fragment1());
    }
    protected void getData(){
        lstFood=new ArrayList<>();
        Cursor dataRes = database.GetData("SELECT res_name FROM Restaurant WHERE Restaurant.res_id =" + Res_id);
        dataRes.moveToFirst();
        txtRes_name.setText(dataRes.getString(0));
        Cursor dataFood = database.GetData("SELECT * FROM Food WHERE Food.res_id = " + Res_id);
        while (dataFood.moveToNext()) {
            lstFood.add( new Food(dataFood.getInt(0), dataFood.getString(1),  dataFood.getInt(2), dataFood.getString(3),
                    dataFood.getString(4), dataFood.getInt(5), dataFood.getInt(6)));
        }
    }

    protected void addFragment(Fragment fragment) {
        FragmentManager fmgr = getSupportFragmentManager();
        FragmentTransaction ft = fmgr.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("res_id", Res_id);
        fragment.setArguments(bundle);
        ft.add(R.id.container_body, fragment);
        ft.replace(R.id.container_body, fragment);
        ft.commit();
    }
}