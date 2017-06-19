package edmanfeng.paddamagecalculator;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.io.File;

import edmanfeng.paddamagecalculator.GameModel.Monster;

/**
 * Created by t7500 on 5/25/2017.
 */

public class PictureUtils {
    private static final String TAG = "PictureUtils";
    private static final String ABSOLUTE_ASSET_PATH_BASE = "android_asset/";
    private static final String MONSTER_ICON_FOLDER = "monster_icons/";

    public static Uri getMonsterIconUri(Monster monster) {

        int num = 0;
        if (monster != null) {
            num = monster.getNum();
        }

        Log.i(TAG, "Fetching img for " + num);
        String path = ABSOLUTE_ASSET_PATH_BASE
                + MONSTER_ICON_FOLDER
                + num
                + ".png";
        Uri uri = Uri.fromFile(new File(path));
        return uri;
    }

    public static Uri getMonsterIconUri(int num) {
        Monster monster = new Monster();
        monster.setNum(num);
        return getMonsterIconUri(monster);
    }

}
