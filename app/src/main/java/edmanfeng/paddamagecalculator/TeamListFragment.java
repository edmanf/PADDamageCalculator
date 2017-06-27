package edmanfeng.paddamagecalculator;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.CalculateDamage;
import edmanfeng.paddamagecalculator.GameModel.Damage;
import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.OrbMatch;
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

        // Call here to trigger the retrieval of FB data
        MonsterLab.get(getActivity());
        Team team = new Team();
        //String uuid, String owner, String name,
        //int num, int hp, int atk, int rcv, int[] attributes
        Monster kuvia = new Monster(
                UUID.randomUUID().toString(), "", "Kuvia",
                1, 3217, 1516, 590,
                new int[]{Monster.Attribute.FIRE, Monster.Attribute.WATER}
        );
        Monster odin = new Monster(
                UUID.randomUUID().toString(), "", "Odin",
                2, 4531, 1291, 548,
                new int[]{Monster.Attribute.WATER, Monster.Attribute.DARK}
        );
        Monster gilliam = new Monster(
                UUID.randomUUID().toString(), "", "Gilliam",
                3, 2213, 1659, 272,
                new int[]{Monster.Attribute.WOOD, Monster.Attribute.DARK}
        );
        Monster cthulhu = new Monster(
                UUID.randomUUID().toString(), "", "Cthulhu",
                6, 3028, 1827, 253,
                new int[]{Monster.Attribute.FIRE, Monster.Attribute.WATER}
        );
        Monster[] monsters = new Monster[]{kuvia, odin, gilliam,
                null, null, cthulhu};
        team.setMonsters(monsters);

        List<OrbMatch> combos = new ArrayList<>();
        combos.add(new OrbMatch(OrbMatch.ORB_TYPE_DARK, 3, 0));
        combos.add(new OrbMatch(OrbMatch.ORB_TYPE_LIGHT, 3, 0));
        combos.add(new OrbMatch(OrbMatch.ORB_TYPE_FIRE, 3, 0));
        combos.add(new OrbMatch(OrbMatch.ORB_TYPE_WOOD, 3, 0));
        combos.add(new OrbMatch(OrbMatch.ORB_TYPE_WATER, 3, 0));
        combos.add(new OrbMatch(OrbMatch.ORB_TYPE_WOOD, 4, 0));

        List<Damage> damages = new ArrayList<>();
        damages.add(new Damage(17055.0, OrbMatch.ORB_TYPE_FIRE));
        damages.add(new Damage(5695.0, OrbMatch.ORB_TYPE_WATER));
        damages.add(new Damage(14525.0, OrbMatch.ORB_TYPE_WATER));
        damages.add(new Damage(4850.0, OrbMatch.ORB_TYPE_DARK));
        damages.add(new Damage(71170.0, OrbMatch.ORB_TYPE_WOOD));
        damages.add(new Damage(6225.0, OrbMatch.ORB_TYPE_DARK));
        damages.add(null);
        damages.add(null);
        damages.add(null);
        damages.add(null);
        damages.add(new Damage(20555.0, OrbMatch.ORB_TYPE_WATER));
        damages.add(null);
        Damage[] calculated = CalculateDamage.calculatePerMonsterDamage(team, combos,1, 5);
        Log.d("HELEL", calculated.toString());
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
