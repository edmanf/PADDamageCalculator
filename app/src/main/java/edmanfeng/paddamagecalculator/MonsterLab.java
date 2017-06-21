package edmanfeng.paddamagecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.Values;
import edmanfeng.paddamagecalculator.database.MonsterBaseHelper;
import edmanfeng.paddamagecalculator.database.MonsterCursorWrapper;
import edmanfeng.paddamagecalculator.database.PadDbSchema.MonsterTable;

/**
 * A class for retrieving monsters from both local databases as well
 * as firebase.
 */

public class MonsterLab {
    private static final String TAG = "MonsterLab";
    private static MonsterLab sMonsterLab;

    // For custom monsters
    private SQLiteDatabase mDatabase;

    // For the default firebase monsters
    private List<Monster> mFirebaseMonsters;

    private MonsterLab(Context context) {
        mDatabase = new MonsterBaseHelper(context)
                .getWritableDatabase();

        mFirebaseMonsters = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = db.child("monsters");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Datachanged");
                mFirebaseMonsters.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Monster monster = child.getValue(Monster.class);
                    //List<Long> attrs = (List<Long>) child.child("attribute").getValue();
                    //monster.setAttributes(attrs);
                    monster.setOwner(Values.FIREBASE);
                    mFirebaseMonsters.add(monster);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static MonsterLab get(Context context) {
        if (sMonsterLab == null) {
            sMonsterLab = new MonsterLab(context);
        }
        return sMonsterLab;
    }

    public void addMonster(Monster monster) {
        ContentValues contentValues = getContentValues(monster);
        mDatabase.insert(MonsterTable.NAME, null, contentValues);
    }

    public void updateMonster(Monster monster) {
        String whereClause = MonsterTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] { monster.getId() };
        ContentValues values = getContentValues(monster);
        mDatabase.update(
                MonsterTable.NAME,
                values,
                whereClause,
                whereArgs);
    }

    public void deleteMonster(Monster monster) {
        String whereClause = MonsterTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] { monster.getId() };
        ContentValues values = getContentValues(monster);
        mDatabase.delete(MonsterTable.Cols.NAME, whereClause, whereArgs);

    }

    public Monster getMonster(String id) {
        String query = MonsterTable.Cols.ID + " = ?";
        String[] args = new String[] { id };

        MonsterCursorWrapper cursor = queryMonsters(query, args);
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getMonster();
        } finally {
            cursor.close();
        }
    }

    public List<Monster> getMonsters() {
        List<Monster> monsters = new ArrayList<>();
        MonsterCursorWrapper cursor = queryMonsters(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                monsters.add(cursor.getMonster());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Log.i(TAG, "Found monsters: " + monsters.size());
        return monsters;
    }


    public List<Monster> getFirebaseMonsters() {
        return mFirebaseMonsters;
    }

    public Monster getFirebaseMonster(int num) {
        Monster monster = null;
        int lo = 0;
        int hi = mFirebaseMonsters.size() - 1;
        int mid = (lo + hi) / 2;
        while (hi > lo) {

            monster = mFirebaseMonsters.get(mid);
            if (monster.getNum() > num) {
                hi = mid - 1;
            } else if (monster.getNum() < num) {
                lo = mid + 1;
            } else {
                break;
            }
            mid = (lo + hi) / 2;
        }
        monster = mFirebaseMonsters.get(mid);
        return monster;
    }

    public ContentValues getContentValues(Monster monster) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MonsterTable.Cols.ID, monster.getId());
        contentValues.put(MonsterTable.Cols.OWNER, monster.getOwner());
        contentValues.put(MonsterTable.Cols.NAME, monster.getName());
        contentValues.put(MonsterTable.Cols.NUM, monster.getNum());

        contentValues.put(MonsterTable.Cols.BASE_HP, monster.getHp());
        contentValues.put(MonsterTable.Cols.BASE_ATK, monster.getAtk());
        contentValues.put(MonsterTable.Cols.BASE_RCV, monster.getRcv());

        int[] attrs = monster.getAttributes();
        contentValues.put(MonsterTable.Cols.ATTRIBUTES, "" + attrs[0] + "," + attrs[1]);
        return contentValues;
    }

    public MonsterCursorWrapper queryMonsters(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                MonsterTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new MonsterCursorWrapper(cursor);
    }


}
