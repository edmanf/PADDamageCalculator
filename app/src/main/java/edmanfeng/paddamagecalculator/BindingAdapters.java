package edmanfeng.paddamagecalculator;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by t7500 on 6/22/2017.
 */

public class BindingAdapters {
    private static final String TAG = "BindingAdapters";

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView image, String imageUrl) {
        Log.d(TAG, "load image " + imageUrl);
        Uri uri = Uri.parse(imageUrl);
        Glide.with(image.getContext())
                .load(uri)
                .fitCenter()
                .into(image);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageButton imageButton, String imageUrl) {
        Log.d(TAG, "load image " + imageUrl);
        Uri uri = Uri.parse(imageUrl);
        Glide.with(imageButton.getContext())
                .load(uri)
                .fitCenter()
                .into(imageButton);
    }
}
