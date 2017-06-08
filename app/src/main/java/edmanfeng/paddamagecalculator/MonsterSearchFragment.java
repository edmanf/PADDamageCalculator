package edmanfeng.paddamagecalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.databinding.ListItemMonsterSearchBinding;

/**
 * Created by t7500 on 6/8/2017.
 */

public class MonsterSearchFragment extends Fragment {
    private MenuItem mSearchItem;
    private SearchView mSearchView;

    public MonsterSearchFragment newInstance() {
        return new MonsterSearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_monster_search, menu);

        mSearchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) mSearchItem.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
    }

    private class MonsterHolder extends RecyclerView.ViewHolder {
        private final ListItemMonsterSearchBinding mBinding;


        public MonsterHolder(ListItemMonsterSearchBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Monster monster) {
            mBinding.setModel(monster);
        }
    }

    private class MonsterAdapter extends RecyclerView.Adapter<MonsterHolder> {
        private final SortedList<Monster> mSortedList =
                new SortedList<Monster>(Monster.class, new SortedList.Callback<Monster>() {
            @Override
            public int compare(Monster o1, Monster o2) {
                return 0;
            }

            @Override
            public void onChanged(int position, int count) {

            }

            @Override
            public boolean areContentsTheSame(Monster oldItem, Monster newItem) {
                return false;
            }

            @Override
            public boolean areItemsTheSame(Monster item1, Monster item2) {
                return false;
            }

            @Override
            public void onInserted(int position, int count) {

            }

            @Override
            public void onRemoved(int position, int count) {

            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {

            }
        });

        @Override
        public MonsterHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup container,
                                                Bundle savedInstanceState) {
            ListItemMonsterSearchBinding binding = ListItemMonsterSearchBinding.inflate(inflater, R.layout.)
        }



        @Override
        public void onBindViewHolder(MonsterHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mSortedList.size();
        }
    }


}
