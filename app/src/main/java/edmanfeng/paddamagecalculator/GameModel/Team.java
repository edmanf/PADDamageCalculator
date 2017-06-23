package edmanfeng.paddamagecalculator.GameModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by t7500 on 2/25/2017.
 */

public class Team {
    private static final int DEFAULT_SUBS_SOLO = 4;
    public static final int TEAM_SIZE = 6;

    /**
     * Id of team. Either team name from padherder or ID if local.
     */
    private String mId;
    private String mOwner;
    private Monster[] mMonsters;

    public Team() {
        mId = UUID.randomUUID().toString();
        mMonsters = new Monster[Values.SOLO_TEAM_SIZE];
    }

    public Team(String id, String owner, Monster[] monsters) {
        mId = id;
        mOwner = owner;
        mMonsters = monsters;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public Monster getLeader() {
        return mMonsters[0];
    }

    public void setLeader(Monster leader) {
        mMonsters[0] = leader;
    }

    public Monster getFriend() {
        return mMonsters[Values.SOLO_TEAM_SIZE - 1];
    }

    public void setFriend(Monster friend) {
        mMonsters[Values.SOLO_TEAM_SIZE - 1] = friend;
    }

    public Monster[] getMonsters() {
        return mMonsters;
    }

    public void setMonsters(Monster[] monsters) {
        mMonsters = monsters;
    }

    public Monster get(int slot) {
        return mMonsters[slot];
    }

    public void set(int slot, Monster sub) {
        mMonsters[slot] = sub;
    }

    public List<Monster> asList() {
        List<Monster> team = new ArrayList<>();
        for (int i = 0; i < TEAM_SIZE; i++) {
            team.add(mMonsters[i]);
        }
        return team;
    }

    public boolean isEmpty() {
        for (Monster monster : mMonsters) {
            if (monster != null) {
                return false;
            }
        }
        return true;
    }
}
