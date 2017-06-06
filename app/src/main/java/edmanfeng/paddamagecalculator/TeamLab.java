package edmanfeng.paddamagecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Team;
import edmanfeng.paddamagecalculator.GameModel.Values;
import edmanfeng.paddamagecalculator.database.PadDbSchema.TeamTable;
import edmanfeng.paddamagecalculator.database.TeamBaseHelper;
import edmanfeng.paddamagecalculator.database.TeamCursorWrapper;

/**
 * Created by t7500 on 4/4/2017.
 */

public class TeamLab {
    private final static String TAG = "paddamagecalculator";

    private static TeamLab sTeamLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private TeamLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TeamBaseHelper(mContext)
                .getWritableDatabase();
    }

    public static TeamLab get(Context context) {
        if (sTeamLab == null) {
            sTeamLab = new TeamLab(context);
        }
        return sTeamLab;
    }

    public void addTeam(Team team) {

        ContentValues values = getContentValues(team);
        mDatabase.insert(TeamTable.NAME, null, values);
    }

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        TeamCursorWrapper cursor = queryTeams(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                teams.add(cursor.getTeam(mContext));
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Log.i(TAG, "Found teams: " + teams.size());
        return teams;
    }

    public Team getTeam(String id) {
        String whereClause = TeamTable.Cols.ID + " = ?";
        String[] whereArgs= new String[] { id.toString() };
        TeamCursorWrapper cursor = queryTeams(whereClause, whereArgs);

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTeam(mContext);
        } finally {
            cursor.close();
        }
    }

    public void updateTeam(Team team) {
        String whereClause = TeamTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] { team.getId().toString() };
        ContentValues values = getContentValues(team);
        mDatabase.update(TeamTable.NAME, values, whereClause, whereArgs);
    }

    public void deleteTeam(Team team) {
        String whereClause = TeamTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] { team.getId().toString() };
        mDatabase.delete(TeamTable.NAME, whereClause, whereArgs);
    }

    /**
     * Returns a ContentValues for a team
     * @param team
     * @return
     */
    private static ContentValues getContentValues(Team team) {
        if (team == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(TeamTable.Cols.ID, team.getId().toString());

        String[] ids = new String[Values.SOLO_TEAM_SIZE];
        for (int i = 0; i < ids.length; i++) {
            Monster monster = team.get(i);
            if (monster == null) {
                ids[i] = Values.NO_MONSTER_ID;
            } else {
                ids[i] = monster.getId();
            }
        }
        contentValues.put(TeamTable.Cols.LEADER, ids[0]);
        contentValues.put(TeamTable.Cols.SUB1, ids[1]);
        contentValues.put(TeamTable.Cols.SUB2, ids[2]);
        contentValues.put(TeamTable.Cols.SUB3, ids[3]);
        contentValues.put(TeamTable.Cols.SUB4, ids[4]);
        contentValues.put(TeamTable.Cols.FRIEND_LEADER, ids[5]);
        return contentValues;
    }

    private TeamCursorWrapper queryTeams(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TeamTable.NAME,
                null, // select all columns
                whereClause, // where clause
                whereArgs, // where args
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new TeamCursorWrapper(cursor);
    }
}
