package hcmute.edu.vn.food_22;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static hcmute.edu.vn.food_22.MenuRestaurantActivity.mContext;

public class Fragment1 extends Fragment {

    ExpandableListView expandListFood;
    List<String> listFoodGroup;
    HashMap<String, List<FoodMenu>> listDataChild;
    CustomExpanableList customExpanableList;
    Database database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        database = new Database(mContext, "foody.db", null, 1);
        addControl(view);
        customExpanableList = new CustomExpanableList(mContext, listFoodGroup, listDataChild);
        expandListFood.setAdapter(customExpanableList);
        OpenGroup();
        return view;
    }

    private void OpenGroup() {
        expandListFood.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                customExpanableList=new CustomExpanableList(mContext, listFoodGroup, listDataChild){
                    @Override
                    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

                        View view = super.getGroupView(groupPosition,isExpanded, convertView, parent);
                        TextView tv = view.findViewById(R.id.text_view_group_header);
                        ImageView img=view.findViewById(R.id.group_food_image);
                        img.setImageResource(R.drawable.drop);
                        return view;
                    }
                };
            }
        });

    }

    private void addControl(View v) {
        expandListFood=(ExpandableListView) v.findViewById(R.id.expandable_list_view_menu);
        listFoodGroup=new ArrayList<String>();
        listDataChild=new HashMap<String, List<FoodMenu>>();
        listFoodGroup = GetListType();
        List<FoodMenu> listFoodData=new ArrayList<>();
        for(int i=0;i<listFoodGroup.size();i++)
        {
            listFoodData = GetListFoodData(listFoodGroup.get(i));
            listDataChild.put(listFoodGroup.get(i),listFoodData);
        }
    }

    private List<FoodMenu> GetListFoodData(String s) {
        Cursor dataType = database.GetData("SELECT type_id FROM Type_Food WHERE Type_Food.type_name = '" + s + "'");
        dataType.moveToFirst();
        int flag = (int) dataType.getInt(0);
        List<FoodMenu> listFoodData=new ArrayList<>();
        Cursor dataFood = database.GetData("SELECT food_name, price FROM Food WHERE res_id = " + MenuRestaurantActivity.Res_id +
                " AND type_id = " + flag);
        while (dataFood.moveToNext()) {
            listFoodData.add(new FoodMenu(dataFood.getString(0), ConvertString(dataFood.getInt(1))));
        }
        return listFoodData;
    }

    private ArrayList<String> GetListType(){
        ArrayList<String> listTypeFood=new ArrayList<String>();
        List<Food> lstFood=new ArrayList<>();
        Cursor dataFood = database.GetData("SELECT * FROM Food WHERE Food.res_id = " + MenuRestaurantActivity.Res_id);
        while (dataFood.moveToNext()) {
            lstFood.add( new Food(dataFood.getInt(0), dataFood.getString(1),  dataFood.getInt(2), dataFood.getString(3),
                    dataFood.getString(4), dataFood.getInt(5), dataFood.getInt(6)));
        }
        for(int i = 0 ; i < lstFood.size(); i++){
            int flag = lstFood.get(i).getType_id();
            Cursor DataType = database.GetData("SELECT type_name FROM Type_Food WHERE Type_Food.type_id = " + flag);
            DataType.moveToFirst();
            if(!listTypeFood.contains(String.valueOf(DataType.getString(0))))
                listTypeFood.add(String.valueOf(DataType.getString(0)));
        }
        return listTypeFood;
    }

    private String ConvertString(int temp){
        String s = String.valueOf(temp);
        StringBuilder str = new StringBuilder(s);
        int count = s.length();
        int flag = 0;
        for (int i = (count -1); i > 0 ; i--){
            flag += 1;
            if(flag % 3 == 0){
                str.insert(i, ".");
            }
        }
        return str.toString();
    }
}
