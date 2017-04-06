package edmanfeng.paddamagecalculator;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Team;

/**
 * Created by t7500 on 4/3/2017.
 */

public class TeamPageFragment extends Fragment {
    private static final String TAG = "paddamagecalculator";

    private static final String ARG_TEAM_ID = "team_id";

    private static final int MIN_ORBS = 1;
    private static final int MAX_ORBS = 42;

    private RecyclerView mTeamRecyclerView;
    private Spinner mComboTypeSpinner;
    private Spinner mComboOrbNumberSpinner;
    private Spinner mComboEnhanceNumberSpinner;
    private Spinner mComboShapeSpinner;
    private Team mTeam;


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
        } else {
            TeamLab teamLab = TeamLab.get(getActivity());
            mTeam = teamLab.getTeam(uuid);
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
        mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mTeamRecyclerView.setAdapter(new MonsterAdapter(mTeam.asList()));

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_team_page, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                TeamLab teamLab = TeamLab.get(getActivity());
                Team team = new Team();
                Monster leader = new Monster();
                leader.setName("A");
                team.setLeader(leader);
                Monster friend = new Monster();
                friend.setName("B");
                team.setFriend(friend);
                ArrayList<Monster> subs = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    team.setSub(i, new Monster());
                }

                teamLab.addTeam(team);
                Log.i(TAG, "Adding team successful");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class MonsterHolder extends RecyclerView.ViewHolder {
        private TextView mExampleText;

        public MonsterHolder(View itemView) {
            super(itemView);
            mExampleText = (TextView) itemView.findViewById(R.id.monster_item);
        }

        public void bindMonster(Monster monster) {
            if (monster == null) {
                mExampleText.setText("NA");
            } else {
                mExampleText.setText(monster.getName());
            }
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
            holder.bindMonster(mMonsters.get(position));
        }

        @Override
        public int getItemCount() {
            return mMonsters.size();
        }
    }
}
