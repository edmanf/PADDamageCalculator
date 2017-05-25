package edmanfeng.paddamagecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.database.MonsterBaseHelper;
import edmanfeng.paddamagecalculator.database.MonsterCursorWrapper;
import edmanfeng.paddamagecalculator.database.PadDbSchema;
import edmanfeng.paddamagecalculator.database.PadDbSchema.MonsterTable;

/**
 * Created by t7500 on 4/7/2017.
 */

public class MonsterLab {
    private static final String TAG = "paddamagecalculator";
    private static MonsterLab sMonsterLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private MonsterLab(Context context) {
        mContext = context;
        mDatabase = new MonsterBaseHelper(context)
                .getWritableDatabase();
    }

    public static MonsterLab get(Context context) {
        if (sMonsterLab == null) {
            sMonsterLab = new MonsterLab(context);
        }
        return sMonsterLab;
    }

    public void addMonster(Monster monster) {
        ContentValues contentValues = getContentValues(monster);
        mDatabase.insert(MonsterTable.NAME, null, contentValues);
    }

    public void updateMonster(Monster monster) {
        String whereClause = MonsterTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[] { monster.getId().toString() };
        ContentValues values = getContentValues(monster);
        mDatabase.update(
                MonsterTable.NAME,
                null,
                whereClause,
                whereArgs);
    }

    public void deleteMonster(Monster monster) {
        String whereClause = MonsterTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[] { monster.getId().toString() };
        ContentValues values = getContentValues(monster);
        mDatabase.delete(MonsterTable.Cols.NAME, whereClause, whereArgs);

    }

    public Monster getMonster(UUID uuid) {
        String query = MonsterTable.Cols.UUID + " = ?";
        String[] args = new String[] { uuid.toString() };

        MonsterCursorWrapper cursor = queryMonsters(query, args);
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getMonster();
        } finally {
            cursor.close();
        }
    }

    public ContentValues getContentValues(Monster monster) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MonsterTable.Cols.UUID, monster.getId().toString());
        contentValues.put(MonsterTable.Cols.NAME, monster.getName());
        contentValues.put(MonsterTable.Cols.NUM, monster.getNum());

        return contentValues;
    }

    public MonsterCursorWrapper queryMonsters(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                MonsterTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new MonsterCursorWrapper(cursor);
    }

}
