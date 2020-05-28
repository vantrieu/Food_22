package hcmute.edu.vn.food_22;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Store> mData;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name_store.setText(mData.get(position).getName());
        holder.tv_description_store.setText(mData.get(position).getDescription());

        String urlTemp = mData.get(position).getUrl();
        holder.img_store.setImageDrawable(LoadImageFromWebOperations(urlTemp));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name_store, tv_description_store;
        ImageView img_store;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name_store = (TextView) itemView.findViewById(R.id.txt_name_store);
            tv_description_store = (TextView) itemView.findViewById((R.id.txt_description_store));
            img_store = (ImageView) itemView.findViewById(R.id.img_store_avatar);
        }
    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
