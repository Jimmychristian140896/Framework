package framework.jimmy.com.framework.adapter;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractHeaderItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import framework.jimmy.com.framework.R;

public class HeaderItem extends AbstractHeaderItem<HeaderItem.HeaderViewHolder> {

    public HeaderItem(String header)
    {
        super();
        this.header = header;
        setSelectable(false);
    }

    public String header;
    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof HeaderItem) {
            HeaderItem inItem = (HeaderItem) inObject;
            return this.header.equals(inItem.header);
        }
        return false;
        //return this.header.equals(((HeaderItem)o).header);
    }

    @Override
    public int hashCode() {
        return header.hashCode();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.header;
    }

    @Override
    public HeaderViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new HeaderViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, HeaderViewHolder holder, int position, List<Object> payloads) {
        holder.textView.setText(header);
    }

    public class HeaderViewHolder extends FlexibleViewHolder
    {
        public TextView textView;
        public HeaderViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter, true);
            textView = view.findViewById(R.id.txt_notification_date);
        }
    }
}
