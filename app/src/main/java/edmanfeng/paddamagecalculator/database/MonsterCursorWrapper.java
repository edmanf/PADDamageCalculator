package edmanfeng.paddamagecalculator.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;

import static edmanfeng.paddamagecalculator.database.PadDbSchema.*;

/**
 * Created by t7500 on 4/6/2017.
 */

public class MonsterCursorWrapper extends CursorWrapper {
    public MonsterCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Monster getMonster() {
        String uuidString = getString(getColumnIndex(MonsterTable.Cols.UUID));
        String nameString = getString(getColumnIndex(MonsterTable.Cols.NAME));
        String numString = getString(getColumnIndex(MonsterTable.Cols.NUM));

        int num = Integer.parseInt(numString);

        return new Monster(UUID.fromString(uuidString),
                nameString,
                num);
    }
}
