package framework.jimmy.com.framework.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.util.Locale;

public class IntentUtil {
    public static void mapNavigationIntent(Activity activity, double latitude, double longitude)
    {
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        activity.startActivity(mapIntent);
    }

    public static void mapViewIntent(Activity activity, double latitude, double longitude)
    {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        activity.startActivity(intent);
    }
}
