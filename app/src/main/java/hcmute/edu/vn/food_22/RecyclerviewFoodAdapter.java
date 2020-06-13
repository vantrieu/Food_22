package hcmute.edu.vn.food_22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerviewFoodAdapter extends RecyclerView.Adapter<RecyclerviewFoodAdapter.MyViewHolder> {

    private Context mContext;
    private List<Food> mData;

    public RecyclerviewFoodAdapter(Context mContext, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflatter = LayoutInflater.from(mContext);
        view = mInflatter.inflate(R.layout.custom_menu_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_food_name.setText(mData.get(position).getFood_name());
        holder.tv_food_price.setText(ConvertString(mData.get(position).getPrice()));

        String urlTemp = mData.get(position).getFood_img();
        Picasso.with(mContext).load(urlTemp).into(holder.img_food);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_food_name, tv_food_price;
        ImageView img_food;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_food_name = (TextView) itemView.findViewById(R.id.txt_food_name);
            tv_food_price = (TextView) itemView.findViewById((R.id.txt_food_price));
            img_food = (ImageView) itemView.findViewById(R.id.img_food_avatar);
        }
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
        return str + "đ";
    }
}
