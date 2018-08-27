package framework.jimmy.com.framework.util;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import framework.jimmy.com.framework.BuildConfig;
import timber.log.Timber;

import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.VERBOSE;
import static android.util.Log.WARN;
import static com.google.android.gms.analytics.Logger.LogLevel.WARNING;

public class LogUtil {


    public static void init()
    {
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new ReleaseTree());

    }

    public static void i(String tag, String message) {

        if (BuildConfig.DEBUG)
            Log.i(tag, message);
        else
            Crashlytics.log(INFO, tag, message);
        Timber.i(message);
    }

    public static void d(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.d(tag, message);
        else
            Crashlytics.log(DEBUG, tag, message);
        Timber.d(message);
    }

    public static void w(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.w(tag, message);
        else
            Crashlytics.log(WARN, tag, message);
        Timber.w(message);
    }

    public static void e(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.e(tag, message);
        else
            Crashlytics.log(ERROR, tag, message);
        Timber.e(message);
    }

    public static void v(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.v(tag, message);
        else
            Crashlytics.log(VERBOSE, tag, message);
        Timber.v(message);
    }

}
