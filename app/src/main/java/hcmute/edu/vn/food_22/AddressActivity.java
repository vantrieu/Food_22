package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class AddressActivity extends AppCompatActivity {

    ListView listView;
    private static final int RESULT_CODE = 123;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;
    private String tinh;
    private TextView txtOK, txtCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Intent intent = getIntent();
        tinh = intent.getStringExtra("Tinh");

        listView = (ListView) findViewById(R.id.lstTinh);
        txtOK = (TextView) findViewById(R.id.txtOk);
        txtCancel = (TextView) findViewById(R.id.txtCancel);

        SetupArrayAdapter();
        ChangeText(tinh);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SetupArrayAdapter();
                ChangeText(arrayList.get(position));
                listView.setAdapter(arrayAdapter);
                listView.setSelection(position);
                tinh = arrayList.get(position);
            }
        });

        txtOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToHome(RESULT_CODE);
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToHome(RESULT_CODE);
            }
        });

    }

    private void SetupArrayAdapter(){
        arrayList = new ArrayList<>();
        arrayList.add("An Giang");
        arrayList.add("Bà Rịa - Vũng Tàu");
        arrayList.add("Bắc Giang");
        arrayList.add("Bắc Kạn");
        arrayList.add("Bạc Liêu");
        arrayList.add("Bắc Ninh");
        arrayList.add("Bến Tre");
        arrayList.add("Bình Định");
        arrayList.add("Bình Dương");
        arrayList.add("Bình Phước");
        arrayList.add("Bình Thuận");
        arrayList.add("Cà Mau");
        arrayList.add("Cao Bằng");
        arrayList.add("Đắk Lắk");
        arrayList.add("Đắk Nông");
        arrayList.add("Điện Biên");
        arrayList.add("Đồng Nai");
        arrayList.add("Đồng Tháp");
        arrayList.add("Gia Lai");
        arrayList.add("Hà Giang");
        arrayList.add("Hà Nam");
        arrayList.add("Hà Tĩnh");
        arrayList.add("Hải Dương");
        arrayList.add("Hậu Giang");
        arrayList.add("Hòa Bình");
        arrayList.add("Hưng Yên");
        arrayList.add("Khánh Hòa");
        arrayList.add("Kiên Giang");
        arrayList.add("Kon Tum");
        arrayList.add("Lai Châu");
        arrayList.add("Lâm Đồng");
        arrayList.add("Lạng Sơn");
        arrayList.add("Lào Cai");
        arrayList.add("Long An");
        arrayList.add("Nam Định");
        arrayList.add("Nghệ An");
        arrayList.add("Ninh Bình");
        arrayList.add("Ninh Thuận");
        arrayList.add("Phú Thọ");
        arrayList.add("Quảng Bình");
        arrayList.add("Quảng Nam");
        arrayList.add("Quảng Ngãi");
        arrayList.add("Quảng Ninh");
        arrayList.add("Quảng Trị");
        arrayList.add("Sóc Trăng");
        arrayList.add("Sơn La");
        arrayList.add("Tây Ninh");
        arrayList.add("Thái Bình");
        arrayList.add("Thái Nguyên");
        arrayList.add("Thanh Hóa");
        arrayList.add("Thừa Thiên Huế");
        arrayList.add("Tiền Giang");
        arrayList.add("Trà Vinh");
        arrayList.add("Tuyên Quang");
        arrayList.add("Vĩnh Long");
        arrayList.add("Vĩnh Phúc");
        arrayList.add("Yên Bái");
        arrayList.add("Phú Yên");
        arrayList.add("Cần Thơ");
        arrayList.add("Đà Nẵng");
        arrayList.add("Hải Phòng");
        arrayList.add("Hà Nội");
        arrayList.add("TP. Hồ Chí Minh");
    }

    private void ChangeText(final String temp){
        arrayAdapter = new ArrayAdapter<String>(AddressActivity.this, simple_list_item_1 ,arrayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                if (tv.getText().equals(temp))
                    tv.setTextColor(Color.GREEN);
                else
                    tv.setTextColor(Color.BLACK);

                // Generate ListView Item using TextView
                return view;
            }
        };
    }

    public void sendToHome(int resultcode)
    {
        Intent intent=getIntent();
        intent.putExtra("Tinh", tinh);
        setResult(resultcode, intent);
        finish();
    }

}
