package edmanfeng.paddamagecalculator.GameModel;

public class SkillEffect {
    private double mHp;
    private double mAtk;
    private double mRcv;
    private double mShield;

    public SkillEffect() {
        this(1, 1, 1, 0);
    }

    public SkillEffect(double hpMultiplier, double atkMultiplier,
                       double rcvMultiplier, double shield) {
        this.mHp = hpMultiplier;
        this.mAtk = atkMultiplier;
        this.mRcv = rcvMultiplier;
        this.mShield = shield;
    }

    public double getHp() {
        return mHp;
    }

    public void setHp(double hp) {
        mHp = hp;
    }

    public double getAtk() {
        return mAtk;
    }

    public void setAtk(double atk) {
        mAtk = atk;
    }

    public double getRcv() {
        return mRcv;
    }

    public void setRcv(double rcv) {
        mRcv = rcv;
    }

    public double getShield() {
        return mShield;
    }

    public void setShield(double shield) {
        mShield = shield;
    }
}
