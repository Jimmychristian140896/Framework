package framework.jimmy.com.framework.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.HistoryOvoDetail;
import framework.jimmy.com.framework.model.NotificationOvo;


public class NotificationOvoAdapter extends RecyclerView.Adapter<NotificationOvoAdapter.NotificationOvoViewHolder> {
    private static final DateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
    private Context mContext;
    private List<NotificationOvo> listNotificationOvo;
    private OnClickRecyclerViewListener listener;
    private Date lastDate;


    public class NotificationOvoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        @BindView(R.id.txt_notification_message)
        public TextView txtNotificationMessage;

        @BindView(R.id.txt_notification_date)
        public TextView txtNotificationDate;

        @BindView(R.id.divider)
        public View divider;

        public NotificationOvoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            listener.onRowClicked(getAdapterPosition());
        }
    }


    public NotificationOvoAdapter(Context mContext, List<NotificationOvo> listNotificationOvo, OnClickRecyclerViewListener listener) {
        this.mContext = mContext;
        this.listNotificationOvo = listNotificationOvo;
        this.listener = listener;
    }

    @Override
    public NotificationOvoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_notification_ovo, parent, false);

        return new NotificationOvoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NotificationOvoViewHolder holder, final int position) {

        NotificationOvo notificationOvo = listNotificationOvo.get(position);
        if(position-1>=0 && listNotificationOvo.get(position).getDate().getTime().getDate() ==  listNotificationOvo.get(position - 1).getDate().getTime().getDate())
        {
            holder.divider.setVisibility(View.INVISIBLE);
            holder.txtNotificationDate.setVisibility(View.GONE);

        }
        else
        {
            holder.txtNotificationDate.setVisibility(View.VISIBLE);
            holder.txtNotificationDate.setText(sdf.format(notificationOvo.getDate().getTime()).toUpperCase());

        }

        if((position + 1 < listNotificationOvo.size()
                && listNotificationOvo.get(position).getDate().getTime().getDate() ==  listNotificationOvo.get(position + 1).getDate().getTime().getDate())
                || position + 1 == listNotificationOvo.size())
        {
            holder.divider.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.divider.setVisibility(View.GONE);
        }

        holder.txtNotificationMessage.setText(notificationOvo.getMessage());
        if(notificationOvo.getRead() == false)
        {
            holder.txtNotificationMessage.setTypeface(holder.txtNotificationMessage.getTypeface(), Typeface.BOLD);
        }
        else
        {
            holder.txtNotificationMessage.setTypeface(Typeface.DEFAULT);
        }
    }






    @Override
    public int getItemCount() {
        return listNotificationOvo.size();
    }




}