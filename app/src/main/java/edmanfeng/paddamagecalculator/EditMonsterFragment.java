package edmanfeng.paddamagecalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import edmanfeng.paddamagecalculator.GameModel.Monster;

/**
 * Created by t7500 on 4/6/2017.
 */

public class EditMonsterFragment extends Fragment {
    private static final String TAG = "paddamagecalculator";

    private EditText mName;
    private EditText mHp;
    private EditText mAtk;
    private EditText mRcv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static EditMonsterFragment newInstance() {
        return new EditMonsterFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_monster, container, false);

        mName = (EditText) v
                .findViewById(R.id.monster_name_edit_text);

        mHp = (EditText) v
                .findViewById(R.id.monster_hp_edit_text);

        mAtk = (EditText) v
                .findViewById(R.id.monster_atk_edit_text);

        mRcv = (EditText) v
                .findViewById(R.id.monster_rcv_edit_text);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_edit_monster, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_monster_save:
                Monster monster = new Monster();
                monster.setName(mName.getText().toString());
                monster.setAtk(Integer.valueOf(mAtk.getText().toString()));
                monster.setHp(Integer.valueOf(mHp.getText().toString()));
                monster.setRcv(Integer.valueOf(mRcv.getText().toString()));
                MonsterLab ml = MonsterLab.get(getActivity());
                ml.addMonster(monster);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
