package edmanfeng.paddamagecalculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edmanfeng.paddamagecalculator.database.PadDbSchema.MonsterTable;

/**
 * Created by t7500 on 4/6/2017.
 */

public class MonsterBaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "monsterBase.db";
    private final static int VERSION = 1;

    public MonsterBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MonsterTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                MonsterTable.Cols.UUID + ", " +
                MonsterTable.Cols.NAME + ", " +
                MonsterTable.Cols.BASE_HP + ", " +
                MonsterTable.Cols.BASE_ATK + ", " +
                MonsterTable.Cols.BASE_RCV +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
