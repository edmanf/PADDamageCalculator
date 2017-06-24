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
    public static Damage[] calculatePerMonsterDamage(Team team, List<OrbMatch> combos) {
        // G1 round up: attri multi, enhanced in combo, active
        // G2 round near: TPA
        // G3 round near: Row, # combos
        // G4 round near: leader
        // http://puzzleanddragonsforum.com/threads/mechanics-comprehensive-guide-to-game-mechanics.50604/
        List<Damage> damageList = new ArrayList<>(12);
        Damage[] damageArray = new Damage[12];
        SparseArray<List<OrbMatch>> colorCombos = new SparseArray<>();
        double activeMultiplier = 1;
        double leaderMultiplier = 1;
        double friendMultiplier = 1;
        friendMultiplier = 5; // TEST DEBUG

        // Stores sum of bOrb for each type
        SparseArray<Double> bOrbArray = new SparseArray<>();

        /* Without doing this: each monster goes through each combo in list (m * n) always
           With: worst case is go through combos once, then (if all combos are same type)
                 each monster goes through each combo in list (m * n + n)
                 avg case is k combo for each attr, so n + m * k

         */
        for (OrbMatch combo : combos) {
            List<OrbMatch> list = colorCombos.get(combo.getOrbType(), new ArrayList<OrbMatch>());
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(combo);
            colorCombos.put(combo.getOrbType(), list);
        }

        double bCombo = 1 + 0.25 * (combos.size() - 1);
        for (int i = 0; i < Team.TEAM_SIZE; i++) {
            Monster monster = team.get(i);
            if (monster == null) {
                continue;
            }
            int mainAttr = monster.getAttribute(0);
            int subAttr = monster.getAttribute(1);
            double colorMultiplier = 1.0;
            for (OrbMatch combo : colorCombos.get(mainAttr, new ArrayList<OrbMatch>())) {
                if (combo == null) {
                    continue;
                }
                double nOrb = 1 + 0.25 * (combo.getCount() - 3);
                double pOrb = (1 + 0.06 * combo.getEnhanced()) * (1 + team.getEnhanced(mainAttr));
                double total = Math.ceil(monster.getAtk() * colorMultiplier * nOrb * pOrb * activeMultiplier);

                // G2: tpa multipliers
                if (combo.getCount() == 4) {
                    total = Math.round(total * 1);
                }

                // G3: bCombo, bRow
                total = Math.round(total * bCombo); //team.getRowEnhance(mainAttr));

                // G4: Leader
                total = Math.round(total * leaderMultiplier * friendMultiplier);
                //damageList.add(i * 2, new Damage(total, mainAttr));
                damageArray[i * 2] = new Damage(total, mainAttr);
            }
            colorMultiplier = mainAttr == subAttr ? 0.1 : 1.0 / 3;
            for (OrbMatch combo : colorCombos.get(subAttr)) {
                double nOrb = 1 + 0.25 * (combo.getCount() - 3);
                double pOrb = (1 + 0.06 * combo.getEnhanced()) * (1 + team.getEnhanced(subAttr));
                double total = Math.ceil(monster.getAtk() * colorMultiplier * nOrb * pOrb * activeMultiplier);

                // G2: tpa multipliers
                if (combo.getCount() == 4) {
                    total = Math.round(total * 1);
                }

                // G3: bCombo, bRow
                total = Math.round(total * bCombo);// * team.getRowEnhance(subAttr));

                // G4: Leader
                total = Math.round(total * leaderMultiplier * friendMultiplier);
                //damageList.add(i * 2 + 1, new Damage(total, subAttr));
                damageArray[i * 2 + 1] = new Damage(total, subAttr);
            }
        }

        // G1 = roundup(attrimulti * pOrbs * nOrb * active)
        // nOrb = 1 + 0.25 * (match.count() - 3)
        // pOrbs = (1 + 0.06 * OE_matched) * (1 + 0.05 * team.OEA)
        // bOrb per combo is 1 + 0.25(matched orbs - 3)
        return damageArray;
    }
}
