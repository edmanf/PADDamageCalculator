package edmanfeng.paddamagecalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by t7500 on 5/27/2017.
 */

public class AddAccountDialogFragment extends DialogFragment {
    private static final String TAG = "AddAccountDialog";

    private EditText mEditText;
    private Button mPositiveButton;
    private Button mNegativeButton;


    public static AddAccountDialogFragment newInstance() {
        return new AddAccountDialogFragment();
    }
    /*
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_add_account, null))
                .setPositiveButton(R.string.add_account_message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Add account to shared preferences
                        Log.i(TAG, "Adding account");

                        AccountManager.addAccount();
                    }
                })

                .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel
                        Log.i(TAG, "Cancel add account");
                    }
                });

        return builder.create();
    }*/


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(true);

        View v = inflater.inflate(R.layout.dialog_add_account, container, false);
        mEditText = (EditText) v
                .findViewById(R.id.account_name_edit_text);
        mPositiveButton = (Button) v
                .findViewById(R.id.add_account_button);
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountManager.addAccount(mEditText.getText().toString(), getActivity());
                getDialog().dismiss();
            }
        });

        mNegativeButton = (Button) v
                .findViewById(R.id.add_account_cancel_button);
        mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return v;
    }
}
