package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class AddressActivity extends AppCompatActivity {

    ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        listView = (ListView) findViewById(R.id.lstTinh);
        SetupArrayAdapter();
        listView.setAdapter(arrayAdapter);
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
        arrayList.add("TP Hồ Chí Minh");
        arrayAdapter = new ArrayAdapter<String>(AddressActivity.this, simple_list_item_1 ,arrayList);
    }

}
