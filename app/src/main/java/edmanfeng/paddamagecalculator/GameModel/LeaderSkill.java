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

    /*  Returns an array of leader multipliers.
    The array will be formatted as
    [HP_MULTIPLIER, ATK_MULTIPLIER, RCV_MULTIPLIER, SHIELD]
     */
    public double[] getLeaderMultipliers(Team team, Monster monster,
                                      List<OrbMatch> matches) {
        double[] multipliers = new double[4];
        for (LeaderSkillComponent component : mLeaderSkillComponents) {

        }

        return null;
    }
}
