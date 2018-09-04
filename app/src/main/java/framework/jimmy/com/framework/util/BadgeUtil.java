package framework.jimmy.com.framework.util;

import android.content.Context;

import me.leolin.shortcutbadger.ShortcutBadger;

public class BadgeUtil {
    public static void showIconBadge(Context context, int badgeCount)
    {
        ShortcutBadger.applyCount(context, badgeCount);
    }

    public static void hideIconBadge(Context context)
    {
        ShortcutBadger.applyCount(context, 0);
        //ShortcutBadger.removeCount(context);
    }
}
