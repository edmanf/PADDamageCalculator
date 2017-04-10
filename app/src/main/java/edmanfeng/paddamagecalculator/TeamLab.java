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
import edmanfeng.paddamagecalculator.database.PadDbSchema;
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
        List<Team> team = new ArrayList<>();
        TeamCursorWrapper cursor = queryTeams(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                team.add(cursor.getTeam());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Log.i(TAG, "Found teams: " + team.size());
        return team;
    }

    public Team getTeam(UUID id) {
        String whereClause = TeamTable.Cols.UUID + " = ?";
        String[] whereArgs= new String[] { id.toString() };
        TeamCursorWrapper cursor = queryTeams(whereClause, whereArgs);

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTeam();
        } finally {
            cursor.close();
        }
    }

    public void updateTeam(Team team) {
        String whereClause = TeamTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[] { team.getId().toString() };
        ContentValues values = getContentValues(team);
        mDatabase.update(TeamTable.NAME, values, whereClause, whereArgs);
    }

    public void deleteTeam(Team team) {
        String whereClause = TeamTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[] { team.getId().toString() };
        mDatabase.delete(TeamTable.NAME, whereClause, whereArgs);
    }

    /**
     * Returns a ContentValues for a team
     * @param team
     * @return
     */
    private static ContentValues getContentValues(Team team) {
        ContentValues values = new ContentValues();
        values.put(TeamTable.Cols.UUID, team.getId().toString());
        values.put(TeamTable.Cols.LEADER, team.getLeader().getName());
        values.put(TeamTable.Cols.SUB1, team.getSub(0).getName());
        values.put(TeamTable.Cols.SUB2, team.getSub(1).getName());
        values.put(TeamTable.Cols.SUB3, team.getSub(2).getName());
        values.put(TeamTable.Cols.SUB4, team.getSub(3).getName());
        values.put(TeamTable.Cols.FRIEND_LEADER, team.getFriend().getName());
        return values;
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
