package edmanfeng.paddamagecalculator;

import android.app.Activity;
import android.content.Intent;
import android.databinding.InverseBindingListener;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.Inflater;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.OrbMatch;
import edmanfeng.paddamagecalculator.GameModel.Team;
import edmanfeng.paddamagecalculator.databinding.FragmentTeamPageBinding;
import edmanfeng.paddamagecalculator.databinding.ListItemComboBinding;
import edmanfeng.paddamagecalculator.databinding.ListItemMonsterBinding;

/**
 * This fragment holds team information and allows you to build and calculate
 * damage from combos
 */

public class TeamPageFragment extends Fragment {
    private static final String TAG = "TeamPageFragment";

    private static final String ARG_TEAM_ID = "team_id";

    private static final int REQUEST_MONSTER_UPDATE = 0;
    private static final int REQUEST_MONSTER_ADD = 1;
    private static final int REQUEST_NEW_MONSTER = 2;

    private static final int VIEW_ITEMS_TO_DISPLAY = 6;

    private static final int MIN_ORBS = 0;
    private static final int MAX_ORBS = 42;

    private FragmentTeamPageBinding mTeamPageBinding;
    private Team mTeam;
    private List<OrbMatch> mOrbMatches;
    private OrbMatch mCurrentMatch;

    private boolean mNewTeam;


    public static TeamPageFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(ARG_TEAM_ID, id);

        TeamPageFragment fragment = new TeamPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        String id = getArguments().getString(ARG_TEAM_ID);
        if (id == null) {
            mTeam = new Team();
            mNewTeam = true;
        } else {
            TeamLab teamLab = TeamLab.get(getActivity());
            mTeam = teamLab.getTeam(id);
            mNewTeam = false;
        }

        mOrbMatches = new ArrayList<>();
        mCurrentMatch = new OrbMatch();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mTeamPageBinding = FragmentTeamPageBinding.inflate(inflater);
        mTeamPageBinding.setAdapters(new BindingAdapters());
        mTeamPageBinding.setOrbmatch(mCurrentMatch);
        mTeamPageBinding.setCombolist(mOrbMatches);

        //TODO: https://stackoverflow.com/questions/39201778/android-recyclerview-for-a-few-items

        RecyclerView teamRecyclerView = mTeamPageBinding.teamRecyclerView;
        teamRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.HORIZONTAL, false));
        MonsterAdapter adapter = new MonsterAdapter(mTeam.asList());
        teamRecyclerView.setAdapter(adapter);

        ArrayAdapter<CharSequence> comboTypeAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.orb_types, android.R.layout.simple_spinner_item);
        comboTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTeamPageBinding.comboTypeSpinner.setAdapter(comboTypeAdapter);

        List<Integer> orbCountLimits = new ArrayList<>();
        for (int i = MIN_ORBS; i < MAX_ORBS; i++) {
            orbCountLimits.add(i);
        }

        ArrayAdapter<Integer> comboOrbNumAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_item, orbCountLimits);
        comboOrbNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTeamPageBinding.comboOrbNumberSpinner.setAdapter(comboOrbNumAdapter);

        ArrayAdapter<Integer> comboEnhanceNumAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_item, orbCountLimits);
        comboEnhanceNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTeamPageBinding.comboEnhanceNumberSpinner
                .setAdapter(comboEnhanceNumAdapter);

        ArrayAdapter<CharSequence> comboShapeAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.combo_shape_types, android.R.layout.simple_spinner_item);
        comboShapeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTeamPageBinding.comboShapeSpinner.setAdapter(comboShapeAdapter);


        final RecyclerView comboRecyclerView = mTeamPageBinding.comboRecyclerview;
        comboRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        comboRecyclerView.setAdapter(new ComboAdapter(mOrbMatches));

        mTeamPageBinding.addComboButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentMatch.getCount() > 0 && mCurrentMatch.getEnhanced() <= mCurrentMatch.getCount()) {
                    Log.d(TAG, "good combo: " + mCurrentMatch.toString());
                    mOrbMatches.add(new OrbMatch(mCurrentMatch));
                    mTeamPageBinding.comboRecyclerview.getAdapter().notifyDataSetChanged();
                } else {
                    Log.d(TAG, "bad combo");
                    // display a toast describing the error
                }
            }
        });

        return mTeamPageBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        Log.d(TAG, "Result got");
        if (requestCode == REQUEST_MONSTER_UPDATE) {
            String id = data.getStringExtra(EditMonsterFragment.EXTRA_UPDATE);
            int pos = data.getIntExtra(EditMonsterFragment.EXTRA_POSITION, -1);
            MonsterLab ml = MonsterLab.get(getActivity());
            Monster monster = ml.getMonster(id);
            mTeam.set(pos, monster);
            //mTeamAdapter.notifyItemChanged(pos);

//            monster = FirebaseUtility.getMonster(monster.getNum());
  //          mTeam.set(pos, monster);
            mTeamPageBinding.teamRecyclerView.getAdapter().notifyDataSetChanged();
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

    private class ComboHolder extends RecyclerView.ViewHolder {
        ListItemComboBinding mComboBinding;

        public ComboHolder (ListItemComboBinding binding) {
            super(binding.getRoot());
            mComboBinding = binding;
        }

        public void bindCombo(OrbMatch combo) {
            mComboBinding.setCombo(combo);
        }
    }

    private class ComboAdapter extends RecyclerView.Adapter<ComboHolder> {
        List<OrbMatch> mCombos;
        public ComboAdapter(List<OrbMatch> combos) {
            mCombos = combos;
        }

        @Override
        public ComboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // TODO: pass in inflater instead, so combo holder knows its layout instead of the adapter
            ListItemComboBinding binding = ListItemComboBinding
                    .inflate(LayoutInflater.from(getActivity()), parent, false);
            return new ComboHolder(binding);
        }

        @Override
        public void onBindViewHolder(ComboHolder holder, int position) {
            holder.bindCombo(mCombos.get(position));
        }

        @Override
        public int getItemCount() {
            return mCombos.size();
        }
    }

    private class MonsterHolder extends RecyclerView.ViewHolder {
        private ListItemMonsterBinding mItemBinding;

        public MonsterHolder(ListItemMonsterBinding binding) {
            super(binding.getRoot());
            mItemBinding = binding;
            mItemBinding.monsterItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String id;
                    Monster monster = mItemBinding.getMonster();
                    if (monster == null) {
                        MonsterSearchFragment fragment =
                                MonsterSearchFragment.newInstance(position);
                        fragment.setTargetFragment(TeamPageFragment.this, REQUEST_MONSTER_UPDATE);
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        id = monster.getId();
                        EditMonsterFragment fragment = EditMonsterFragment
                                .newInstance(id, position);

                        fragment.setTargetFragment(TeamPageFragment.this, REQUEST_MONSTER_UPDATE);
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    }

                }
            });
        }

        public void bindMonster(Monster monster, int position) {
            mItemBinding.setMonster(monster);
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
            return new MonsterHolder(ListItemMonsterBinding.inflate(inflater));
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
