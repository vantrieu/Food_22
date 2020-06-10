package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuRestaurantActivity extends AppCompatActivity {

    private Button btnThucDon, btnAnh;
    private TextView btnRes_name;
    private ImageView imgBack;
    private int Res_id;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);

        Intent intent = getIntent();
        Res_id = intent.getExtras().getInt("res_id");
        Toast.makeText(this, String.valueOf(Res_id), Toast.LENGTH_SHORT).show();

        btnThucDon = (Button) findViewById(R.id.button_menu_text);
        btnAnh = (Button) findViewById(R.id.button_menu_image);
        imgBack = (ImageView) findViewById(R.id.imgbutton_back);
        frameLayout = (FrameLayout) findViewById(R.id.container_body);

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

        initFragment();


    }

    private void initFragment() {
        Fragment1 firstFragment = new Fragment1();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container_body, firstFragment);
        ft.commit();
    }

    protected void addFragment(Fragment fragment) {
        FragmentManager fmgr = getSupportFragmentManager();
        FragmentTransaction ft = fmgr.beginTransaction();
        ft.add(R.id.container_body, fragment);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }
}