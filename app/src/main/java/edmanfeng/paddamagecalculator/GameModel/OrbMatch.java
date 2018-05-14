package edmanfeng.paddamagecalculator.GameModel;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.Map;

import static edmanfeng.paddamagecalculator.GameModel.Values.OrbType;
import static edmanfeng.paddamagecalculator.GameModel.Values.OrbShape;

/**
 * Represents a single match of orbs
 */

public class OrbMatch {


    // sets the color of the combo for data-binding
    private final Map<String, String> mColors;

    private int mOrbType;
    private int mCount;
    private int mShape;
    private int mEnhanced;

    public OrbMatch(int orbType, int count, int enhanced, int shape) {
        mOrbType = orbType;
        mCount = count;
        mEnhanced = enhanced;
        mShape = shape;

        mColors = new HashMap<>();
        mColors.put(OrbType.ORB_TYPES[OrbType.FIRE], "red");
        mColors.put(OrbType.ORB_TYPES[OrbType.WATER], "blue");
        mColors.put(OrbType.ORB_TYPES[OrbType.WOOD], "green");
        mColors.put(OrbType.ORB_TYPES[OrbType.DARK], "purple");
        mColors.put(OrbType.ORB_TYPES[OrbType.LIGHT], "yellow");
        mColors.put(OrbType.ORB_TYPES[OrbType.HEAL], "pink");
        mColors.put(OrbType.ORB_TYPES[OrbType.JAMMER], "darkgray");
        mColors.put(OrbType.ORB_TYPES[OrbType.POISON], "darkgray");

    }

    public OrbMatch() {
        this(OrbType.FIRE, 0, 0, 0);
    }


    public OrbMatch(OrbMatch orig) {
        this(orig.getOrbType(), orig.getCount(), orig.getEnhanced(), orig.getShape());
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

    public Drawable getColor() {
        return new ColorDrawable(Color.parseColor(mColors.get(OrbType.ORB_TYPES[getOrbType()])));
    }

    /**
     * Format: {type,number of orbs,number of enhanced orbs,shape}
     * @return
     */
    @Override
    public String toString() {
        return "[" + Values.OrbType.ORB_TYPES[mOrbType] + ", " + mCount + " orbs, " +
                mEnhanced + " enhanced, " + OrbShape.SHAPES[mShape] + "]";
    }


}
