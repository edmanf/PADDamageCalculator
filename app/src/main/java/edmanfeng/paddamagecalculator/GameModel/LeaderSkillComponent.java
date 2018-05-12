package edmanfeng.paddamagecalculator.GameModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderSkillComponent {
    private List<Integer> mAttributes;
    private List<Integer> mTypes;
    private double mHpMultiplier;
    private double mAtkMultiplier;
    private double mRcvMultiplier;
    private double mShield;
    private double mAtkMultiplierScale;
    private double mRcvMultiplierScale;
    private String mAtkScaleType;
    private String mRcvScaleType;

    private double mMinAtkMultiplier;
    private double mMaxAtkMultiplier;
    private double mMinRcvMultiplier;
    private double mMaxRcvMultiplier;

    private int mMinCombo;
    private int mMaxCombo;

    private int mMinConnected;
    private int mMaxConnected;

    private int mRows;
    private int mCols;

    private List<Integer> mOrbTypes;

    // Inner map is orbtypes to amount of necessary orbs
    // out map is sets of orbs to multipliers
    private List<Map<Map<Integer, Integer>, List<Integer>>> mComplexOrbTypes;

    private LeaderSkillComponent() {}

    public class Builder {
        private LeaderSkillComponent mLeaderSkillComponent;

        public Builder(int skillType) {
            mLeaderSkillComponent = new LeaderSkillComponent();
        }

        public LeaderSkillComponent getLeaderSkillComponent() {
            return mLeaderSkillComponent;
        }

        public Builder setAtkMultiplier(double multiplier) {
            mLeaderSkillComponent.mAtkMultiplier = multiplier;
            return this;
        }

        public Builder setHpMultiplier(double multiplier) {
            mLeaderSkillComponent.mHpMultiplier = multiplier;
            return this;
        }

        public Builder setRcvMultiplier(double multiplier) {
            mLeaderSkillComponent.mRcvMultiplier = multiplier;
            return this;
        }

        public Builder setAttributes(List<Integer> attributes) {
            mLeaderSkillComponent.mAttributes = attributes;
            return this;
        }

        public Builder addAttribute(int attribute) {
            if (mLeaderSkillComponent.mAttributes == null) {
                mLeaderSkillComponent.mAttributes = new ArrayList<>();
            }
            mLeaderSkillComponent.mAttributes.add(attribute);
            return this;
        }
    }

}
