package hcmute.edu.vn.food_22;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static hcmute.edu.vn.food_22.MenuRestaurantActivity.mContext;

public class Fragment2 extends Fragment {
    int res_id;
    RecyclerView list_food_recyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        list_food_recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_food);

        RecyclerviewFoodAdapter myAdapter = new RecyclerviewFoodAdapter(mContext, MenuRestaurantActivity.lstFood);
        list_food_recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2));
        list_food_recyclerview.setAdapter(myAdapter);
        return view;
    }
}
