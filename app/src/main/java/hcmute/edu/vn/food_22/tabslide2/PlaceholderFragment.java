package hcmute.edu.vn.food_22.tabslide2;

import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import java.util.ArrayList;
import java.util.List;
import hcmute.edu.vn.food_22.Database;
import hcmute.edu.vn.food_22.GeocodingLocation;
import hcmute.edu.vn.food_22.MainActivity;
import hcmute.edu.vn.food_22.R;
import hcmute.edu.vn.food_22.RestaurantDetailActivity;
import hcmute.edu.vn.food_22.ShowKQAdapter;
import hcmute.edu.vn.food_22.ShowKQTimKiem;
import hcmute.edu.vn.food_22.Store;

import static hcmute.edu.vn.food_22.ShowKQTimKiem.resourceIds;

public class PlaceholderFragment extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    public ShowKQAdapter showKQAdapter;
    public static ShowKQTimKiem context;
    public static String text_input_by_user;
    public static int province_id;
    private PageViewModel pageViewModel;
    List<Store> arrayList;
    List<Store> arrayList2;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        int index = getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView = inflater.inflate(resourceIds[index], container, false);
        ListView listView;
        switch (index) {
            case 0:
                listView=(ListView) rootView.findViewById(R.id.listView1_slide2);
                loadDATA(1);
                listView.setAdapter(showKQAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, RestaurantDetailActivity.class);
                        intent.putExtra("res_id",arrayList.get(position).getRes_id());
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                listView=(ListView) rootView.findViewById(R.id.listView2_slide2);
                loadDATA(2);
                listView.setAdapter(showKQAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, RestaurantDetailActivity.class);
                        intent.putExtra("res_id",arrayList.get(position).getRes_id());
                        context.startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
        return rootView;
    }

    private void loadDATA(int tab)
    {
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();

        Cursor cursor=get_by_food(text_input_by_user,province_id);
        if(cursor.getCount()<1)
        {
            cursor=get_by_name(text_input_by_user,province_id);
            if(cursor.getCount()<1)
            {
                Toast.makeText(context, "Không tìm thấy kết quả phù hợp", Toast.LENGTH_SHORT).show();
            }
        }
        if (tab==2)
        {
            while (cursor.moveToNext()) {
                GeocodingLocation g = new GeocodingLocation();
                Location temp = g.getLatLngFromAddress(cursor.getString(3), context);
                double distance = g.Calculate(temp.getLatitude(), temp.getLongitude(), MainActivity.mLastLocation.getLatitude(), MainActivity.mLastLocation.getLongitude());
                if (MainActivity.isGPSEnabled &&distance < 3) {
                    arrayList2.add(new Store(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            distance,
                            cursor.getString(4),
                            cursor.getString(5),
                            null,
                            null,
                            cursor.getInt(6),
                            cursor.getInt(7),
                            cursor.getInt(8)));
                }
            }
            showKQAdapter = new ShowKQAdapter(context,R.layout.ketqua_timkiem_item,arrayList2);
        }
        else
        {
            while (cursor.moveToNext()) {
                GeocodingLocation g = new GeocodingLocation();
                Location temp = g.getLatLngFromAddress(cursor.getString(3), context);
                double distance = g.Calculate(temp.getLatitude(), temp.getLongitude(), MainActivity.mLastLocation.getLatitude(), MainActivity.mLastLocation.getLongitude());
                arrayList.add(new Store(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        distance,
                        cursor.getString(4),
                        cursor.getString(5),
                        null,
                        null,
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8)));
            }
            showKQAdapter = new ShowKQAdapter(context,R.layout.ketqua_timkiem_item,arrayList);
        }
    }
    private Cursor get_by_name(String name, int province)
    {
        Database dt=new Database(context, "foody.db", null, 1);
        Cursor cursor=dt.GetData("SELECT DISTINCT res_id, res_name, res_type, res_address, Restaurant.description, res_img, province_id, has_wifi, has_delivery FROM Restaurant WHERE province_id="+province+" AND res_name LIKE '%"+name+"%'");
        return cursor;
    }

    private Cursor get_by_food(String str,int province)
    {
        Database dt=new Database(context, "foody.db", null, 1);
        Cursor cursor=dt.GetData("SELECT DISTINCT Restaurant.res_id, res_name, res_type, res_address, Restaurant.description, res_img, province_id, has_wifi, has_delivery FROM Restaurant LEFT JOIN Food ON Restaurant.res_id=Food.res_id WHERE province_id="+province+" AND (food_name LIKE '%"+str+"%' or res_name LIKE '%"+str+"%')");
        return cursor;
    }
}
