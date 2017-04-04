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
            public static final String FRIEND_LEADER = "friend lead";
            public static final String MP_SUB1 = "mp sub 1";
            public static final String MP_SUB2 = "mp sub 2";
            public static final String MP_SUB3 = "mp sub 3";
            public static final String MP_SUB4 = "mp sub 4";
        }
    }
}
