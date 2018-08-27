package framework.jimmy.com.framework.adapter;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.AbstractSectionableItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.model.NotificationOvo;

public class MyFlexibleItem extends AbstractSectionableItem<MyFlexibleItem.MyViewHolder, HeaderItem> {

    private NotificationOvo notificationOvo;


    public MyFlexibleItem(NotificationOvo notificationOvo, HeaderItem header)
    {
        super(header);
        this.notificationOvo = notificationOvo;
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof NotificationOvo)
        {
            NotificationOvo notificationOvo = (NotificationOvo) o;
            return this.notificationOvo.equals(notificationOvo);
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.model;
    }

    @Override
    public MyViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new MyViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, MyViewHolder holder, int position, List<Object> payloads) {
        holder.txtNotificationMessage.setText(notificationOvo.getMessage());
    }

    public class MyViewHolder extends FlexibleViewHolder {

        public TextView txtNotificationMessage;

        public MyViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            txtNotificationMessage=itemView.findViewById(R.id.txt_notification_message);
        }
    }
}
