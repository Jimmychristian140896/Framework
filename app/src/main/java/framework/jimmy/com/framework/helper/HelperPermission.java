package framework.jimmy.com.framework.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class HelperPermission {
    public static final int CONTACTS_REQUEST_CODE = 1;
    public static Boolean askForPermission(Activity context, String permission, Integer requestCode) {
        Boolean result = false;
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(context, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(context, new String[]{permission}, requestCode);
            }
        } else {
            result =true;
        }
        return  result;
    }
}
