package hcmute.edu.vn.food_22;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/**
 *  tạo các bảng cho csdl.
 * Hiện mới chỉ tạo bảng Province và chèn dữ liệu vào. Các bảng còn lại chưa được tạo.
 * Xem lại csdl đã hợp lý chưa. Nếu hợp lý gọi khởi tạo bảng trong mainactivity như đã khởi tạo với bảng Province
* */
public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Truy van khong tra ve ket qua (CREATE, INSERT, UPDATE, DELETE)
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //Truy van tra ve ket qua (SELECT)
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    //Tao bang va chen du lieu tinh thanh
    public void CreateTableProvince(){
        String sql = "CREATE TABLE IF NOT EXISTS Province(province_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(254))";
        QueryData(sql);
        Cursor dataProvince = GetData("SELECT * FROM Province");
        //Chen tinh vao bang Province (NOTE: Chi chay 1 lan duy nhat de tranh bi trung code)
        if(dataProvince.getCount() == 0) {
            String[] tvNoiDung = {"An Giang", "Bà Rịa – Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước",
                    "Bình Thuận", "Cà Mau","Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang",
                    "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu",
                    "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam",
                    "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang",
                    "TP. Hồ Chí Minh", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"};
            for (int i=0; i<tvNoiDung.length; i++){
                sql = "INSERT INTO Province VALUES(null, '" +  tvNoiDung[i].toString() + "')";
                QueryData(sql);
            }
        }
    }

    //Tao bang Restaurant va chen cac du lieu
    public void CreateTableRestaurant(){
        String sql = "CREATE TABLE IF NOT EXISTS Restaurant(res_id INTEGER PRIMARY KEY AUTOINCREMENT, res_name VARCHAR(254), res_type VARCHAR(254), res_address TEXT, " +
                "res_img TEXT, res_open VARCHAR(254), res_close VARCHAR(254), province_id INTEGER, has_wifi TINYINT, has_delivery TINYINT, has_free_parking TINYINT," +
                " FOREIGN KEY (province_id) REFERENCES Province(province_id))";
        QueryData(sql);
        Cursor dataRes = GetData("SELECT * FROM Restaurant");
        if(dataRes.getCount() == 0) {

        }
    }

    //Tao bang type_food va chen cac du lieu lien quan
    public void CreateTableTypeFood(){
        String sql = "CREATE TABLE IF NOT EXISTS Type_Food(type_id INTEGER PRIMARY KEY AUTOINCREMENT, type_name VARCHAR(254))";
        QueryData(sql);
        Cursor dataType = GetData("SELECT * FROM Type_Food");
        if(dataType.getCount() == 0) {

        }
    }

    //Tao bang food va chen cac du lieu lien quan
    public void CreateTableFood(){
        String sql = "CREATE TABLE IF NOT EXISTS Food(food_id INTEGER PRIMARY KEY AUTOINCREMENT, food_name VARCHAR(254), type_id INTEGER, " +
                "description VARCHAR(254), food_img TEXT, price bigint, res_id INTEGER, FOREIGN KEY (res_id) REFERENCES Restaurant(res_id), " +
                "FOREIGN KEY (type_id) REFERENCES Type_Food(type_id))";
        QueryData(sql);
        Cursor dataFood = GetData("SELECT * FROM Type_Food");
        if(dataFood.getCount() == 0) {

        }
    }

    //Tao bang Wifi vaf chen cac du lieu lien quan
    public void CreateTableWifi(){
        String sql = "CREATE TABLE IF NOT EXISTS Wifi(wifi_id INTEGER PRIMARY KEY AUTOINCREMENT, wifi_name VARCHAR(254), wifi_pass VARCHAR(254)," +
                "res_id INTEGER, FOREIGN KEY (res_id) REFERENCES Restaurant(res_id))";
        QueryData(sql);
        Cursor dataWifi = GetData("SELECT * FROM Type_Food");
        if(dataWifi.getCount() == 0) {

        }
    }
}
