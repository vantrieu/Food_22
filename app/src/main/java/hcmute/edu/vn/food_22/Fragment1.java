package hcmute.edu.vn.food_22;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static hcmute.edu.vn.food_22.MenuRestaurantActivity.mContext;

public class Fragment1 extends Fragment {

    ExpandableListView expandListFood;
    List<String> listFoodGroup;
    List<List<Food>> listDataFood;
    HashMap<String, List<FoodMenu>> listDataChild;
    CustomExpanableList customExpanableList;
    Database database = new Database(mContext, "foody.db", null, 1);
    //List<Food> lstFood;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
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

    private void addControl(View v)
    {
        expandListFood=(ExpandableListView) v.findViewById(R.id.expandable_list_view_menu);
        listFoodGroup=new ArrayList<String>();
        listDataChild=new HashMap<String, List<FoodMenu>>();
        listFoodGroup = GetListType();
        List<FoodMenu> listFoodData=new ArrayList<>();
        for(int i=0;i<listFoodGroup.size();i++)
        {
            //Toast.makeText(mContext, listFoodGroup.get(i), Toast.LENGTH_SHORT).show();
            //listFoodData = GetListFoodData(listFoodGroup.get(i));
            if(listFoodGroup.get(i).equals("Bò Mỹ nhúng ớt"))
            {
                listFoodData.add(new FoodMenu("Bò Mỹ Núng Ớt Nhỏ","119,000"));
                listFoodData.add(new FoodMenu("Bò Mỹ Núng Ớt Vừa","239,000"));
                listFoodData.add(new FoodMenu("Bò Mỹ Núng Ớt Lớn","249,000"));
                listFoodData.add(new FoodMenu("Bê Thui Mẹt Nhỏ","119,000"));
                listFoodData.add(new FoodMenu("Bê Thui Mẹt Vừa","239,000"));
                listFoodData.add(new FoodMenu("Bê Thui Mẹt Lớn","249,000"));
                listFoodData.add(new FoodMenu("Heo Luộc Thập Cẩm","119,000"));

            }
//            if(listFoodGroup.get(i).equals("Bún đậu"))
//            {
//                listFoodData.add(new FoodMenu("Bún Đậu Nhỏ","50,000"));
//                listFoodData.add(new FoodMenu("Bún Đậu Vừa","60,000"));
//                listFoodData.add(new FoodMenu("Bún Đậu Lớn","70,000"));
//            }
//            if(listFoodGroup.get(i).equals("Món Thêm"))
//            {
//                listFoodData.add(new FoodMenu("Rau Thêm","20,000"));
//                listFoodData.add(new FoodMenu("Thịt Thêm","100,000"));
//                listFoodData.add(new FoodMenu("Bún Thêm","20,000"));
//            }
            listDataChild.put(listFoodGroup.get(i),listFoodData);
            listFoodData=new ArrayList<>();
        }
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
}
