package hcmute.edu.vn.food_22;

import android.content.Context;
import android.content.Intent;
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

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Store> mData;
    private Database db = new Database(mContext, "foody.db", null, 1);

    public RecyclerviewAdapter(Context context, List<Store> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_store, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_name_store.setText(mData.get(position).getRes_name());
        holder.tv_description_store.setText(mData.get(position).getDescription());

        String urlTemp = mData.get(position).getUrl();
        Picasso.with(mContext).load(urlTemp).into(holder.img_store);
        //Glide.with(mContext).load(urlTemp).into(holder.img_store);
        holder.cardview_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
                intent.putExtra("res_id",mData.get(position).getRes_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name_store, tv_description_store;
        ImageView img_store;
        CardView cardview_res;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name_store = (TextView) itemView.findViewById(R.id.txt_name_store);
            tv_description_store = (TextView) itemView.findViewById((R.id.txt_description_store));
            img_store = (ImageView) itemView.findViewById(R.id.img_store_avatar);
            cardview_res = (CardView) itemView.findViewById(R.id.cardview_res);

        }
    }
}
