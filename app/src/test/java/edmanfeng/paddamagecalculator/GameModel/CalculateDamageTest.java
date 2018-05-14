package edmanfeng.paddamagecalculator.GameModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import edmanfeng.paddamagecalculator.GameModel.Values.Attribute;
import edmanfeng.paddamagecalculator.GameModel.Values.OrbType;
import edmanfeng.paddamagecalculator.GameModel.Values.OrbShape;
import edmanfeng.paddamagecalculator.GameModel.Values.Awakening;


public class CalculateDamageTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void basicEnhancedTest() {
        double leadMulti = 1;
        double friendMulti = 1;
        Team team = new Team();

        // Tsubaki
        int atk = 2513;
        Monster monster = new Monster();
        monster.setAttribute(Attribute.FIRE, 0);
        monster.setAtk(atk);
        monster.setAwakening(Awakening.ENHANCED_ORBS[OrbType.FIRE], 3);
        team.setMonster(monster, 0);

        List<OrbMatch> matches = new ArrayList<>();
        matches.add(new OrbMatch(
                OrbType.HEAL, 3, 1, OrbShape.NORMAL
        ));
        matches.add(new OrbMatch(
                OrbType.FIRE, 3, 3, OrbShape.NORMAL
        ));
        matches.add(new OrbMatch(
                OrbType.LIGHT, 3, 0, OrbShape.NORMAL
        ));

        Damage[] damages = CalculateDamage
                .calculatePerMonsterDamage(team, matches, leadMulti, friendMulti);
        int expected = 5117;
        int actual = damages[0].getDamageAsInt();
        assertEquals(expected, actual);

        matches.clear();
        matches.add(new OrbMatch(
                OrbType.FIRE, 4, 2, OrbShape.TPA
        ));
        matches.add(new OrbMatch(
                OrbType.WATER, 3, 0, OrbShape.NORMAL
        ));
        matches.add(new OrbMatch(
                OrbType.FIRE, 3, 3, OrbShape.NORMAL
        ));
        matches.add(new OrbMatch(
                OrbType.WOOD, 3, 1, OrbShape.NORMAL
        ));
        damages = CalculateDamage
                .calculatePerMonsterDamage(team, matches, leadMulti, friendMulti);
        expected = 13050;
        actual = damages[0].getDamageAsInt();
        assertEquals(expected, actual);
    }

    @Test
    public void basicTeamRowTest() {
        List<OrbMatch> matches = new ArrayList<>();
        OrbMatch match = new OrbMatch(OrbType.FIRE, 6, 0, OrbShape.ROW);
        matches.add(match);
        matches.add(new OrbMatch(
                OrbType.WOOD, 3, 0, OrbShape.NORMAL
        ));
        matches.add(new OrbMatch(
                OrbType.WATER, 3, 0, OrbShape.NORMAL
        ));
        matches.add(new OrbMatch(
                OrbType.FIRE, 6, 0, OrbShape.ROW
        ));

        Team team = new Team();
        double leadMulti = 6;
        double friendMulti = 1.5;
        // Red Valkyrie
        int atk = 1639;
        int row = 2;
        Monster monster = new Monster();
        monster.setAtk(atk);
        monster.setAttribute(Attribute.FIRE, 0);
        monster.setAwakening(Awakening.ROWS[Attribute.FIRE], 2);

        team.setMonster(monster,0);

        // Uriel
        atk = 2387;
        row = 2;
        monster = new Monster();
        monster.setAtk(atk);
        monster.setAttribute(Attribute.FIRE, 0);
        monster.setAwakening(Awakening.ROWS[Attribute.FIRE], 3);
        team.setMonster(monster, 1);

        Damage[] damages = CalculateDamage
                .calculatePerMonsterDamage(team, matches, leadMulti, friendMulti);

        // R. Valk
        int expected = 180756;
        int actual = damages[0].getDamageAsInt();
        assertEquals(expected, actual);

        // Uriel
        expected = 263214;
        actual = damages[2].getDamageAsInt();
        assertEquals(expected, actual);
    }

    @Test
    public void basicRowTest() {

        OrbMatch match = new OrbMatch(OrbType.FIRE, 6, 0, OrbShape.ROW);
        List<OrbMatch> matches = new ArrayList<>();
        matches.add(match);

        // Red Valkyrie
        int atk = 1639;
        Monster monster = new Monster();
        monster.setAtk(atk);
        monster.setAttribute(Attribute.FIRE, 0);
        monster.setAwakening(Awakening.ROWS[Attribute.FIRE], 2);
        Team team = new Team();
        team.setMonster(monster,0);

        double leadMulti = 6;
        double friendMulti = 1.5;

        Damage[] damages = CalculateDamage
                .calculatePerMonsterDamage(team, matches, leadMulti, friendMulti);

        int expected = 30987;
        int actual = damages[0].getDamageAsInt();
        assertEquals(expected, actual);

    }

    @Test
    public void basicDamageTest() {
        Team team = new Team();
        Monster monster = new Monster();
        monster.setAtk(2454);
        monster.setAttribute(Attribute.WATER, 0);
        monster.setAttribute(Attribute.DARK, 1);
        team.setMonster(monster, 0);

        double leaderMulti = 1.5;
        List<OrbMatch> combos = new ArrayList<>();
        OrbMatch match = new OrbMatch(OrbType.WATER, 3, 0, OrbShape.NORMAL);
        combos.add(match);
        Damage[] damage = CalculateDamage.calculatePerMonsterDamage(team, combos, leaderMulti, 1);

        int actual = damage[0].getDamageAsInt();
        int expectedDamage = 3681;

        assertEquals(expectedDamage, actual);

        match = new OrbMatch(OrbType.WOOD, 3, 0, OrbShape.NORMAL);
        combos.add(match);
        match = new OrbMatch(OrbType.WATER, 4, 0, OrbShape.TPA);
        combos.add(match);

        expectedDamage = 12425;
        damage = CalculateDamage.calculatePerMonsterDamage(team, combos, leaderMulti, 1);
        actual = damage[0].getDamageAsInt();
        assertEquals(expectedDamage, actual);
    }


}