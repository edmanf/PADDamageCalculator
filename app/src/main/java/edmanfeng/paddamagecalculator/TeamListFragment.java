package edmanfeng.paddamagecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Team;

/**
 * Created by t7500 on 2/27/2017.
 */

public class TeamListFragment extends Fragment {

    private RecyclerView mTeamRecyclerView;
    private TeamAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the recycler layout and save the recycler view
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);

        ArrayList<Team> tempTeams = new ArrayList<Team>();
        Team tempTeam = new Team();
        tempTeam.setLeader(new Monster("Example", 1234));
        tempTeams.add(tempTeam);

        mTeamRecyclerView = (RecyclerView) view
                .findViewById(R.id.team_list_recycler_view);
        mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTeamRecyclerView.setAdapter(new TeamAdapter(tempTeams));
        return view;
    }

    private class TeamHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private ImageView mMonsterImageView;
        private TextView mExampleTextView;

        public TeamHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);


            mExampleTextView = (TextView) itemView;
            /*
            mMonsterImageView = (ImageView) itemView
                    .findViewById(R.id.list_monster_image);*/

        }

        @Override
        public void onClick(View v) {
            TeamPageFragment teamPageFragment = TeamPageFragment.newInstance();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, teamPageFragment)
                    .addToBackStack(null)
                    .commit();
        }

        public void bindTeam(Team team) {
            mExampleTextView.setText(team.getLeader().getName());
        }
    }

    private class TeamAdapter extends RecyclerView.Adapter<TeamHolder> {

        private List<Team> mTeamList;

        public TeamAdapter(List<Team> teamList) {
            mTeamList = teamList;
        }

        @Override
        public TeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new TeamHolder(view);
        }

        @Override
        public void onBindViewHolder(TeamHolder holder, int position) {
            holder.bindTeam(mTeamList.get(position));
        }

        @Override
        public int getItemCount() {
            return mTeamList.size();
        }
    }
}
