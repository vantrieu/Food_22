package hcmute.edu.vn.food_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AddressActivity extends AppCompatActivity {


    private static final int RESULT_CODE = 123;
    private String tinh, temp;
    private TextView txtOK, txtCancel;
    public static int imgAvatar = R.drawable.bluee_tick;
    ListView lvCustomListView;
    public static String[] tvNoiDung = {"An Giang","Bà Rịa – Vũng Tàu","Bắc Giang","Bắc Kạn","Bạc Liêu","Bắc Ninh","Bến Tre","Bình Định","Bình Dương","Bình Phước","Bình Thuận","Cà Mau",
            "Cần Thơ","Cao Bằng","Đà Nẵng","Đắk Lắk","Đắk Nông","Điện Biên","Đồng Nai","Đồng Tháp","Gia Lai","Hà Giang","Hà Nam","Hà Nội","Hà Tĩnh","Hải Dương","Hải Phòng","Hậu Giang",
            "Hòa Bình","Hưng Yên","Khánh Hòa","Kiên Giang","Kon Tum","Lai Châu","Lâm Đồng","Lạng Sơn","Lào Cai","Long An","Nam Định","Nghệ An","Ninh Bình","Ninh Thuận","Phú Thọ","Phú Yên",
            "Quảng Bình","Quảng Nam","Quảng Ngãi","Quảng Ninh","Quảng Trị","Sóc Trăng","Sơn La","Tây Ninh","Thái Bình","Thái Nguyên","Thanh Hóa","Thừa Thiên Huế","Tiền Giang",
            "TP. Hồ Chí Minh","Trà Vinh","Tuyên Quang","Vĩnh Long","Vĩnh Phúc","Yên Bái"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

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
                temp = tvNoiDung[position];
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

}
