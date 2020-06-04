package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {


    private static final int RESULT_CODE = 123;
    private String tinh, temp;
    private TextView txtOK, txtCancel;
    private EditText editTextFind;
    public static int imgAvatar = R.drawable.bluee_tick;
    ListView lvCustomListView;
    public ArrayList<String> tvNoiDung = new ArrayList<>();
    private Database database = new Database(this, "foody.db", null, 1);
    private int province_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        GetDataProvince();


        Intent intent = getIntent();
        province_id = intent.getIntExtra("province_id", 0);
        temp = tinh = GetProvinceName();

        lvCustomListView = (ListView) findViewById(R.id.lvCustomListView);

        lvCustomListView.setAdapter(new CustomAdapter(AddressActivity.this, tvNoiDung, imgAvatar, tinh));

        lvCustomListView = (ListView) findViewById(R.id.lvCustomListView);
        txtOK = (TextView) findViewById(R.id.txtOk);
        txtCancel = (TextView) findViewById(R.id.txtCancel);
        editTextFind = (EditText) findViewById(R.id.editTextFind);

        lvCustomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                temp = tvNoiDung.get(position);
                lvCustomListView.setAdapter(new CustomAdapter(AddressActivity.this, tvNoiDung, imgAvatar, temp));
                lvCustomListView.setSelection(position);
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

        editTextFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final ArrayList<String> lstTinh = new ArrayList<>();
                if(!s.equals("")){
                    Cursor dataProvince = database.GetData("SELECT * FROM Province WHERE name LIKE '" + editTextFind.getText() + "%'");
                    while (dataProvince.moveToNext()) {
                        lstTinh.add(dataProvince.getString(1));
                    }
                    lvCustomListView.setAdapter(new CustomAdapter(AddressActivity.this, lstTinh, imgAvatar, tinh));
                    lvCustomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            temp = lstTinh.get(position);
                            lvCustomListView.setAdapter(new CustomAdapter(AddressActivity.this, lstTinh, imgAvatar, temp));
                            lvCustomListView.setSelection(position);
                        }
                    });
                } else {
                    lvCustomListView.setAdapter(new CustomAdapter(AddressActivity.this, tvNoiDung, imgAvatar, tinh));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void sendToHome(int resultcode, String flag)
    {
        //Intent intent=getIntent();
        Intent intent=new Intent(AddressActivity.this, HomeActivity.class);
        province_id = GetProvinceId(flag);
        intent.putExtra("province_id", province_id);
        setResult(resultcode, intent);
        finish();
    }

    public void GetDataProvince(){
        Cursor dataProvince = database.GetData("SELECT * FROM Province");
        while (dataProvince.moveToNext()) {
            tvNoiDung.add(dataProvince.getString(1));
        }
    }

    //khi không chọn tỉnh nào, ấn back để quay lại. ứng dụng bị dừng đột ngột (Fix sau)
    @Override
    protected void onDestroy() {
        /*Intent intent = getIntent();
        tinh = intent.getStringExtra("Tinh");
        sendToHome(RESULT_CODE, tinh);*/
        super.onDestroy();
    }

    private String GetProvinceName(){
        Cursor dataFlag = database.GetData("SELECT * FROM Province WHERE province_id = " + province_id);
        dataFlag.moveToFirst();
        return dataFlag.getString(1);
    }

    private int GetProvinceId(String flag){
        Cursor dataFlag = database.GetData("SELECT * FROM Province WHERE name = '" + flag + "'");
        dataFlag.moveToFirst();
        return dataFlag.getInt(0);
    }
}
