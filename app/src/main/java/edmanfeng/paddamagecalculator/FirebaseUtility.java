package edmanfeng.paddamagecalculator;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edmanfeng.paddamagecalculator.GameModel.Monster;

/**
 * Created by t7500 on 6/2/2017.
 */

public class FirebaseUtility {
    private static final String TAG = "FirebaseUtility";

    public static Monster getMonster(final int monsterNum) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = db.child("monsters");
        Monster monster;

        ref.child("" + monsterNum).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "Received change: " + dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error when fetching data for " + monsterNum + " : " +
                        databaseError.toString());
            }
        });
        return null;
    }
}
