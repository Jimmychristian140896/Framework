package framework.jimmy.com.framework.util.barcode;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import framework.jimmy.com.framework.activity.CustomBarcodeScannerActivity;

public class MyBarcodeUtil {
    public static void scanCustom(Activity activity)
    {
        new IntentIntegrator(activity).setCaptureActivity(CustomBarcodeScannerActivity.class).initiateScan();
        //IntentIntegrator.forSupportFragment(fragment).setCaptureActivity(CustomBarcodeScannerActivity.class).initiateScan();
    }

    public static void scan(Activity activity)
    {
        new IntentIntegrator(activity).initiateScan();
    }

    public static void parseActivityResult(Activity activity, int reqCode, int resultCode, Intent data, BarcodeResult barcodeResult)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(reqCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.e(activity.getLocalClassName(), "Cancelled scan");
                barcodeResult.failed();
                //Toast.makeText(activity, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                // dapetin hasil scannya
                Log.e(activity.getLocalClassName(), "Scanned: " + result.getContents());
                barcodeResult.success(result.getContents());
                // wishlistCheckProduct(result.getContents());

            }
        }
        else {
            barcodeResult.notBarcode();
        }

    }

    public interface BarcodeResult
    {
        public void success(String result);
        public void failed();
        public void notBarcode();
    }
}
