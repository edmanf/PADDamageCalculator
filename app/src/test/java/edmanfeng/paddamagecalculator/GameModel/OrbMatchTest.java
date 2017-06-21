package edmanfeng.paddamagecalculator.GameModel;

import org.junit.Before;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by t7500 on 6/20/2017.
 */
public class OrbMatchTest {
    // mock team
    private Team mTeam;
    private List<OrbMatch> mCombos;

    @Before
    public void setup() throws Exception {
        mTeam = mock(Team.class);
    }
}