package edmanfeng.paddamagecalculator.GameModel;

import java.math.BigDecimal;

/**
 * Defines categories of values for monsters
 */

public class Values {

    /**
     * ID used to identify monsters and teams owned locally,
     * not by a synced padherder account. Padherder does not allow
     * "~" in its usernames.
     */
    public static final String LOCAL = "~~LOCAL~~";
    public static final String FIREBASE = "~~FIREBASE~~";

    public static final String NO_MONSTER_ID = "";

    public static final int SOLO_TEAM_SIZE = 6;

    public static final class Attribute {
        public static final int NONE = -1;
        public static final int FIRE = 0;
        public static final int WATER = 1;
        public static final int WOOD = 2;
        public static final int LIGHT = 3;
        public static final int DARK = 4;
    }

    public static final class Type {
        public static final int NONE = -1;
        public static final int GOD = 0;
        public static final int DRAGON = 1;
        public static final int DEVIL = 2;
        public static final int MACHINE = 3;
        public static final int BALANCED = 4;
        public static final int ATTACKER = 5;
        public static final int PHYSICAL = 6;
        public static final int HEALER = 7;
        public static final int EVO_MATERIAL = 8;
        public static final int AWAKEN_MATERIAL = 9;
        public static final int ENHANCE_MATERIAL = 10;
        public static final int REDEEMABLE_MATERIAL = 11;
    }

    public static final class Awakening {

        public static final int NONE = -1;
        public static final int REDUCE_FIRE_DAMAGE = 0;
        public static final int REDUCE_WATER_DAMAGE = 1;
        public static final int REDUCE_WOOD_DAMAGE = 2;
        public static final int REDUCE_LIGHT_DAMAGE = 3;
        public static final int REDUCE_DARK_DAMAGE = 4;
        public static final int ENHANCED_HP = 5;
        public static final int ENHANCED_ATK = 6;
        public static final int ENHANCED_RCV = 7;
        public static final int ENHANCED_FIRE_ORBS = 8;
        public static final int ENHANCED_WATER_ORBS = 9;
        public static final int ENHANCED_WOOD_ORBS = 10;
        public static final int ENHANCED_LIGHT_ORBS = 11;
        public static final int ENHANCED_DARK_ORBS = 12;
        public static final int ENHANCED_FIRE_ROW = 13;
        public static final int ENHANCED_WATER_ROW = 14;
        public static final int ENHANCED_WOOD_ROW = 15;
        public static final int ENHANCED_LIGHT_ROW = 16;
        public static final int ENHANCED_DARK_ROW = 17;
        public static final int AUTOHEAL = 18;
        public static final int BIND_RESIST = 19;
        public static final int BIND_CLEAR = 20;
        public static final int BLIND_RESIST = 21;
        public static final int JAMMER_RESIST = 22;
        public static final int POISON_RESIST = 23;
        public static final int TIME_EXTEND = 24;
        public static final int SKILL_BOOST = 25;
        public static final int TWO_PRONG = 26;
        public static final int SKILL_BIND_RESIST = 27;
        public static final int HEAL_ORB_ENHANCE = 28;
        public static final int MULTI_BOOST = 29;
        public static final int GOD_KILLER = 29;
        public static final int DRAGON_KILLER = 30;
        public static final int DEVIL_KILLER = 31;
        public static final int MACHINE_KILLER = 32;
        public static final int BALANCE_KILLER = 33;
        public static final int ATTACKER_KILLER = 34;
        public static final int PHYSICAL_KILLER = 35;
        public static final int HEALER_KILLER = 36;
        public static final int EVO_MATERIAL_KILLER = 37;
        public static final int AWOKEN_MATERIAL_KILLER = 38;
        public static final int ENHANCE_MATERIAL_KILLER = 39;
        public static final int REDEEMABLE_MATERIAL_KILLER = 40;
        public static final int ENHANCED_COMBO_7 = 41;
        public static final int DEFENSE_BREAK = 42;
        public static final int ENHANCED_TEAM_HP = 43;
        public static final int ENHANCED_TEAM_RCV = 44;
        public static final int DAMAGE_VOID_PIERCER = 45;
        public static final int SUPER_BONUS_ATTACK = 46;
        public static final int HP_LESS_THAN_ENHANCED = 47;
        public static final int HP_GREATER_THAN_ENHANCED = 48;
        public static final int L_INCREASED_ATTACK = 49;
        public static final int SUPER_ENHANCED_COMBOS = 50;


        // Index i is attribute i and ROWS[i] is the row awakening for that attribute
        public static final int[] ROWS = new int[]{
                ENHANCED_FIRE_ROW,
                ENHANCED_WATER_ROW,
                ENHANCED_WOOD_ROW,
                ENHANCED_LIGHT_ROW,
                ENHANCED_DARK_ROW};

