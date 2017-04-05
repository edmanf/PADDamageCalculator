package edmanfeng.paddamagecalculator.GameModel;

import java.util.UUID;

/**
 * Created by t7500 on 2/25/2017.
 */

public class Monster {
    private static final int MAX_ATTRIBUTES = 2;
    private static final int MAX_TYPES = 3;
    private static final int MAX_AWAKENINGS = 9;
    private static final int MAX_LATENT_SLOTS = 6;



    private UUID mId;

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
        this("Default", 0);
    }

    public Monster(String name, int num) {
        this(name, num, 1, 1, 1);
    }

    public Monster(String name, int num, int hp, int atk, int rcv) {
        this.mId = UUID.randomUUID();
        this.mName = name;
        this.mNum = num;
        this.mHp = hp;
        this.mAtk = atk;
        this.mRcv = rcv;
        this.mAttributes = new int[MAX_ATTRIBUTES];
        this.mAwakenings = new int[MAX_AWAKENINGS];
        this.mLatentAwakenings = new int[MAX_LATENT_SLOTS];
        this.mTypes = new int[MAX_TYPES];
    }

    public UUID getId() {
        return mId;
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

}
