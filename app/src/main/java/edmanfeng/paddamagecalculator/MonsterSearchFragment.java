package edmanfeng.paddamagecalculator;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.databinding.FragmentMonsterSearchBinding;
import edmanfeng.paddamagecalculator.databinding.ListItemMonsterSearchBinding;

/**
 * Created by t7500 on 6/8/2017.
 */

public class MonsterSearchFragment extends Fragment {
    private static final String TAG = "MonsterSearchFragment";
    private static final Comparator<Monster> MONSTER_NUM_COMPARATOR =
            new Comparator<Monster>() {
                @Override
                public int compare(Monster o1, Monster o2) {
                    return o1.getNum() - o2.getNum();
                }
            };
    private MenuItem mSearchItem;
    private SearchView mSearchView;
    private CheckBox mCheckBox;
    private FragmentMonsterSearchBinding mBinding;

    private final int RECYCLER_VIEW_COLUMNS = 5;

    private MonsterAdapter mAdapter;
    private List<Monster> mMonsters;
    private int mRecyclerViewWidth;

    public static MonsterSearchFragment newInstance() {
        return new MonsterSearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // where does binding come from??
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_monster_search, container, false);


        MonsterLab ml = MonsterLab.get(getActivity());
        mMonsters = ml.getMonsters();

        mAdapter = new MonsterAdapter(getActivity(), MONSTER_NUM_COMPARATOR,
                mMonsters);
        mBinding.monsterSearchRecyclerView.setLayoutManager(
                new GridLayoutManager(getActivity(), RECYCLER_VIEW_COLUMNS));
        mBinding.monsterSearchRecyclerView.setAdapter(mAdapter);

        mCheckBox = mBinding.newMonsterCheckbox;
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MonsterLab ml = MonsterLab.get(getActivity());
                if (isChecked) {
                    // Want to make a new monster, so list default ones
                    mMonsters = ml.getDefaultMonsters().subList(0, 10);
                    Log.i(TAG, "Switching to FB, found: " + mMonsters.size());
                } else {
                    // Show locally saved monsters
                    mMonsters = ml.getMonsters();
                    Log.i(TAG, "Switching to ML, found: " + mMonsters.size());
                }
                mAdapter.replaceAll(mMonsters);
            }
        });
        mRecyclerViewWidth = mBinding.monsterSearchRecyclerView.getMeasuredWidth();
        return mBinding.getRoot();
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
                List<Monster> filteredMonsters = filter(mMonsters, query);
                mAdapter.replaceAll(filteredMonsters);

                // scroll back to top
                mBinding.monsterSearchRecyclerView.scrollToPosition(0);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
    }

    private List<Monster> filter(List<Monster> monsters, String query) {
        boolean cleared = query == null || query.isEmpty();
        Log.d(TAG, "Query isEmpty(): " + query.isEmpty());
        String lowerCaseQuery = query.toLowerCase();

        List<Monster> filteredList = new ArrayList<>();
        for (Monster monster : monsters) {

            // search by name or number
            String text = monster.getName().toLowerCase() + monster.getNum();

            if (text.contains(lowerCaseQuery) || cleared) {
                filteredList.add(monster);
            }
        }
        return filteredList;
    }

    private class MonsterHolder extends RecyclerView.ViewHolder {
        private final ListItemMonsterSearchBinding mListItemBinding;


        public MonsterHolder(ListItemMonsterSearchBinding binding) {
            super(binding.getRoot());
            mListItemBinding = binding;
            TextView view = mListItemBinding.monsterTextView;
            view.getLayoutParams().width = mRecyclerViewWidth / RECYCLER_VIEW_COLUMNS;
            view.getLayoutParams().height = mRecyclerViewWidth / RECYCLER_VIEW_COLUMNS;
        }

        public void bind(Monster monster) {
            mListItemBinding.setMonster(monster);
        }
    }

    private class MonsterAdapter extends RecyclerView.Adapter<MonsterHolder> {

        private final LayoutInflater mInflater;
        private final Comparator<Monster> mComparator;
        private final SortedList<Monster> mSortedList =
                new SortedList<Monster>(Monster.class, new SortedList.Callback<Monster>() {
            @Override
            public int compare(Monster o1, Monster o2) {
                return mComparator.compare(o1, o2);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemChanged(position, count);
            }


            //TODO: Decide the visual rep, then change this method if needed
            /**
             * Used with RecyclerView, so I should return wether or not the visual
             * reprsentation is the same or not (not decided yet).
             * @param oldItem
             * @param newItem
             * @return
             */
            @Override
            public boolean areContentsTheSame(Monster oldItem, Monster newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Monster item1, Monster item2) {
                return item1.equals(item2);
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });


        public MonsterAdapter(Context context, Comparator<Monster> comparator,
                              List<Monster> monsters) {
            mInflater = LayoutInflater.from(context);
            mComparator = comparator;
            mSortedList.addAll(monsters);
        }

        @Override
        public MonsterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final ListItemMonsterSearchBinding binding
                    = ListItemMonsterSearchBinding.inflate(mInflater, parent, false);
            return new MonsterHolder(binding);
        }

        @Override
        public void onBindViewHolder(MonsterHolder holder, int position) {
            Monster monster = mSortedList.get(position);
            holder.bind(monster);
        }

        public void add(Monster monster) {
            mSortedList.add(monster);
        }

        public void remove(Monster monster) {
            mSortedList.remove(monster);
        }

        public void add(List<Monster> monsters) {
            mSortedList.addAll(monsters);
        }

        public void remove(List<Monster> monsters) {
            // notify all changes at once with batch updates
            mSortedList.beginBatchedUpdates();
            for(Monster monster : monsters) {
                mSortedList.remove(monster);
            }
            mSortedList.endBatchedUpdates();
        }

        public void replaceAll(List<Monster> monsters) {
            mSortedList.beginBatchedUpdates();
            for (int i = mSortedList.size() - 1; i >= 0; i--) {
                Monster sortedMonster = mSortedList.get(i);
                if (!monsters.contains(sortedMonster)) {
                    mSortedList.remove(sortedMonster);
                }
            }
            mSortedList.addAll(monsters);
            mSortedList.endBatchedUpdates();
        }

        @Override
        public int getItemCount() {
            return mSortedList.size();
        }
    }


}