        // access with OrbType
        public static final int[] ENHANCED_ORBS = new int[] {
                ENHANCED_FIRE_ORBS,
                ENHANCED_WATER_ORBS,
                ENHANCED_WOOD_ORBS,
                ENHANCED_LIGHT_ORBS,
                ENHANCED_DARK_ORBS
        };
    }

    public static final class AwakeningValue {
        /**
         * reduction in damage taken for a specific attribute.
         */
        public static final BigDecimal RESIST_ELEMENT = new BigDecimal(0.05);
        public static final int HP_ENHANCE = 200;
        public static final int ATK_ENHANCE = 100;
        public static final int RCV_ENHANCE = 50;

        /**
         * Damage increased for having an orb enhance awakening
         */
        public static final double ORB_ENHANCE_BASE = 0.05;

        /**
         * Damage increased for matching an enhanced orb
         */
        public static final double ORB_ENHANCE_MATCHED = 0.06;

        /**
         * Damage increase for matching a row.
         */
        public static final BigDecimal ROW_ENHANCE = new BigDecimal(0.1);

        /**
         * HP recovered
         */
        public static final int AUTOHEAL = 800;

        /**
         * chance for unit to resist a bind
         */
        public static final BigDecimal BIND_RESIST = new BigDecimal(0.50);

        /**
         * Turns of bind cleared for entire team.
         */
        public static final int BIND_CLEAR = 3;

        /**
         * chance to block blinds
         */
        public static final BigDecimal BLIND_RESIST = new BigDecimal(0.20);

        /**
         * chance to block jammers and bombs
         */
        public static final BigDecimal JAMMER_RESIST = new BigDecimal(0.20);

        /**
         * chance to block poisons
         */
        public static final BigDecimal POISON_RESIST = new BigDecimal(0.20);

        /**
         * Additional time added to move time in seconds.
         */
        public static final BigDecimal TIME_EXTEND = new BigDecimal(0.5);

        /**
         * TPA multiplier (multiplicative)
         */
        public static final double TWO_PRONG = 1.5;

        /**
         * chance to block skill bind
         */
        public static final BigDecimal SKILL_BIND_RESIST = new BigDecimal(0.2);

        /**
         * Stat multiplier
         */
        public static final BigDecimal MULTI_BOOST = new BigDecimal(1.5);

        /**
         * Atk multiplier against specific type
         */
        public static final int KILLER = 3;

        /**
         * Atk multiplier for 7+ combos
         */
        public static final int ENHANCED_COMBO_7 = 2;

        /**
         * defense ignored by unit (additive)
         */
        public static final BigDecimal DEFENSE_BREAK = new BigDecimal(0.5);

        public static final double VOID_DEFENSE_PIERCER = 1.5;

        public static final double L_INCREASED_ATTACK = 1.5;

    }

    public static final class LatentAwakening {
        public static final int NONE = -1;
        public static final int IMPROVED_HP = 0;
        public static final int IMPROVED_ATK = 1;
        public static final int IMPROVED_RCV = 2;
        public static final int AUTOHEAL = 3;
        public static final int TIME_EXTEND = 4;
        public static final int FIRE_RESIST = 5;
        public static final int WATER_RESIST = 6;
        public static final int WOOD_RESIST = 7;
        public static final int LIGHT_RESIST = 8;
        public static final int DARK_RESIST = 9;
        public static final int SKILL_DELAY_RESIST = 10;
        public static final int IMPROVED_ALL = 11;
        public static final int GOD_KILLER = 12;
        public static final int DRAGON_KILLER = 13;
        public static final int DEVIL_KILLER = 14;
        public static final int MACHINE_KILLER = 15;
        public static final int BALANCED_KILLER = 16;
        public static final int ATTACKER_KILLER = 17;
        public static final int PHYSICAL_KILLER = 18;
        public static final int HEALER_KILLER = 19;
    }

    public static final class LatentAwakeningValue {
        public static int slotsUsed(int latent) {
            if (latent == LatentAwakening.IMPROVED_ALL ||
                    latent == LatentAwakening.GOD_KILLER ||
                    latent == LatentAwakening.DRAGON_KILLER ||
                    latent == LatentAwakening.DEVIL_KILLER ||
                    latent == LatentAwakening.MACHINE_KILLER ||
                    latent == LatentAwakening.BALANCED_KILLER ||
                    latent == LatentAwakening.ATTACKER_KILLER ||
                    latent == LatentAwakening.PHYSICAL_KILLER ||
                    latent == LatentAwakening.HEALER_KILLER) {
                return 2;
            } else {
                return 1;
            }
        }

        public static final BigDecimal IMPROVED_HP = new BigDecimal(0.015);
        public static final BigDecimal IMPROVED_ATK = new BigDecimal(0.01);
        public static final BigDecimal IMPROVED_RCV = new BigDecimal(0.05);

