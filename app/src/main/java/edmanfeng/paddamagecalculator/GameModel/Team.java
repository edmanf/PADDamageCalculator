package edmanfeng.paddamagecalculator.GameModel;

import java.util.UUID;

/**
 * Created by t7500 on 2/25/2017.
 */

public class Team {
    private static final int DEFAULT_SUBS_SOLO = 4;

    private UUID mId;
    private Monster mLeader;
    private Monster mFriend;
    private Monster[] mSubs;
    private int mBadge;

    public Team() {
        mId = UUID.randomUUID();
        mLeader = null;
        mFriend = null;
        mSubs = new Monster[DEFAULT_SUBS_SOLO];
        mBadge = Values.Badge.NONE;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public Monster getLeader() {
        return mLeader;
    }

    public void setLeader(Monster leader) {
        mLeader = leader;
    }

    public Monster getFriend() {
        return mFriend;
    }

    public void setFriend(Monster friend) {
        mFriend = friend;
    }

    public Monster[] getSubs() {
        return mSubs;
    }

    public void setSubs(Monster[] subs) {
        mSubs = subs;
    }

    public Monster getSub(int slot) {
        return mSubs[slot];
    }

    public void setSub(int slot, Monster sub) {
        mSubs[slot] = sub;
    }

    public int getBadge() {
        return mBadge;
    }

    public void setBadge(int badge) {
        mBadge = badge;
    }
}
