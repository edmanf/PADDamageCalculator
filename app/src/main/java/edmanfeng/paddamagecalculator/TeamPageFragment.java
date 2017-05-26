package edmanfeng.paddamagecalculator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Team;

/**
 * Created by t7500 on 4/3/2017.
 */

public class TeamPageFragment extends Fragment {
    private static final String TAG = "TeamPageFragment";

    private static final String ARG_TEAM_ID = "team_id";

    private static final int REQUEST_MONSTER_UPDATE = 0;

    private static final int VIEW_ITEMS_TO_DISPLAY = 6;

    private static final int MIN_ORBS = 1;
    private static final int MAX_ORBS = 42;

    private RecyclerView mTeamRecyclerView;
    private RecyclerView.Adapter mTeamAdapter;
    private Spinner mComboTypeSpinner;
    private Spinner mComboOrbNumberSpinner;
    private Spinner mComboEnhanceNumberSpinner;
    private Spinner mComboShapeSpinner;
    private Team mTeam;

    private boolean mNewTeam;


    public static TeamPageFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TEAM_ID, uuid);

        TeamPageFragment fragment = new TeamPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID uuid = (UUID) getArguments().getSerializable(ARG_TEAM_ID);
        if (uuid == null) {
            mTeam = new Team();
            mNewTeam = true;
        } else {
            TeamLab teamLab = TeamLab.get(getActivity());
            mTeam = teamLab.getTeam(uuid);
            mNewTeam = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_team_page,container, false);

        mTeamRecyclerView = (RecyclerView)view
                .findViewById(R.id.team_recycler_view);
        mTeamRecyclerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        mTeamAdapter = new MonsterAdapter(mTeam.asList());
        mTeamRecyclerView.setAdapter(mTeamAdapter);

        mComboTypeSpinner = (Spinner)view.findViewById(R.id.combo_type_spinner);
        ArrayAdapter<CharSequence> comboTypeAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.orb_types, android.R.layout.simple_spinner_item);
        comboTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboTypeSpinner.setAdapter(comboTypeAdapter);

        List<Integer> orbCountLimits = new ArrayList<>();
        for (int i = MIN_ORBS; i < MAX_ORBS; i++) {
            orbCountLimits.add(i);
        }
        mComboOrbNumberSpinner = (Spinner)view
                .findViewById(R.id.combo_orb_number_spinner);
        ArrayAdapter<Integer> comboOrbNumAdapter = new ArrayAdapter<Integer>(
                getActivity(), android.R.layout.simple_spinner_item, orbCountLimits);
        comboOrbNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboOrbNumberSpinner.setAdapter(comboOrbNumAdapter);


        mComboEnhanceNumberSpinner = (Spinner) view
                .findViewById(R.id.combo_enhance_number_spinner);
        ArrayAdapter<Integer> comboEnhanceNumAdapter = new ArrayAdapter<Integer>(
                getActivity(), android.R.layout.simple_spinner_item, orbCountLimits);
        comboEnhanceNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboEnhanceNumberSpinner.setAdapter(comboEnhanceNumAdapter);

        mComboShapeSpinner = (Spinner)view.findViewById(R.id.combo_shape_spinner);
        ArrayAdapter<CharSequence> comboShapeAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.combo_shape_types, android.R.layout.simple_spinner_item);
        comboShapeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboShapeSpinner.setAdapter(comboShapeAdapter);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        Log.d(TAG, "Result got");
        if (requestCode == REQUEST_MONSTER_UPDATE) {
            UUID uuid = (UUID) data.getSerializableExtra(EditMonsterFragment.EXTRA_UPDATE);
            int pos = data.getIntExtra(EditMonsterFragment.EXTRA_POSITION, -1);
            MonsterLab ml = MonsterLab.get(getActivity());
            mTeam.setSub(pos, ml.getMonster(uuid));
            //mTeamAdapter.notifyItemChanged(pos);
            mTeamAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_team_page, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                if (!mTeam.isEmpty()) {
                    if (mNewTeam) {
                        TeamLab teamLab = TeamLab.get(getActivity());
                        teamLab.addTeam(mTeam);
                        mNewTeam = false;
                        Log.i(TAG, "Adding team successful");
                    } else {
                        TeamLab.get(getActivity()).updateTeam(mTeam);
                    }
                }
                return true;
            case R.id.menu_item_delete_team:
                if (!mNewTeam) {
                    TeamLab.get(getActivity()).deleteTeam(mTeam);
                }
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class MonsterHolder extends RecyclerView.ViewHolder {
        private ImageButton mMonsterImageButton;
        private Monster mMonster;

        public MonsterHolder(View itemView) {
            super(itemView);
            mMonsterImageButton = (ImageButton) itemView.findViewById(R.id.monster_item);
            mMonsterImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    UUID id;

                    if (mMonster == null) {
                        id = null;
                    } else {
                        id = mMonster.getId();
                    }
                    EditMonsterFragment fragment = EditMonsterFragment.newInstance(id, position);

                    fragment.setTargetFragment(TeamPageFragment.this, REQUEST_MONSTER_UPDATE);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
            int width = mTeamRecyclerView.getMeasuredWidth();
            mMonsterImageButton.getLayoutParams().width = width / VIEW_ITEMS_TO_DISPLAY;
            mMonsterImageButton.getLayoutParams().height = width / VIEW_ITEMS_TO_DISPLAY;
        }

        public void bindMonster(Monster monster, int position) {
            mMonster = monster;

            Uri uri = PictureUtils.getMonsterIconUri(mMonster);
            Log.d(TAG, "Try to get icon at: " + uri.toString());
            Glide.with(getFragmentManager().findFragmentById(R.id.fragment_container))
                    .load(uri)
                    .fitCenter()
                    .into(mMonsterImageButton);
        }

        private int getScreenWidth() {
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity()
                    .getWindowManager()
                    .getDefaultDisplay()
                    .getMetrics(metrics);
            return metrics.widthPixels;
        }
    }

    private class MonsterAdapter extends RecyclerView.Adapter<MonsterHolder> {
        private List<Monster> mMonsters;

        public MonsterAdapter(List<Monster> monsters) {
            mMonsters = monsters;
        }

        @Override
        public MonsterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.list_item_monster, parent, false);
            return new MonsterHolder(v);
        }

        @Override
        public void onBindViewHolder(MonsterHolder holder, int position) {
            holder.bindMonster(mMonsters.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mMonsters.size();
        }
    }
}
