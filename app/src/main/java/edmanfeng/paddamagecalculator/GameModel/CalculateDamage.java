package edmanfeng.paddamagecalculator.GameModel;

import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by t7500 on 6/20/2017.
 */

public class CalculateDamage {
    /**
     * The order will be in (main, sub) for each monster in team
     * @param team
     * @param combos
     * @return
     */
    public List<Damage> calculatePerMonsterDamage(Team team, List<OrbMatch> combos) {
        // G1 round up: attri multi, enhanced in combo, active
        // G2 round near: TPA
        // G3 round near: Row, # combos
        // G4 round near: leader
        // http://puzzleanddragonsforum.com/threads/mechanics-comprehensive-guide-to-game-mechanics.50604/
        List<Damage> damageList = new ArrayList<>();
        SparseArray<OrbMatch> colorComboArray = new SparseArray<>();

        // Stores sum of bOrb for each type
        SparseArray<Double> bOrbArray = new SparseArray<>();

        // bOrb per combo is 1 + 0.25(matched orbs - 3)
        for (OrbMatch match : combos) {
            double bOrb = bOrbArray.get(match.getOrbType(), 0.0);
            bOrbArray.put(match.getOrbType(), bOrb + 1 + 0.25 * (match.getCount() - 3));
        }

        double bCombo = 1 + 0.25 * (combos.size() - 1);

        for (int i = 0; i < Team.TEAM_SIZE; i++) {
            Monster monster = team.get(i);
            int atk = monster.getAtk();
            int mainAttr = monster.getAttribute(0);
            double damage = atk * bOrbArray.get(mainAttr) * bCombo;
            damageList.add(new Damage(damage, mainAttr));

            int subAttr = monster.getAttribute(1);
            double subMultiplier = subAttr == mainAttr ? 0.1 : 1.3 / 3;
            damage = atk * bOrbArray.get(subAttr) * bCombo * subMultiplier;
            damageList.add(new Damage(damage, subAttr));
        }
        return damageList;
    }
}
