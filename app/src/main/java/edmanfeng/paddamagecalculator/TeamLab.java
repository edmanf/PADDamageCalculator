package edmanfeng.paddamagecalculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Team;
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

    }

    public List<Team> getTeams() {
        return new ArrayList<>();
    }

    public Team getTeam(UUID id) {
        return null;
    }
}
