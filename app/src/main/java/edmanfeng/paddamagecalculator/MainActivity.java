package edmanfeng.paddamagecalculator;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends SingleFragmentActivity {

    /*@Override
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }*/

    @Override
    protected Fragment createFragment() {
        return new TeamListFragment();
    }
}
