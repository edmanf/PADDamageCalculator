package edmanfeng.paddamagecalculator;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Values;
import edmanfeng.paddamagecalculator.databinding.FragmentMonsterSearchBinding;
import edmanfeng.paddamagecalculator.databinding.ListItemMonsterSearchBinding;

/**
 * Created by t7500 on 6/8/2017.
 */

public class MonsterSearchFragment extends Fragment {
    public static final String TAG = "MonsterSearchFragment";

    // The team position to add a selected monster to
    private static final String ARG_MONSTER_POS = "monster pos";

    private static final Comparator<Monster> MONSTER_NUM_COMPARATOR =
            new Comparator<Monster>() {
                @Override
                public int compare(Monster o1, Monster o2) {
                    return o1.getNum() - o2.getNum();
                }
            };
    private MenuItem mSearchItem;
    private SearchView mSearchView;
    private FragmentMonsterSearchBinding mBinding;
    private int mPos;

    private MonsterAdapter mAdapter;
    private List<Monster> mMonsters;

    public static MonsterSearchFragment newInstance(int pos) {
        MonsterSearchFragment frag = new MonsterSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MONSTER_POS, pos);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);

        mPos = getArguments().getInt(ARG_MONSTER_POS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // where does binding come from??
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_monster_search, container, false);
        mAdapter = null;

        MonsterLab ml = MonsterLab.get(getActivity());
        mBinding.newMonsterCheckbox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        MonsterLab monsterLab = MonsterLab.get(getActivity());
                        if (isChecked) {
                            mMonsters = monsterLab.getFirebaseMonsters();
                        } else {
                            mMonsters = monsterLab.getMonsters();
                        }
                        if (mAdapter != null) {
                            mAdapter.replaceAll(mMonsters);
                        }
                        Log.d(TAG, "Checked changed, found: " + mMonsters.size());

                    }
                }
        );
        mMonsters = ml.getMonsters();

        mAdapter = new MonsterAdapter(getActivity(), MONSTER_NUM_COMPARATOR,
                mMonsters);
        mBinding.monsterSearchRecyclerView.setLayoutManager(
                new GridLayoutManager(getActivity(), 5));
        mBinding.monsterSearchRecyclerView.setAdapter(mAdapter);

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
        String lowerCaseQuery = query.toLowerCase();

        List<Monster> filteredList = new ArrayList<>();
        for (Monster monster : monsters) {

            // search by name or number
            String text = monster.getName().toLowerCase() + monster.getNum();

            if (text.contains(lowerCaseQuery)) {
                filteredList.add(monster);
            }
        }
        return filteredList;
    }



    private class MonsterHolder extends RecyclerView.ViewHolder {
        private final ListItemMonsterSearchBinding mBinding;


        public MonsterHolder(ListItemMonsterSearchBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.monsterSearchImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    EditMonsterFragment frag;
                    if (mBinding.getMonster().getOwner() == Values.FIREBASE) {
                        frag = EditMonsterFragment
                                .newInstance(mPos, mBinding.getMonster().getNum());
                    } else {
                        frag = EditMonsterFragment
                                .newInstance(mBinding.getMonster().getId(), mPos);
                    }


                    frag.setTargetFragment(getTargetFragment(), getTargetRequestCode());
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, frag)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }

        public void bind(Monster monster) {
            mBinding.setMonster(monster);
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
            getActivity().getSupportFragmentManager();
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
            Log.d(TAG, "Replace all");
        }

        @Override
        public int getItemCount() {
            return mSortedList.size();
        }
    }
}
