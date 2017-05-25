package edmanfeng.paddamagecalculator.GameModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by t7500 on 2/25/2017.
 */

public class Team {
    private static final int DEFAULT_SUBS_SOLO = 4;
    private static final int DEFAULT_TEAM_SIZE = 6;

    private UUID mId;
    private Monster[] mMonsters;
    private int mBadge;

    public Team() {
        mId = UUID.randomUUID();
        mMonsters = new Monster[DEFAULT_TEAM_SIZE];
        mBadge = Values.Badge.NONE;
    }

    public Team(UUID id, Monster[] monsters) {
        mId = id;
        mMonsters = monsters;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public Monster getLeader() {
        return mMonsters[0];
    }

    public void setLeader(Monster leader) {
        mMonsters[0] = leader;
    }

    public Monster getFriend() {
        return mMonsters[5];
    }

    public void setFriend(Monster friend) {
        mMonsters[5] = friend;
    }

    public Monster[] getMonsters() {
        return mMonsters;
    }

    public void setMonsters(Monster[] monsters) {
        mMonsters = monsters;
    }

    public Monster getSub(int slot) {
        return mMonsters[slot];
    }

    public void setSub(int slot, Monster sub) {
        mMonsters[slot] = sub;
    }

    public int getBadge() {
        return mBadge;
    }

    public void setBadge(int badge) {
        mBadge = badge;
    }

    public List<Monster> asList() {
        List<Monster> team = new ArrayList<>();
        for (int i = 0; i < DEFAULT_TEAM_SIZE; i++) {
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
