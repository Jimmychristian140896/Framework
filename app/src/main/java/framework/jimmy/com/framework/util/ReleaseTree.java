package framework.jimmy.com.framework.util;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

import static android.util.Log.ERROR;
import static android.util.Log.WARN;


public class ReleaseTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {

        if (priority == ERROR || priority == WARN)
            Crashlytics.log(priority, tag, message);
    }
}