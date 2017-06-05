package edmanfeng.paddamagecalculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edmanfeng.paddamagecalculator.database.PadDbSchema.TeamTable;

/**
 * Created by t7500 on 4/3/2017.
 */

public class TeamBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "teamBase.db";

    public TeamBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TeamTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                TeamTable.Cols.ID + ", " +
                TeamTable.Cols.OWNER + ", " +
                TeamTable.Cols.LEADER + ", " +
                TeamTable.Cols.SUB1 + ", " +
                TeamTable.Cols.SUB2 + ", " +
                TeamTable.Cols.SUB3 + ", " +
                TeamTable.Cols.SUB4 + ", " +
                TeamTable.Cols.FRIEND_LEADER
                + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
