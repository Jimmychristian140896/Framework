package framework.jimmy.com.framework.util.image;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import framework.jimmy.com.framework.FrameworkApplication;
import framework.jimmy.com.framework.R;
/*import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
*/
//import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
//import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class PicassoUtil {

    private static Picasso mPiccaso;

    public static void Piccaso(Context context, String url, ImageView imageView)
    {
        int color = Color.parseColor("#339b59b6");
        Picasso.get()
                //.with(context)
                //.get()
                .load(url) //http://i.imgur.com/DvpvklR.png
                //.load("file:///android_asset/DvpvklR.png")
                //.load(R.drawable.ic_account_balance_wallet_black_24dp)
                //.load(new File("path"))
                .placeholder(R.drawable.ic_add_black_24dp)
                .error(R.drawable.ic_expand_more_black_24dp)
                //.transform(new ColorFilterTransformation(color))
                //.transform(new CropCircleTransformation())
                //.resize(50, 50)
                //.centerCrop()
                //.into(imageView)
                .into(imageView);
    }

    public static void Glide(Context context, String url, ImageView imageView)
    {
        /*MultiTransformation multi = new MultiTransformation(
                new BlurTransformation(25),
                new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));
*/
        /*Glide.with(context).load(url)
                //.apply(bitmapTransform(new BlurTransformation(25, 3)))

                //.apply(bitmapTransform(multi))
                .into(imageView);
*/
        GlideApp
                .with(context)
                .load(url)
                //.centerCrop()
                .circleCrop()
                .placeholder(R.drawable.ic_history_black_24dp)
                .into(imageView);



    }


}
