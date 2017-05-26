package edmanfeng.paddamagecalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by t7500 on 5/25/2017.
 */

public class AccountSyncFragment extends Fragment {
    private static final String TAG = "AccountSyncFragment";

    private RecyclerView mAccountsRecyclerView;

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

    private class AccountHolder extends RecyclerView.ViewHolder {
        public AccountHolder(View itemView) {
            super(itemView);
        }

        public void bindAccount(String name, int position) {

        }
    }

    private class AccountAdapter extends RecyclerView.Adapter<AccountHolder> {
        private List<String> mAccounts;

        public AccountAdapter(List<String> accounts) {
            mAccounts = accounts;
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
