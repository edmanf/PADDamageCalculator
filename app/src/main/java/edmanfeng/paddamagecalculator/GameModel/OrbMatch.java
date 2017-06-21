package edmanfeng.paddamagecalculator.GameModel;

/**
 * Created by t7500 on 4/3/2017.
 */

public class OrbMatch {
    public static final int ORB_TYPE_FIRE = 0;
    public static final int ORB_TYPE_WATER = 1;
    public static final int ORB_TYPE_WOOD = 2;
    public static final int ORB_TYPE_LIGHT = 3;
    public static final int ORB_TYPE_DARK = 4;
    public static final int ORB_TYPE_HEAL = 5;
    public static final int ORB_TYPE_JAMMER = 6;
    public static final int ORB_TYPE_POISON = 7;

    private int mOrbType;
    private int mCount;
    private int mShape;
    private int mEnhanced;

    public OrbMatch(int orbType, int count, int enhanced) {
        mOrbType = orbType;
        mCount = count;
        mEnhanced = enhanced;
    }

    public int getOrbType() {
        return mOrbType;
    }

    public void setOrbType(int orbType) {
        mOrbType = orbType;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public int getShape() {
        return mShape;
    }

    public void setShape(int shape) {
        mShape = shape;
    }

    public int getEnhanced() {
        return mEnhanced;
    }

    public void setEnhanced(int enhanced) {
        mEnhanced = enhanced;
    }



}