        /**
         * % of unit RCV healed after match
         */
        public static final BigDecimal AUTOHEAL = new BigDecimal(0.15);

        /**
         * Combo time added in seconds
         */
        public static final BigDecimal TIME_EXTEND = new BigDecimal(0.005);
        public static final BigDecimal RESIST_ELEMENT = new BigDecimal(0.01);

        /**
         * Atk multiplier against enemy type.
         */
        public static final BigDecimal KILLER = new BigDecimal(1.5);
    }

    public static final class Badge {
        public static final int NONE = -1;
        public static final int TEAM_COST = 0;
        public static final int EXTEND_TIME = 1;
        public static final int MASS_ATTACK = 2;
        public static final int IMPROVED_RCV = 3;
        public static final int IMPROVED_HP = 4;
        public static final int IMPROVED_ATK = 5;
        public static final int SKILL_BOOST = 6;
        public static final int BIND_RESIST = 7;
        public static final int SKILL_BIND_RESIST = 8;
        public static final int IMPROVED_HP_2 = 9;
        public static final int EXTEND_TIME_2 = 10;
        public static final int IMPROVED_RCV_2 = 11;
        public static final int IMPROVED_ATK_2 = 12;
    }

    public static final class BadgeValue {
        public static final BigDecimal IMPROVED_RCV = new BigDecimal(0.25);
        public static final BigDecimal IMPROVED_HP = new BigDecimal(0.05);
        public static final BigDecimal IMPROVED_ATK = new BigDecimal(0.05);
        public static final BigDecimal SKILL_BIND_RESIST = new BigDecimal(0.50);
        public static final BigDecimal EXTEND_TIME = new BigDecimal(1.0);
        public static final BigDecimal IMPROVED_HP_2 = new BigDecimal(0.15);
        public static final BigDecimal EXTEND_TIME_2 = new BigDecimal(2.0);
        public static final BigDecimal IMPROVED_RCV_2 = new BigDecimal(0.35);
        public static final BigDecimal IMPROVED_ATK_2 = new BigDecimal(0.15);
    }

    public static final class OrbType {
        public static int FIRE = 0;
        public static int WATER = 1;
        public static int WOOD = 2;
        public static int LIGHT = 3;
        public static int DARK = 4;
        public static int HEAL = 5;
        public static int JAMMER = 6;
        public static int POISON = 7;
        public static final String[] ORB_TYPES = new String[] {"FIRE", "WATER",
                "WOOD","LIGHT", "DARK", "HEAL", "JAMMER", "POISON"};
    }

    public static final class OrbShape {
        public static final int NORMAL = 0;
        public static final int TPA = 1;
        public static final int ROW = 2;
        public static final int CROSS = 3;
        public static final int COLUMN = 4;
        public static final int SQUARE_3X3 = 5;
        public static final int L_5 = 6;
        public static final String[] SHAPES = new String[] {"NORMAL", "TPA",
                "ROW", "CROSS", "COLUMN", "SQUARE_3X3", "L_5"};
    }

    public static final class LeaderSkillType {
        public static final int NONE = -1;

        // General static stat multipliers
        public static final int BASIC = 0;

        // Skill applies when connecting a certain amount of orbs in a match
        public static final int CONNECTED = 1;

        // Skill applies when matching certain combos
        public static final int COMBO = 2;

        // Skill applies when matching at least one enhanced orb in a 5 orb match
        public static final int ENHANCED_MATCH = 3;

        // Skill applies when matching a certain combination of orb types
        public static final int ORB_TYPE_MATCH = 4;

        // Skill applies when matching heart orbs in a cross shape
        public static final int HEART_CROSS = 5;

        // Skill applies when matching orbs in a cross shape
        public static final int CROSS = 6;

        // Skill applies when HP% is in a certain range
        public static final int HP_CONDITIONAL = 7;

        // Skill applies if a skill was used that turn
        public static final int SKILL_USE = 8;

        // Skill applies after taking damage
        public static final int COUNTER = 9;

        // Skill applies after making a match
        public static final int POST_MATCH = 10;

        // Skill applies when a team has a certain sub or subs
        public static final int TEAMMATE = 11;

        // Skill applies when played in co-op mode
        public static final int COOPERATION = 12;

        // Skill prevents death based on team HP%
        public static final int RESOLVE = 13;

        // Skill changes the dimensions of the board
        public static final int BOARD_SIZE = 14;

        // Skill changes the ammount of orbs needed to make a match
        public static final int MIN_CONNECTED = 15;

        // Skill changes the rewards received after completing a dungeon
        public static final int BOOST = 16;

        // Skill prevents skyfalls from matching
        public static final int RESTRICT_SKYFALL_MATCHES = 17;

        // SKill changes the amount of move time the user has
        public static final int MOVE_TIME = 18;
    }
}
