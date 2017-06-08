package edmanfeng.paddamagecalculator.GameModel;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by t7500 on 2/25/2017.
 */

public class Monster implements SortedListAdapter.ViewModel {
    private static final int MAX_ATTRIBUTES = 2;
    private static final int MAX_TYPES = 3;
    private static final int MAX_AWAKENINGS = 9;
    private static final int MAX_LATENT_SLOTS = 6;

    private String mId;
    private String mOwner;
    private int mHp;
    private int mAtk;
    private int mRcv;
    private int[] mAttributes;
    private int[] mAwakenings;
    private int[] mLatentAwakenings;
    private int[] mTypes;
    private int mRarity;
    private String mName;
    private int mNum;

    public Monster() {
        this(UUID.randomUUID().toString(), Values.LOCAL, "CUSTOM", 0, 0, 0, 0);
    }

    public Monster(String uuid, String owner, String name,
                   int num, int hp, int atk, int rcv) {
        this.mId = uuid;
        mOwner = owner;
        this.mName = name;
        this.mNum = num;
        this.mHp = hp;
        this.mAtk = atk;
        this.mRcv = rcv;
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

    public int getNum() {
        return mNum;
    }

    public void setNum(int num) {
        mNum = num;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getHp() {
        return mHp;
    }

    public void setHp(int hp) {
        mHp = hp;
    }

    public int getAtk() {
        return mAtk;
    }

    public void setAtk(int atk) {
        mAtk = atk;
    }

    public int getRcv() {
        return mRcv;
    }

    public void setRcv(int rcv) {
        mRcv = rcv;
    }

    public int[] getAttributes() {
        return mAttributes;
    }

    public void setAttribute(int slot, int attribute) {
        if (slot < 0 || slot >= MAX_ATTRIBUTES) {
            throw new IndexOutOfBoundsException("attribute slot out of bounds");
        }

        this.mAttributes[slot] = attribute;
    }

    public void setAttributes(int[] attributes) {
        mAttributes = attributes;
    }

    public int[] getAwakenings() {
        return mAwakenings;
    }

    public void setAwakenings(int[] awakenings) {
        mAwakenings = awakenings;
    }

    public void setAwakening(int slot, int awakening) {
        if (slot < 0 || slot > MAX_AWAKENINGS) {
            throw new IndexOutOfBoundsException("awakening slot out of bounds");
        }

        mAwakenings[slot] = awakening;
    }

    public int[] getLatentAwakenings() {
        return mLatentAwakenings;
    }

    public void setLatentAwakenings(int[] latentAwakenings) {
        mLatentAwakenings = latentAwakenings;
    }

    public void setLatentAwakening(int slot, int latent) {
        if (slot < 0 || slot >= MAX_LATENT_SLOTS) {
            throw new IndexOutOfBoundsException("latent slot out of bounds");
        }

        this.mLatentAwakenings[slot] = latent;
    }

    public int[] getTypes() {
        return mTypes;
    }

    public void setTypes(int[] types) {
        mTypes = types;
    }

    public void setType(int slot, int type) {
        if (slot < 0 || slot >= MAX_TYPES) {
            throw new IndexOutOfBoundsException("type slot out of bounds");
        }

        this.mTypes[slot] = type;
    }

    public int getRarity() {
        return mRarity;
    }

    public void setRarity(int rarity) {
        mRarity = rarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = (Monster) o;

        if (mHp != monster.mHp) return false;
        if (mAtk != monster.mAtk) return false;
        if (mRcv != monster.mRcv) return false;
        if (mRarity != monster.mRarity) return false;
        if (mNum != monster.mNum) return false;
        if (mId != null ? !mId.equals(monster.mId) : monster.mId != null) return false;
        if (mOwner != null ? !mOwner.equals(monster.mOwner) : monster.mOwner != null) return false;
        if (!Arrays.equals(mAttributes, monster.mAttributes)) return false;
        if (!Arrays.equals(mAwakenings, monster.mAwakenings)) return false;
        if (!Arrays.equals(mLatentAwakenings, monster.mLatentAwakenings)) return false;
        if (!Arrays.equals(mTypes, monster.mTypes)) return false;
        return mName != null ? mName.equals(monster.mName) : monster.mName == null;

    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mOwner != null ? mOwner.hashCode() : 0);
        result = 31 * result + mHp;
        result = 31 * result + mAtk;
        result = 31 * result + mRcv;
        result = 31 * result + Arrays.hashCode(mAttributes);
        result = 31 * result + Arrays.hashCode(mAwakenings);
        result = 31 * result + Arrays.hashCode(mLatentAwakenings);
        result = 31 * result + Arrays.hashCode(mTypes);
        result = 31 * result + mRarity;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + mNum;
        return result;
    }
}
