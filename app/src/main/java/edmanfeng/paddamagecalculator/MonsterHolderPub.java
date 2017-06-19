package edmanfeng.paddamagecalculator;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.databinding.ListItemMonsterSearchBinding;

/**
 * Created by t7500 on 6/19/2017.
 */

public class MonsterHolderPub extends RecyclerView.ViewHolder {
    private static final String TAG = "MonsterHolder";
    private final ListItemMonsterSearchBinding mListItemBinding;


    public MonsterHolderPub(ListItemMonsterSearchBinding binding) {
        super(binding.getRoot());
        mListItemBinding = binding;


    }

    public void bind(Monster monster) {
        mListItemBinding.setMonster(monster);
    }

        /*
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
                    String id;

                    if (mMonster == null) {
                        MonsterSearchFragment fragment =
                                MonsterSearchFragment.newInstance();
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        id = mMonster.getId();
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
            // Set the layout params so that things will fit in one screen
            int width = mTeamRecyclerView.getMeasuredWidth();
            mMonsterImageButton.getLayoutParams().width = width / VIEW_ITEMS_TO_DISPLAY;
            mMonsterImageButton.getLayoutParams().height = width / VIEW_ITEMS_TO_DISPLAY;
        }

        public void bindMonster(Monster monster, int position) {
            mMonster = monster;

            Uri uri = PictureUtils.getMonsterIconUri(mMonster);
            Log.d(TAG, "Try to get icon at: " + uri.toString());
            Glide.with(getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container))
                    .load(uri)
                    .fitCenter()
                    .into(mMonsterImageButton);
        }
    }*/
}
