package edmanfeng.paddamagecalculator;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Team;
import edmanfeng.paddamagecalculator.database.TeamBaseHelper;

/**
 * Created by t7500 on 2/27/2017.
 */

public class TeamListFragment extends Fragment {
    private static final String TAG = "TeamListFragment";


    private RecyclerView mTeamRecyclerView;
    private TeamAdapter mAdapter;
    private SQLiteDatabase mTeamDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_team:
                TeamPageFragment teamPageFragment = TeamPageFragment.newInstance(null);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, teamPageFragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.menu_item_manage_accounts:
                AccountSyncFragment fragment = AccountSyncFragment
                        .newInstance();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the recycler layout and save the recycler view
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);

        mTeamDatabase = new TeamBaseHelper(getContext()).getWritableDatabase();

        mTeamRecyclerView = (RecyclerView) view
                .findViewById(R.id.team_list_recycler_view);
        mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTeamRecyclerView.setAdapter(
                new TeamAdapter(TeamLab.get(getContext()).getTeams()));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_team_list, menu);
    }

    private class TeamHolder extends RecyclerView.ViewHolder
            implements  View.OnClickListener {

        private Team mTeam;
        private TextView mTeamNameTextView;


        public TeamHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTeamNameTextView = (TextView) itemView.findViewById(R.id.team_list_item_name);
        }

        @Override
        public void onClick(View v) {
            TeamPageFragment teamPageFragment =
                    TeamPageFragment.newInstance(mTeam.getId());

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, teamPageFragment)
                    .addToBackStack(null)
                    .commit();
        }

        public void bindTeam(Team team) {
            mTeam = team;
            mTeamNameTextView.setText(team.getLeader().getName());
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
                    .inflate(R.layout.list_item_team, parent, false);
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
