package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {


    private static final int RESULT_CODE = 123;
    private String tinh, temp;
    private TextView txtOK, txtCancel;
    public static int imgAvatar = R.drawable.bluee_tick;
    ListView lvCustomListView;
    public ArrayList<String> tvNoiDung = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        GetDataProvince();


        Intent intent = getIntent();
        temp = tinh = intent.getStringExtra("Tinh");

        lvCustomListView = (ListView) findViewById(R.id.lvCustomListView);

        lvCustomListView.setAdapter(new CustomAdapter(AddressActivity.this, tvNoiDung, imgAvatar, tinh));

        lvCustomListView = (ListView) findViewById(R.id.lvCustomListView);
        txtOK = (TextView) findViewById(R.id.txtOk);
        txtCancel = (TextView) findViewById(R.id.txtCancel);

        lvCustomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                temp = tvNoiDung.get(position);
                lvCustomListView.setSelection(position);
                lvCustomListView.setAdapter(new CustomAdapter(AddressActivity.this, tvNoiDung, imgAvatar, temp));
            }
        });

        txtOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp.equals(tinh)) {
                    sendToHome(RESULT_CODE, tinh);
                }else{
                    sendToHome(RESULT_CODE, temp);
                }
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToHome(RESULT_CODE, tinh);
            }
        });
    }

    public void sendToHome(int resultcode, String flag)
    {
        Intent intent=getIntent();
        intent.putExtra("Tinh", flag);
        setResult(resultcode, intent);
        finish();
    }

    public void GetDataProvince(){
        Database database = new Database(this, "foody.db", null, 1);
        Cursor dataProvince = database.GetData("SELECT * FROM Province");
        while (dataProvince.moveToNext()) {
            tvNoiDung.add(dataProvince.getString(1));
        }
    }

}
