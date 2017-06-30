package edmanfeng.paddamagecalculator;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.net.Uri;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import edmanfeng.paddamagecalculator.GameModel.Monster;

/**
 * Created by t7500 on 6/22/2017.
 */

/*
@InverseBindingMethods({
        @InverseBindingMethod(
                type = AppCompatSpinner.class,
                attribute = "selectedValue"
        )
})*/
public class BindingAdapters {
    private static final String TAG = "BindingAdapters";

    @BindingAdapter({"bind:imageUrl"})
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


/*
    @BindingAdapter(value = {"bind:selectedValue", "bind:selectedValueAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner spinner,String newSelectedValue,
                                       final InverseBindingAdapter newTextAttrChanged) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //newTextAttrChanged.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(newSelectedValue);
            spinner.setSelection(pos);
        }
    }*/

    /*
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageButton imageButton, String imageUrl) {
        Log.d(TAG, "load image " + imageUrl);
        Uri uri = Uri.parse(imageUrl);
        Glide.with(imageButton.getContext())
                .load(uri)
                .fitCenter()
                .into(imageButton);
    }*/
}
