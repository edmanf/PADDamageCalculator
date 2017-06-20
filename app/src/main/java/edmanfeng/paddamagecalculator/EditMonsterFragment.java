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
import edmanfeng.paddamagecalculator.GameModel.Values;

public class EditMonsterFragment extends Fragment {
    public static final String EXTRA_UPDATE = "edmanfeng.paddamagecalculator.monsterupdate";
    public static final String EXTRA_POSITION = "edmanfeng.paddamagecalculator.monsterposition";

    private static final String TAG = "EditMonsterFragment";
    private static final String ARG_MONSTER_ID = "monster id";
    private static final String ARG_MONSTER_POS = "monster pos";
    private static final String ARG_OWNER = "monster owner";
    private static final String ARG_MONSTER_NUM = "monster num";

    private static final int NO_NUM = -1;

    private EditText mNameEditTxt;
    private EditText mHpEditText;
    private EditText mAtkEditText;
    private EditText mRcvEditText;
    private EditText mNumEditText;


    private Monster mMonster;
    private boolean mNewMonster;
    private int mPos;

    /**
     * Returns a new instance of EditMonsterFragment meant for a saved mosnter
     * @param id
     * @param pos
     * @return
     */
    public static EditMonsterFragment newInstance(String id, int pos) {
        EditMonsterFragment fragment = new EditMonsterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MONSTER_ID, id);
        args.putInt(ARG_MONSTER_POS, pos);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Returns an instance of EditMonsterFragment meant for a monster from Firebase.
     * Monster will have owner set to local
     * @param pos Team slot position of the new monster
     * @param num Monster number
     * @return The fragment instance
     */
    public static EditMonsterFragment newInstance(int pos, int num) {
        EditMonsterFragment fragment = new EditMonsterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MONSTER_POS, pos);
        args.putInt(ARG_MONSTER_NUM, num);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        MonsterLab ml = MonsterLab.get(getActivity());

        Bundle args = getArguments();
        String id = args.getString(ARG_MONSTER_ID);
        int num = args.getInt(ARG_MONSTER_NUM, -1);
        mPos = args.getInt(ARG_MONSTER_POS);
        if (id != null) {

            mMonster = ml.getMonster(id);
            mNewMonster = false;
        } else {
            mMonster = new Monster();
            mNewMonster = true;
            if (num != NO_NUM) {
                mMonster = ml.getFirebaseMonster(num);
                mMonster.setOwner(Values.LOCAL);
            }
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

        setDisplay();
        return v;
    }

    private void setDisplay() {
        Monster monster = MonsterLab.get(getActivity())
                .getMonster(mMonster.getId());
        if (mMonster != null) {
            mNumEditText.setText(Integer.toString(mMonster.getNum()));
            mNameEditTxt.setText(mMonster.getName());
            mHpEditText.setText(Integer.toString(mMonster.getHp()));
            mAtkEditText.setText(Integer.toString(mMonster.getAtk()));
            mRcvEditText.setText(Integer.toString(mMonster.getRcv()));
        }
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
