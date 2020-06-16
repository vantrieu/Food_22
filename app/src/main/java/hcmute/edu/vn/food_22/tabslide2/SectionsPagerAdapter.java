package hcmute.edu.vn.food_22.tabslide2;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import hcmute.edu.vn.food_22.ShowKQAdapter;
import hcmute.edu.vn.food_22.ShowKQTimKiem;


import static hcmute.edu.vn.food_22.ShowKQTimKiem.tabs;


public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    private final ShowKQTimKiem mContext;
    ShowKQAdapter adapter;
    String text_input_by_user;
    int province_id;

    public SectionsPagerAdapter(ShowKQTimKiem context,String p_input, int p_provin_id, FragmentManager fm) {
        super(fm);
        mContext = context;
        text_input_by_user=p_input;
        province_id=p_provin_id;
        PlaceholderFragment.showKQAdapter=adapter;
        PlaceholderFragment.context=mContext;
        PlaceholderFragment.text_input_by_user=text_input_by_user;
        PlaceholderFragment.province_id=province_id;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
