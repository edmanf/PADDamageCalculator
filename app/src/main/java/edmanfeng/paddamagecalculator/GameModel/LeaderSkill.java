package edmanfeng.paddamagecalculator.GameModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edman on 7/4/2017.
 */

public class LeaderSkill {
    private List<LeaderSkillComponent> mLeaderSkillComponents;

    public LeaderSkill() {
        mLeaderSkillComponents = new ArrayList<LeaderSkillComponent>();
    }

    public void addComponent(LeaderSkillComponent component) {
        mLeaderSkillComponents.add(component);
    }

    public void removeComponent(int index) {
        mLeaderSkillComponents.remove(index);
    }

    public List<LeaderSkillComponent> getLeaderSkillComponents() {
        return mLeaderSkillComponents;
    }

    public int size() {
        return mLeaderSkillComponents.size();
    }

    public double getLeaderSkillMultiplier(Team team, int pos, List<OrbMatch> combos) {
        return 1;
    }
}
