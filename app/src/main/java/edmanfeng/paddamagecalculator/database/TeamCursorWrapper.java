package edmanfeng.paddamagecalculator.database;

import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Team;

import static edmanfeng.paddamagecalculator.database.PadDbSchema.*;

/**
 * Created by t7500 on 4/6/2017.
 */

public class TeamCursorWrapper extends CursorWrapper {
    public TeamCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Team getTeam() {
        String uuidString = getString(getColumnIndex(TeamTable.Cols.UUID));
        String leaderString = getString(getColumnIndex(TeamTable.Cols.LEADER));
        String sub1String = getString(getColumnIndex(TeamTable.Cols.SUB1));
        String sub2String = getString(getColumnIndex(TeamTable.Cols.SUB2));
        String sub3String = getString(getColumnIndex(TeamTable.Cols.SUB3));
        String sub4String = getString(getColumnIndex(TeamTable.Cols.SUB4));
        String friendString = getString(getColumnIndex(TeamTable.Cols.FRIEND_LEADER));


        Team team = new Team();
        team.setLeader(new Monster(leaderString));
        team.setFriend(new Monster(friendString));
        team.setId(UUID.fromString(uuidString));
        Monster[] subs = {new Monster(sub1String),
                new Monster(sub2String),
                new Monster(sub3String),
                new Monster(sub4String)
        };
        team.setSubs(subs);
        return team;
    }
}
