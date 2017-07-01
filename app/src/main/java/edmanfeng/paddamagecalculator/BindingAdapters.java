package edmanfeng.paddamagecalculator;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.net.Uri;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.PagerSnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import edmanfeng.paddamagecalculator.GameModel.Monster;
import edmanfeng.paddamagecalculator.GameModel.OrbMatch;

/**
 * This class stores the adapters for data binding
 */

public class BindingAdapters {
    private static final String TAG = "BindingAdapters";

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView image, String imageUrl) {
        Log.d(TAG, "load image " + imageUrl);
        String url = imageUrl;
        if (imageUrl == null) {
            url = new Monster().getImageUrl().toString();
        }
        Uri uri = Uri.parse(url);
        Glide.with(image.getContext())
                .load(uri)
                .placeholder(R.drawable.monster_image_placeholder)
                .fitCenter()
                .into(image);
    }


    @BindingAdapter({"app:selectedValue"})
    public static void setValue(AppCompatSpinner appCompatSpinner,
                                int value) {
        appCompatSpinner.setSelection(value);
        Log.d(TAG, "Set: " + value);
    }

    @InverseBindingAdapter(attribute = "app:selectedValue", event = "app:selectedValueAttrChanged")
    public static int getValue(AppCompatSpinner appCompatSpinner) {
        int res = appCompatSpinner.getSelectedItemPosition();
        Log.d(TAG, "Inverse get: " + res);
        return res;
    }

    @BindingAdapter(value = {"bind:selectedValue",
            "bind:selectedValueAttrChanged"},
            requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner pAppCompatSpinner,
                                       final String newSelectedValue,
                                       final InverseBindingListener newTextAttrChanged) {
        Log.d(TAG, "bindSpinnerData: " + newSelectedValue);
        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "bindSpinnerData: onItemSelected");
                if (newSelectedValue != null && newSelectedValue.equals(parent.getSelectedItem())) {
                    Log.d(TAG, "No change");
                    return;
                }
                newTextAttrChanged.onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            pAppCompatSpinner.setSelection(pos, true);
        }
    }

    public static void onAddCombo(OrbMatch combo, List<OrbMatch> list) {
        if (combo.getCount() > 0 && combo.getEnhanced() <= combo.getCount()) {
            list.add(combo);
        } else {
            Log.d(TAG, "bad combo");
            // display a toast describing the error
        }
    }

}
