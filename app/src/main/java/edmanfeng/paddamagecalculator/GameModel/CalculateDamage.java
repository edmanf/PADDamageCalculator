package edmanfeng.paddamagecalculator.GameModel;

import java.util.List;

/**
 * Created by t7500 on 6/20/2017.
 */

public class CalculateDamage {
    public int calculateDamage(Team team, List<OrbMatch> combos) {
        int sum = 0;
        for (Monster monster : team.getMonsters()) {
            int atk = monster.getAtk();
        }

        return sum;
    }
}
