package hcmute.edu.vn.food_22;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    private TextView txttemp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        txttemp = (TextView) view.findViewById(R.id.txtTest);
        Bundle bundle = getArguments();
        Toast.makeText(container.getContext(), String.valueOf(bundle.getInt("res_id")), Toast.LENGTH_SHORT).show();
        return view;
    }
}
