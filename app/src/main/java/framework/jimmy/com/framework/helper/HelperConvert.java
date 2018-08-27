package framework.jimmy.com.framework.helper;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static android.os.Build.VERSION_CODES.N;

public class HelperConvert {
    public static Spanned convertFromHtml(String html)
    {
        Spanned result;
        if (Build.VERSION.SDK_INT >= N)
        {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }
        else
        {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public static Bitmap convertStringToQRCode(String value)
    {
        int width = 512, height = 512;
        Log.d("StringToQRCodeStart", String.valueOf(System.currentTimeMillis()));
        String text=value; // Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,width,height);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            Log.d("StringToQRCodeEnd", String.valueOf(System.currentTimeMillis()));
            return  bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }



        /*QRCodeWriter writer = new QRCodeWriter();
        try
        {
            BitMatrix bitMatrix = writer.encode(value, BarcodeFormat.QR_CODE, width, height);
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            return bmp;

        }
        catch (WriterException ex)
        {
            return null;
        }*/
    }



}
