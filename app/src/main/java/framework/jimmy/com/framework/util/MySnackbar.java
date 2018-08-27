package framework.jimmy.com.framework.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import framework.jimmy.com.framework.R;

public class MySnackbar {
    public static void showSnackbarColored(Context context, View view, String text, int duration)
    {
        Snackbar snackbar = Snackbar.make(view,text, duration);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        snackbar.show();
    }
}
