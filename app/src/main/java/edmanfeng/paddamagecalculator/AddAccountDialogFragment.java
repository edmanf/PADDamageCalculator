package edmanfeng.paddamagecalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
                String name = mEditText.getText().toString();
                boolean added = AccountManager.addAccount(name, getActivity());
                Log.d(TAG, "Result of adding " + name + ": " + added);
                if (added) {
                    sendResult(Activity.RESULT_OK);
                } else {
                    sendResult(Activity.RESULT_CANCELED);
                }

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


    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


}
