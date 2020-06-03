package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class RestaurantDetailActivity extends AppCompatActivity {

    private int res_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        Intent intent = getIntent();
        res_id = intent.getIntExtra("res_id", 0);
        Toast.makeText(this, String.valueOf(res_id), Toast.LENGTH_SHORT).show();
    }
}
