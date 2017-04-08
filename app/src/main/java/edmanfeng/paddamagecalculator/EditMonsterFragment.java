package edmanfeng.paddamagecalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by t7500 on 4/6/2017.
 */

public class EditMonsterFragment extends Fragment {
    private static final String TAG = "paddamagecalculator";
    public static EditMonsterFragment newInstance() {
        return new EditMonsterFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_monster, container, false);


        return v;
    }
}
