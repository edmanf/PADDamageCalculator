package edmanfeng.paddamagecalculator.database;

import android.database.Cursor;
import android.database.CursorWrapper;

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

    public UUID[] getTeamUUIDs() {
        String[] teamUUIDStrings = {getString(getColumnIndex(TeamTable.Cols.UUID)),
                getString(getColumnIndex(TeamTable.Cols.LEADER)),
                getString(getColumnIndex(TeamTable.Cols.SUB1)),
                getString(getColumnIndex(TeamTable.Cols.SUB2)),
                getString(getColumnIndex(TeamTable.Cols.SUB3)),
                getString(getColumnIndex(TeamTable.Cols.SUB4)),
                getString(getColumnIndex(TeamTable.Cols.FRIEND_LEADER))};

        UUID[] teamUUIDs = new UUID[teamUUIDStrings.length];
        for (int i = 0; i < teamUUIDStrings.length; i++) {
            teamUUIDs[i] = UUID.fromString(teamUUIDStrings[i]);
        }
        return teamUUIDs;
    }
}
