package framework.jimmy.com.framework.model;


import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleViewHolder;

import framework.jimmy.com.framework.R;

public class GalaxyCell extends SimpleCell<NotificationOvo,GalaxyCell.ViewHolder> {


    public GalaxyCell(@NonNull NotificationOvo item) {
        super(item);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.model;
    }

    /*
    - Return a ViewHolder instance
     */
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
        return new ViewHolder(cellView);
    }

    /*
    - Bind data to widgets in our viewholder.
     */
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Context context, Object o) {

        viewHolder.txtNotificationMessage.setText(getItem().getMessage());
        if(getItem().getRead() == false)
        {
            viewHolder.txtNotificationMessage.setTypeface(viewHolder.txtNotificationMessage.getTypeface(), Typeface.BOLD);
        }
        else
        {
            viewHolder.txtNotificationMessage.setTypeface(Typeface.DEFAULT);
        }
    }
    /**
     - Our ViewHolder class.
     - Inner static class.
     * Define your view holder, which must extend SimpleViewHolder.
     * */
    public static class ViewHolder extends SimpleViewHolder {
        TextView txtNotificationMessage;

        ViewHolder(View itemView) {
            super(itemView);
            txtNotificationMessage=itemView.findViewById(R.id.txt_notification_message);

        }
    }
}