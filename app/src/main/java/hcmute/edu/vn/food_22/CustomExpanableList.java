package hcmute.edu.vn.food_22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpanableList extends BaseExpandableListAdapter {
    Context context;
    List<String> listFoodGroup;
    HashMap<String, List<Food>> listDataChild;
    @Override
    public int getGroupCount() {
        return listFoodGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listFoodGroup.get(groupPosition)).size();
    }

    public CustomExpanableList(Context context, List<String> listFoodGroup, HashMap<String, List<Food>> listDataChild) {
        this.context = context;
        this.listFoodGroup = listFoodGroup;
        this.listDataChild = listDataChild;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listFoodGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDataChild.get(listFoodGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupName= (String) getGroup(groupPosition);
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.extenableview_group_view,null);
        TextView txtGroup=convertView.findViewById(R.id.text_view_group_header);
        ImageView imageView=convertView.findViewById(R.id.group_food_image);
        if(isExpanded)
            imageView.setImageResource(R.drawable.drop);
        txtGroup.setText(groupName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Food food= (Food) getChild(groupPosition,childPosition);
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.extenable_food_view,null);
        TextView txtFoodName=convertView.findViewById(R.id.text_view_food_name);
        TextView txtFoodPrice=convertView.findViewById(R.id.text_view_food_price);
        txtFoodName.setText(food.getFood_name());
        txtFoodPrice.setText(String.valueOf(food.getPrice()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
