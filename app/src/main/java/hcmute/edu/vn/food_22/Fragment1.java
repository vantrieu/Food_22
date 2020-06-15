package hcmute.edu.vn.food_22;

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
    List<List<Food>> listDataFood;
    HashMap<String, List<Food>> listDataChild;
    CustomExpanableList customExpanableList;

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
        expandListFood.setAdapter(customExpanableList);
    }

    private void addControl(View v)
    {
        expandListFood=(ExpandableListView) v.findViewById(R.id.expandable_list_view_menu);
        listFoodGroup=new ArrayList<String>();
        listDataChild=new HashMap<String, List<Food>>();
        listFoodGroup.add("Bò Mỹ Nhúng Ớt");
        listFoodGroup.add("Bún Đậu");
        List<Food> listFoodData=new ArrayList<>();
        for(int i=0;i<listFoodGroup.size();i++)
        {
//            if(listFoodGroup.get(i).equals("Bò Mỹ Nhúng Ớt"))
//            {
//                String sql = "food_id, food_name, type_id, description, food_img, price, res_id";
//                listFoodData.add(new Food(1,"Bò Mỹ Núng Ớt Nhỏ",5,"Bò Mỹ Nhúng Ớt", "", 100000, 1));
//                listFoodData.add(new Food("Bò Mỹ Núng Ớt Vừa","239,000","Bò Mỹ Nhúng Ớt"));
//                listFoodData.add(new Food("Bò Mỹ Núng Ớt Lớn","249,000","Bò Mỹ Nhúng Ớt"));
//                listFoodData.add(new Food("Bê Thui Mẹt Nhỏ","119,000","Bò Mỹ Nhúng Ớt"));
//                listFoodData.add(new Food("Bê Thui Mẹt Vừa","239,000","Bò Mỹ Nhúng Ớt"));
//                listFoodData.add(new Food("Bê Thui Mẹt Lớn","249,000","Bò Mỹ Nhúng Ớt"));
//                listFoodData.add(new Food("Heo Luộc Thập Cẩm","119,000","Bò Mỹ Nhúng Ớt"));
//
//            }
//            if(listFoodGroup.get(i).equals("Bún Đậu"))
//            {
//                listFoodData.add(new Food("Bún Đậu Nhỏ","50,000","Bún Đậu"));
//                listFoodData.add(new Food("Bún Đậu Vừa","60,000","Bún Đậu"));
//                listFoodData.add(new Food("Bún Đậu Lớn","70,000","Bún Đậu"));
//            }
//            if(listFoodGroup.get(i).equals("Món Thêm"))
//            {
//                listFoodData.add(new Food("Rau Thêm","20,000","Bún Đậu"));
//                listFoodData.add(new Food("Thịt Thêm","100,000","Bún Đậu"));
//                listFoodData.add(new Food("Bún Thêm","20,000","Bún Đậu"));
//            }
            listDataChild.put(listFoodGroup.get(i),listFoodData);
            listFoodData=new ArrayList<>();
        }
    }
}
