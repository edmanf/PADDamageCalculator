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
        String id = getString(getColumnIndex(MonsterTable.Cols.ID));
        String owner = getString(getColumnIndex(MonsterTable.Cols.OWNER));
        String name = getString(getColumnIndex(MonsterTable.Cols.NAME));
        String numString = getString(getColumnIndex(MonsterTable.Cols.NUM));
        String hpString = getString(getColumnIndex(MonsterTable.Cols.BASE_HP));
        String atkString = getString(getColumnIndex(MonsterTable.Cols.BASE_ATK));
        String rcvString = getString(getColumnIndex(MonsterTable.Cols.BASE_RCV));

        int num = Integer.parseInt(numString);
        int hp = Integer.parseInt(hpString);
        int atk = Integer.parseInt(atkString);
        int rcv = Integer.parseInt(rcvString);

        return new Monster(id, owner, name, num, hp, atk, rcv);
    }
}
