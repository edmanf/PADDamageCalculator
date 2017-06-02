package edmanfeng.paddamagecalculator;

import android.app.Activity;
import android.content.Intent;
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

import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;

/**
 * Created by t7500 on 4/6/2017.
 */

public class EditMonsterFragment extends Fragment {
    public static final String EXTRA_UPDATE = "edmanfeng.paddamagecalculator.monsterupdate";
    public static final String EXTRA_POSITION = "edmanfeng.paddamagecalculator.monsterposition";

    private static final String TAG = "EditMonsterFragment";
    private static final String ARG_MONSTER_ID = "monster id";
    private static final String ARG_MONSTER_POS = "monster pos";

    private EditText mNameEditTxt;
    private EditText mHpEditText;
    private EditText mAtkEditText;
    private EditText mRcvEditText;
    private EditText mNumEditText;


    private Monster mMonster;
    private boolean mNewMonster;
    private int mPos;

    public static EditMonsterFragment newInstance(UUID id, int pos) {
        EditMonsterFragment fragment = new EditMonsterFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MONSTER_ID, id);
        args.putSerializable(ARG_MONSTER_POS, pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle args = getArguments();
        UUID id = (UUID) args.getSerializable(ARG_MONSTER_ID);
        mPos = args.getInt(ARG_MONSTER_POS);
        if (id == null) {
            mMonster = new Monster();
            mNewMonster = true;
        } else {
            MonsterLab ml = MonsterLab.get(getActivity());
            mMonster = ml.getMonster(id);
            mNewMonster = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_monster, container, false);

        mNameEditTxt = (EditText) v
                .findViewById(R.id.monster_name_edit_text);

        mHpEditText = (EditText) v
                .findViewById(R.id.monster_hp_edit_text);

        mAtkEditText = (EditText) v
                .findViewById(R.id.monster_atk_edit_text);

        mRcvEditText = (EditText) v
                .findViewById(R.id.monster_rcv_edit_text);

        mNumEditText = (EditText) v
                .findViewById(R.id.monster_id_edit_text);

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
                MonsterLab ml = MonsterLab.get(getActivity());
                mMonster.setName(mNameEditTxt.getText().toString());
                mMonster.setAtk(Integer.valueOf(mAtkEditText.getText().toString()));
                mMonster.setHp(Integer.valueOf(mHpEditText.getText().toString()));
                mMonster.setRcv(Integer.valueOf(mRcvEditText.getText().toString()));
                mMonster.setNum(Integer.valueOf(mNumEditText.getText().toString()));
                if (mNewMonster) {
                    ml.addMonster(mMonster);
                } else {
                    ml.updateMonster(mMonster);
                }
                sendResult(Activity.RESULT_OK, true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendResult(int resultCode, boolean updated) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_UPDATE, mMonster.getId());
        intent.putExtra(EXTRA_POSITION, mPos);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
