package edmanfeng.paddamagecalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by t7500 on 5/25/2017.
 */

public class AccountSyncFragment extends Fragment {
    private static final String TAG = "AccountSyncFragment";
    private static final String DIALOG_ADD_ACCOUNT = "AddAccountDialog";

    private RecyclerView mAccountsRecyclerView;

    public static AccountSyncFragment newInstance() {
        return new AccountSyncFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_accounts, container, false);

        mAccountsRecyclerView = (RecyclerView) v
                .findViewById(R.id.account_recycler_view);
        mAccountsRecyclerView.setAdapter(
                new AccountAdapter(AccountManager.getAccountNames(getActivity())));
        mAccountsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_account_manager, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_account_add:
                AddAccountDialogFragment fragment = AddAccountDialogFragment
                        .newInstance();
                fragment.show(getActivity().getSupportFragmentManager(),
                        DIALOG_ADD_ACCOUNT);
            case R.id.menu_account_remove:
                Log.d(TAG, "Remove accounts");
            case R.id.menu_sync_accounts:
                Log.d(TAG, "Sync accounts");
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private class AccountHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private CheckBox mCheckBox;

        public AccountHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.
                    findViewById(R.id.account_name_text_view);
            mCheckBox = (CheckBox) itemView
                    .findViewById(R.id.account_selected_checkbox);
        }

        public void bindAccount(String name, int position) {
            mNameTextView.setText(name);
        }
    }

    private class AccountAdapter extends RecyclerView.Adapter<AccountHolder> {
        private List<String> mAccounts;

        public AccountAdapter(List<String> accounts) {
            mAccounts = accounts;

            //mAccounts = Arrays.asList("ACC1", "ACC2", "ACC3", "ACC4", "ACC5", "ACC6", "ACC7", "ACC8", "ACC9", "ACC10", "ACC11", "ACC12", "ACC13", "ACC14", "ACC15", "ACC16", "ACC17", "ACC18", "ACC20");
        }

        @Override
        public AccountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.list_item_account, parent, false);
            return new AccountHolder(v);
        }

        @Override
        public void onBindViewHolder(AccountHolder holder, int position) {
            String name = mAccounts.get(position);
            holder.bindAccount(name, position);
        }

        @Override
        public int getItemCount() {
            return mAccounts.size();
        }
    }
}
