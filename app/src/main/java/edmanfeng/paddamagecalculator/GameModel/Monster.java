package edmanfeng.paddamagecalculator.GameModel;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.UUID;

import edmanfeng.paddamagecalculator.PictureUtils;

/**
 * Created by t7500 on 2/25/2017.
 */

public class Monster {
    private static final String TAG = "Monster";
    private static final int MAX_ATTRIBUTES = 2;
    private static final int MAX_TYPES = 3;
    private static final int MAX_AWAKENINGS = 9;
    private static final int MAX_LATENT_SLOTS = 6;

    private String mId;
    private String mOwner;
    private int mHp;
    private int mAtk;
    private int mRcv;
    private String mName;
    private int mNum;
    private Uri imageUrl;

    public Monster() {
        this(UUID.randomUUID().toString(), Values.LOCAL, "CUSTOM", 0, 0, 0, 0);
    }

    public Monster(String uuid, String owner, String name,
                   int num, int hp, int atk, int rcv) {
        this.mId = uuid;
        mOwner = owner;
        this.mName = name;
        this.mNum = num;
        this.mHp = hp;
        this.mAtk = atk;
        this.mRcv = rcv;
    }


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public int getNum() {
        return mNum;
    }

    public void setNum(int num) {
        mNum = num;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getHp() {
        return mHp;
    }

    public void setHp(int hp) {
        mHp = hp;
    }

    public int getAtk() {
        return mAtk;
    }

    public void setAtk(int atk) {
        mAtk = atk;
    }

    public int getRcv() {
        return mRcv;
    }

    public void setRcv(int rcv) {
        mRcv = rcv;
    }

    public Uri getImageUrl() {
        return PictureUtils.getMonsterIconUri(mNum);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = (Monster) o;

        if (mHp != monster.mHp) return false;
        if (mAtk != monster.mAtk) return false;
        if (mRcv != monster.mRcv) return false;
        if (mNum != monster.mNum) return false;
        if (mId != null ? !mId.equals(monster.mId) : monster.mId != null) return false;
        if (mOwner != null ? !mOwner.equals(monster.mOwner) : monster.mOwner != null) return false;
        return mName != null ? mName.equals(monster.mName) : monster.mName == null;

    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mOwner != null ? mOwner.hashCode() : 0);
        result = 31 * result + mHp;
        result = 31 * result + mAtk;
        result = 31 * result + mRcv;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + mNum;
        return result;
    }


}
