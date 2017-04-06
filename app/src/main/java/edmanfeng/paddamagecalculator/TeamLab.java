package edmanfeng.paddamagecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Team;
import edmanfeng.paddamagecalculator.database.PadDbSchema;
import edmanfeng.paddamagecalculator.database.PadDbSchema.TeamTable;
import edmanfeng.paddamagecalculator.database.TeamBaseHelper;

/**
 * Created by t7500 on 4/4/2017.
 */

public class TeamLab {
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
        return new ArrayList<>();
    }

    public Team getTeam(UUID id) {
        return null;
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
        values.put(TeamTable.Cols.SUB1, team.getSubs()[0].getName());
        values.put(TeamTable.Cols.SUB2, team.getSubs()[1].getName());
        values.put(TeamTable.Cols.SUB3, team.getSubs()[2].getName());
        values.put(TeamTable.Cols.SUB4, team.getSubs()[3].getName());
        values.put(TeamTable.Cols.FRIEND_LEADER, team.getFriend().getName());
        return values;
    }
}
