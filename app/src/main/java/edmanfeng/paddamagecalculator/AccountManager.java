package edmanfeng.paddamagecalculator;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by t7500 on 5/26/2017.
 */

public class AccountManager {
    private static final String PREF_ACCOUNT_NAMES = "accountNames";
    private static final String DELIMITER = ";";

    /**
     * Returns a list of account names in SharedPreferences
     * @param context Context to get SharedPreferences from
     * @return The list of names of accounts being tracked
     */
    public static List<String> getAccountNames(Context context) {
        String namesString = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_ACCOUNT_NAMES, null);
        return separateNames(namesString);
    }

    /**
     * Adds an account to SharedPreferences
     * @param name Name of the account
     * @param context Context to get the SharedPreferences from
     */
    public static void addAccount(String name, Context context) {
        List<String> names = getAccountNames(context);
        names.add(name);
        String prefString = getPrefString(names);
        updatePreferences(prefString, context);
    }

    /**
     * Removes the account from SharedPreferences
     * @param name Name of the account to remove
     * @param context Context to get the SharedPreferences from
     */
    public static void removeAccount(String name, Context context) {
        List<String> names = getAccountNames(context);
        names.remove(name);
        String prefString = getPrefString(names);
        updatePreferences(prefString, context);
    }

    private static void updatePreferences(String prefString, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_ACCOUNT_NAMES, prefString)
                .apply();
    }

    private static String getPrefString(List<String> names) {
        String str = names.get(0);
        for (int i = 1; i < names.size(); i++) {
            str += DELIMITER + names.get(i);
        }
        return str;
    }

    private static List<String> separateNames(String namesString) {
        return Arrays.asList(namesString.split(DELIMITER));
    }
}
