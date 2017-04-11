package edmanfeng.paddamagecalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;

/**
 * Created by t7500 on 4/6/2017.
 */

public class EditMonsterFragment extends Fragment {
    private static final String TAG = "paddamagecalculator";

    private static final String ARG_MONSTER_ID = "monster id";

    private EditText mName;
    private EditText mHp;
    private EditText mAtk;
    private EditText mRcv;
    private Monster mMonster;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle args = getArguments();
        UUID id = (UUID) args.getSerializable(ARG_MONSTER_ID);
        MonsterLab ml = MonsterLab.get(getActivity());
        Monster monster = ml.getMonster(id);
        if (monster != null) {
            mMonster = monster;
            mName.setText(mMonster.getName());
            mHp.setText(mMonster.getHp());
            mAtk.setText(mMonster.getAtk());
            mRcv.setText(mMonster.getRcv());
        } else {
            mMonster = new Monster();
            mMonster.setId(id);
        }
        Log.d(TAG, "EMF;" +
                mMonster.getName() + ";" +
                mMonster.getId() + ";" +
                mMonster.getHp() + ";" +
                mMonster.getAtk() + ";" +
                mMonster.getRcv());
    }

    public static EditMonsterFragment newInstance(UUID id) {
        EditMonsterFragment fragment = new EditMonsterFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MONSTER_ID, id);
        fragment.setArguments(args);
        return fragment;
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
                mMonster.setName(mName.getText().toString());
                mMonster.setAtk(Integer.valueOf(mAtk.getText().toString()));
                mMonster.setHp(Integer.valueOf(mHp.getText().toString()));
                mMonster.setRcv(Integer.valueOf(mRcv.getText().toString()));
                MonsterLab ml = MonsterLab.get(getActivity());
                ml.addMonster(mMonster);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
