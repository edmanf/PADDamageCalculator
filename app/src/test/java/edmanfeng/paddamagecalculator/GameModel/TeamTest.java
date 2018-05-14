package edmanfeng.paddamagecalculator.GameModel;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Attr;

import edmanfeng.paddamagecalculator.GameModel.Values.Awakening;
import edmanfeng.paddamagecalculator.GameModel.Values.Attribute;

import static org.junit.Assert.*;
import edmanfeng.paddamagecalculator.GameModel.Values.Awakening;
import edmanfeng.paddamagecalculator.GameModel.Values.Attribute;

/**
 * Created by t7500 on 6/22/2017.
 */
public class TeamTest {
    private Team mTeam;

    @Before
    public void setUp()  {
        mTeam = new Team();
        mTeam.setMonsters(new Monster[] {
                new Monster(),
                new Monster(),
                new Monster(),
                new Monster(),
                new Monster(),
                new Monster()
        });
    }

    @Test
    public void rowAwakeningCorrect() {
        assertEquals(mTeam.getAwakening(Awakening.ROWS[Attribute.FIRE]), 0);
        assertEquals(mTeam.getAwakening(Awakening.ROWS[Attribute.WATER]), 0);
        mTeam.getMonster(0).setAwakening(Awakening.ROWS[Attribute.FIRE], 2);
        assertEquals(mTeam.getAwakening(Awakening.ROWS[Attribute.FIRE]), 2);
        assertEquals(mTeam.getAwakening(Awakening.ROWS[Attribute.WATER]), 0);
    }
}