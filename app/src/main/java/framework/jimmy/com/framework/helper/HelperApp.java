package framework.jimmy.com.framework.helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class HelperApp {
    public static void copyToClipboard(Context context, String label, String text)
    {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
    }
}
