package edmanfeng.paddamagecalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by t7500 on 2/27/2017.
 */

public class TeamListFragment extends Fragment {

    private RecyclerView mTeamRecyclerView;

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

        mTeamRecyclerView = (RecyclerView) view
                .findViewById(R.id.team_recycler_view);
        return view;
    }

    private class TeamHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private ImageView mMonsterImageView;
        private TextView mExampleTextView;

        public TeamHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            /*
            mMonsterImageView = (ImageView) itemView
                    .findViewById(R.id.list_monster_image);*/

        }

        @Override
        public void onClick(View v) {

        }
    }

    private class TeamAdapter extends RecyclerView.Adapter<TeamHolder> {

        @Override
        public TeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new TeamHolder(view);
        }

        @Override
        public void onBindViewHolder(TeamHolder holder, int position) {

            //holder.mT
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
