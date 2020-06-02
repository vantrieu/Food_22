package hcmute.edu.vn.food_22;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CustomAdapter extends BaseAdapter {

    String[] result;
    Context context;
    int imageId;
    String tinh;

    public CustomAdapter(Context context, String[] result, int imageId, String tinh) {
        this.context = context;
        this.result = result;
        this.imageId = imageId;
        this.tinh = tinh;
    }

    //Trả về độ dài của mảng chứa nội dung list item
    @Override
    public int getCount() {
        return result.length;
    }

    //Trả về vị trí của mảng chứa nội dung list item
    @Override
    public Object getItem(int position) {
        return position;
    }

    //Trả về vị trí của mảng image list item
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list_item, parent, false);

        final TextView tvNoiDung = (TextView) rowView.findViewById(R.id.tvNoiDung);
        final ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.imgCheck);


        if(result[position].equals(tinh)) {
            tvNoiDung.setText(result[position]);
            tvNoiDung.setTextColor(0xFF03A9F4);
            //imgAvatar.setImageResource(imageId);
            Glide.with(context).load(imageId).into(imgAvatar);
        } else {
            tvNoiDung.setText(result[position]);
            tvNoiDung.setTextColor(Color.BLACK);
        }

        /*rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                tvNoiDung.setTextColor(Color.GREEN);
                imgAvatar.setImageResource(imageId);
            }
        });*/

        return rowView;
    }


}
