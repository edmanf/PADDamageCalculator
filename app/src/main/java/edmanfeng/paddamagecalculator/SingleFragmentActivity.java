package edmanfeng.paddamagecalculator;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * An activity that hosts a single full screen fragment.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity{

    protected abstract Fragment createFragment();

    /**
     * Returns the layout id of the activity that hosts the fragment.
     * @return The layout id of the activity.
     */
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.navigation_drawer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId()); // sets the activity's layout

        // expand the fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.drawer_layout);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
