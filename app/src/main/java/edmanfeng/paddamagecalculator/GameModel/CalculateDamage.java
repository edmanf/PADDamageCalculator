package edmanfeng.paddamagecalculator.GameModel;

import android.support.v4.util.SparseArrayCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edmanfeng.paddamagecalculator.GameModel.Values.Awakening;
import edmanfeng.paddamagecalculator.GameModel.Values.AwakeningValue;
import edmanfeng.paddamagecalculator.GameModel.Values.OrbShape;
/**
 * Created by t7500 on 6/20/2017.
 */

public class CalculateDamage {

    // TODO: Get rid of leadermulti and friendleadermlti
    /**
     * Array of damage given by the team and combos made.
     * The ith monster in the team will have its main attribute damage in the (i * 2) position
     * and its sub attribute damage in the ((i * 2) + 1) positon.
     * @param team The team to calculate damage for
     * @param combos A list of OrbMatches that make up the combos
     * @return Returns an array of Damage that the team will deal with the given combos
     */
    public static Damage[] calculatePerMonsterDamage(Team team, List<OrbMatch> combos,
                                                     double leaderMulti, double friendLeaderMulti) {
        // G1 round up: attri multi, enhanced in combo, active
        // G2 round near: TPA
        // G3 round near: Row, # combos
        // G4 round near: leader
        // http://puzzleanddragonsforum.com/threads/mechanics-comprehensive-guide-to-game-mechanics.50604/

        // 6 monsters, 2 attributes
        Damage[] damageArray = new Damage[12];

        // replace with HashMap<Integer, List<OrbMatch> for non-Android
        SparseArrayCompat<List<OrbMatch>> colorCombos = new SparseArrayCompat<>();
        double activeMultiplier = 1;
        double leaderMultiplier = leaderMulti;
        double friendMultiplier = friendLeaderMulti;

        // Stores sum of bOrb for each type
        //SparseArrayCompat<Double> bOrbArray = new SparseArray<>();

        SparseArrayCompat<Integer> rowCounts = new SparseArrayCompat<>();

        /* Without doing this: each monster goes through each combo in list (m * n) always
           With: worst case is go through combos once, then (if all combos are same type)
                 each monster goes through each combo in list (m * n + n)
                 avg case is k combo for each attr, so n + m * k
         */
        for (OrbMatch combo : combos) {
            int orbType = combo.getOrbType();
            List<OrbMatch> list = colorCombos.get(orbType, new ArrayList<OrbMatch>());
            list.add(combo);
            colorCombos.put(orbType, list);

            if (combo.getShape() == OrbShape.ROW) {
                int count = rowCounts.get(orbType, 0);
                rowCounts.put(orbType, count + 1);
            }
        }

        double bCombo = 1 + 0.25 * (combos.size() - 1);


        for (int i = 0; i < Team.TEAM_SIZE; i++) {
            Monster monster = team.get(i);
            if (monster == null) {
                continue;
            }
            int mainAttr = monster.getAttribute(0);
            int subAttr = monster.getAttribute(1);
            boolean sameAttr = mainAttr == subAttr;
            double colorMultiplier = 1.0;

            for (OrbMatch combo : colorCombos.get(mainAttr, new ArrayList<OrbMatch>())) {
                if (combo == null) {
                    continue;
                }
                int teamEnhanced = team.getAwakening(
                        Awakening.ENHANCED_ORBS[mainAttr]);
                int enhancedOrbs = combo.getEnhanced();
                double nOrb = 1 + 0.25 * (combo.getCount() - 3);
                double pOrb = (1 + AwakeningValue.ORB_ENHANCE_MATCHED * enhancedOrbs) *
                        (1 + AwakeningValue.ORB_ENHANCE_BASE * teamEnhanced);
                double total = Math.ceil(monster.getAtk() * colorMultiplier *
                        nOrb * pOrb * activeMultiplier);

                // G2: tpa multipliers
                double bTPA = 1;
                if (combo.getShape() == OrbShape.TPA) {
                    bTPA = Math.max(1, AwakeningValue.TWO_PRONG *
                            monster.getNumAwakening(Awakening.TWO_PRONG));
                }

                double bDVP = 1;
                if (combo.getShape() == OrbShape.SQUARE_3X3 &&
                        monster.getNumAwakening(Awakening.DAMAGE_VOID_PIERCER) > 0) {
                    bDVP = AwakeningValue.VOID_DEFENSE_PIERCER;
                }

                double bLIA = 1;
                if (combo.getShape() == OrbShape.L_5) {
                    bLIA = Math.max(1, AwakeningValue.L_INCREASED_ATTACK *
                            monster.getNumAwakening(Awakening.L_INCREASED_ATTACK));
                }

                total = Math.round(total * bTPA * bDVP * bLIA);

                // G3: bCombo, bRow, b7c, bSBA, bLTH, bGTH
                int rowAwakenings = team.getAwakening(Awakening.ROWS[mainAttr]);
                int rowsMatched = rowCounts.get(mainAttr, 0);
                double bRow = 1 + (0.1 * rowAwakenings * rowsMatched);
                total = Math.round(total * bCombo * bRow);

                // G4: Leader
                total = Math.round(total * leaderMultiplier * friendMultiplier);
                if (damageArray[i * 2] == null) {
                    damageArray[i * 2] = new Damage(0, mainAttr);
                }
                damageArray[i * 2].addDamage(total);
            }

            /*
            colorMultiplier = mainAttr == subAttr ? 0.1 : 1.0 / 3;
            List<OrbMatch> subCombos = colorCombos.get(subAttr, new ArrayList<OrbMatch>());
            for (OrbMatch combo : subCombos) {
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
            }*/
        }

        // G1 = roundup(attrimulti * pOrbs * nOrb * active)
        // nOrb = 1 + 0.25 * (match.count() - 3)
        // pOrbs = (1 + 0.06 * OE_matched) * (1 + 0.05 * team.OEA)
        // bOrb per combo is 1 + 0.25(matched orbs - 3)
        return damageArray;
    }
}
