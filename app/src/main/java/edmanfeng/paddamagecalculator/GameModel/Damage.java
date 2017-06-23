package edmanfeng.paddamagecalculator.GameModel;

/**
 * Created by t7500 on 6/23/2017.
 */

public class Damage {
    // Will be negative for poison
    private double mDamage;

    // Orb type from Orb match
    private int mOrbType;

    public Damage(double damage, int type) {
        mDamage = damage;
        mOrbType = type;
    }

    public double getDamage() {
        return mDamage;
    }

    public void setDamage(double damage) {
        mDamage = damage;
    }

    public int getOrbType() {
        return mOrbType;
    }

    public void setOrbType(int orbType) {
        mOrbType = orbType;
    }
}
