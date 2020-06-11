package hcmute.edu.vn.food_22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hcmute.edu.vn.food_22.tabslide2.PlaceholderFragment;
import hcmute.edu.vn.food_22.tabslide2.SectionsPagerAdapter;

public class ShowKQAdapter extends BaseAdapter {

    private ShowKQTimKiem context;
    private int layout;
    private List<Store> dsQuan;  //danh sách quán

    public ShowKQAdapter(ShowKQTimKiem context, int layout, List<Store> dsQuan) {
        this.context = context;
        this.layout = layout;
        this.dsQuan = dsQuan;
    }


    @Override
    public int getCount() {
        return dsQuan.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder
    {
        ImageView img_info;
        TextView tenQuan, diaChi, khoangCach, loaiHinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.img_info=convertView.findViewById(R.id.img_thong_tin);
            holder.tenQuan=convertView.findViewById(R.id.tv_ten_quan);
            holder.diaChi=convertView.findViewById(R.id.tv_diachi);
            holder.khoangCach=convertView.findViewById(R.id.tv_khoangcach);
            holder.loaiHinh=convertView.findViewById(R.id.tv_loaihinh);
            convertView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        final Store infoQuan=dsQuan.get(position);
        holder.tenQuan.setText(infoQuan.getRes_name());
        holder.diaChi.setText(infoQuan.getRes_address());
        holder.khoangCach.setText("0 km");
        holder.loaiHinh.setText(infoQuan.getTes_type());
        Picasso.with(context).load(infoQuan.getUrl()).into(holder.img_info);
        return convertView;
    }


}
