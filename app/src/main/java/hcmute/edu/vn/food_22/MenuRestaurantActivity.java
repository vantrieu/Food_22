package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuRestaurantActivity extends AppCompatActivity {

    private Button btnThucDon, btnAnh;
    private ImageView imgBack;
    private int Res_id;

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

        btnThucDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnThucDon.setBackgroundResource(R.drawable.button_menu_active);
                btnAnh.setBackgroundResource(R.drawable.button_menu_normal);
            }
        });

        btnAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnThucDon.setBackgroundResource(R.drawable.button_menu_normal);
                btnAnh.setBackgroundResource(R.drawable.button_menu_active);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}