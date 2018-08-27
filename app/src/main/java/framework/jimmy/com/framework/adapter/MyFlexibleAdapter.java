package framework.jimmy.com.framework.adapter;

import android.support.annotation.Nullable;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class MyFlexibleAdapter extends FlexibleAdapter<IFlexible> {
    public MyFlexibleAdapter(@Nullable List<IFlexible> items, @Nullable Object listeners) {
        super(items, listeners);
    }
}
