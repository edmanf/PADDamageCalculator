package edmanfeng.paddamagecalculator.GameModel;

import android.net.Uri;

import java.util.List;
import java.util.UUID;

import edmanfeng.paddamagecalculator.PictureUtils;

/**
 * A ViewModel for monsters
 */

public class Monster {
    private static final String TAG = "Monster";
    private static final int MAX_ATTRIBUTES = 2;
    private static final int MAX_TYPES = 3;
    private static final int MAX_AWAKENINGS = 9;
    private static final int MAX_LATENT_SLOTS = 6;

    private String mId;
    private String mOwner;
    private int mHp;
    private int mAtk;
    private int mRcv;
    private String mName;
    private int mNum;
    private int[] mAttributes;
    private Uri mImageUrl;

    public Monster() {
        this(UUID.randomUUID().toString(), Values.LOCAL,
                "CUSTOM", 0, 0, 0, 0, new int[] {Values.Attribute.NONE, Values.Attribute.NONE});
    }

    public Monster(String uuid, String owner, String name,
                   int num, int hp, int atk, int rcv, int[] attributes) {
        mId = uuid;
        mOwner = owner;
        mName = name;
        mNum = num;
        mHp = hp;
        mAtk = atk;
        mRcv = rcv;
        mAttributes = attributes;
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


    /**
     * Sets the monsters attributes. Array must be size 2
     * @param attributes The new attributes of the monster
     */
    /*
    public void setAttributes(int[] attributes) {
        if (attributes == null || attributes.length != 2) {
            throw new IllegalArgumentException("attributes must be an array of size 2");
        }
        this.mAttributes = attributes;
    }*/

    public void setAttributes(List<Long> attributes) {
        mAttributes[0] = attributes.get(0).intValue();
        mAttributes[1] = attributes.get(1).intValue();
    }

    public int getNumberAttributes() {
        int count = 0;
        for (int attr : mAttributes) {
            if (attr != Values.Attribute.NONE) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * Returns the monster's nth (zero-based) attribute.
     * @param n The number attribute
     * @return The nth attribute
     */
    public int getAttribute(int n) {
        return mAttributes[n];
    }

    /**
     * Sets the attribute at n to the given attribute.
     * @param attribute The new attribute
     * @param n The number attribute to change
     */
    public void setAttribute(int attribute, int n) {
        mAttributes[n] = attribute;
    }

    public Uri getImageUrl() {
        return PictureUtils.getMonsterIconUri(mNum);
    }

    public void setImageUrl(String url) {
        mImageUrl = Uri.parse(url);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = (Monster) o;

        if (mHp != monster.mHp) return false;
        if (mAtk != monster.mAtk) return false;
        if (mRcv != monster.mRcv) return false;
        if (mNum != monster.mNum) return false;
        if (mId != null ? !mId.equals(monster.mId) : monster.mId != null) return false;
        if (mOwner != null ? !mOwner.equals(monster.mOwner) : monster.mOwner != null) return false;
        return mName != null ? mName.equals(monster.mName) : monster.mName == null;

    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mOwner != null ? mOwner.hashCode() : 0);
        result = 31 * result + mHp;
        result = 31 * result + mAtk;
        result = 31 * result + mRcv;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + mNum;
        return result;
    }


}
