package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtVersion;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this, "foody.db", null, 1);
        txtVersion = (TextView) findViewById(R.id.textViewVersion);

        //Tạo các bảng và chèn dữ liệu
        database.CreateTableProvince();
        database.CreateTableRestaurant();
        database.CreateTableTypeFood();
        database.CreateTableFood();

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            txtVersion.setText("Phiên bản: " + packageInfo.versionName);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }, 3000);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
