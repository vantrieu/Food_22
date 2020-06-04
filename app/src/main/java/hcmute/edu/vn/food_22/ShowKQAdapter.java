package hcmute.edu.vn.food_22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ShowKQAdapter extends BaseAdapter {

    private ShowKQTimKiem context;
    private int layout;
    private List<InfoQuan> dsQuan;  //danh sách quán

    public ShowKQAdapter(ShowKQTimKiem context, int layout, List<InfoQuan> dsQuan) {
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
        final InfoQuan infoQuan=dsQuan.get(position);
        holder.tenQuan.setText(infoQuan.getTenQuan());
        holder.diaChi.setText(infoQuan.getDiaChi());
        holder.khoangCach.setText(String.valueOf(infoQuan.getKhoangCach())+" km");
        holder.loaiHinh.setText(infoQuan.getLoaiHinh());
        holder.img_info.setImageResource(infoQuan.getThumbnail());
        return convertView;
    }


}
