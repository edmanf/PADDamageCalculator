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
        String hpString = getString(getColumnIndex(MonsterTable.Cols.BASE_HP));
        String atkString = getString(getColumnIndex(MonsterTable.Cols.BASE_ATK));
        String rcvString = getString(getColumnIndex(MonsterTable.Cols.BASE_RCV));

        return new Monster(UUID.fromString(uuidString),
                nameString,
                Integer.getInteger(hpString),
                Integer.getInteger(atkString),
                Integer.getInteger(rcvString));
    }
}
