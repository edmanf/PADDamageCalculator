package edmanfeng.paddamagecalculator;

import android.net.Uri;
import android.util.DisplayMetrics;

import java.io.File;

import edmanfeng.paddamagecalculator.GameModel.Monster;

/**
 * Created by t7500 on 5/25/2017.
 */

public class PictureUtils {
    private static final String ABSOLUTE_ASSET_PATH_BASE = "android_asset/";
    private static final String MONSTER_ICON_FOLDER = "monster_icons/";

    public static Uri getMonsterIconUri(Monster monster) {
        int num = 0;
        if (monster != null) {
            num = monster.getNum();
        }
        String path = ABSOLUTE_ASSET_PATH_BASE
                + MONSTER_ICON_FOLDER
                + num
                + ".png";
        Uri uri = Uri.fromFile(new File(path));
        return uri;
    }


}
