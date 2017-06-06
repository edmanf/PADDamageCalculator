package edmanfeng.paddamagecalculator.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Team;
import edmanfeng.paddamagecalculator.GameModel.Values;
import edmanfeng.paddamagecalculator.MonsterLab;

import static edmanfeng.paddamagecalculator.database.PadDbSchema.*;

/**
 * Created by t7500 on 4/6/2017.
 */

public class TeamCursorWrapper extends CursorWrapper {
    public TeamCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Team getTeam(Context context) {
        MonsterLab ml = MonsterLab.get(context);
        String owner = getString(getColumnIndex(TeamTable.Cols.OWNER));
        String id = getString(getColumnIndex(TeamTable.Cols.ID));
        String[] teamIds = {
                getString(getColumnIndex(TeamTable.Cols.LEADER)),
                getString(getColumnIndex(TeamTable.Cols.SUB1)),
                getString(getColumnIndex(TeamTable.Cols.SUB2)),
                getString(getColumnIndex(TeamTable.Cols.SUB3)),
                getString(getColumnIndex(TeamTable.Cols.SUB4)),
                getString(getColumnIndex(TeamTable.Cols.FRIEND_LEADER))};

        Monster[] monsters = new Monster[Values.SOLO_TEAM_SIZE];
        for (int i = 0; i < Values.SOLO_TEAM_SIZE; i++) {
            monsters[i] = ml.getMonster(teamIds[i]);
        }
        return new Team(id, owner, monsters);
    }
}
