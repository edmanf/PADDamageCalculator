package edmanfeng.paddamagecalculator.database;

import android.provider.BaseColumns;

/**
 * Created by t7500 on 4/3/2017.
 */

public class PadDbSchema {
    public static final class TeamTable {
        public static final String NAME = "teams";

        public static final class Cols implements BaseColumns {
            public static final String UUID = "uuid";
            public static final String LEADER = "leader";
            public static final String SUB1 = "sub1";
            public static final String SUB2 = "sub2";
            public static final String SUB3 = "sub3";
            public static final String SUB4 = "sub4";
            public static final String FRIEND_LEADER = "friend_lead";
            public static final String MP_SUB1 = "mp_sub1";
            public static final String MP_SUB2 = "mp_sub2";
            public static final String MP_SUB3 = "mp_sub3";
            public static final String MP_SUB4 = "mp_sub4";
        }
    }

    public static final class MonsterTable {
        public static final String NAME = "monsters";

        public static final class Cols implements BaseColumns {
            public static final String UUID = "uuid";
            public static final String NUM = "num";
            public static final String NAME = "name";
            public static final String AWAKENINGS = "awakenings";
            public static final String LATENT_AWAKENINGS = "latents";
            public static final String BASE_HP = "hp";
            public static final String BASE_ATK = "atk";
            public static final String BASE_RCV = "rcv";
            public static final String BASE_INHERIT = "inherit";
            public static final String ACTIVE_SKILL = "active";
            public static final String LEADER_SKILL = "leader";
        }
    }
}
