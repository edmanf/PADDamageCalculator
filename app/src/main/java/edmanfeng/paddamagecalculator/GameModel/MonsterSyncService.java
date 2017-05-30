package edmanfeng.paddamagecalculator.GameModel;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by t7500 on 5/30/2017.
 */

public class MonsterSyncService extends IntentService {
    private static final String TAG = "MonsterSyncService";

    public static Intent newIntent(Context context) {
        return new Intent(context, MonsterSyncService.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    public MonsterSyncService(String name) {
        super(TAG); // sets name for debugging
    }
}
