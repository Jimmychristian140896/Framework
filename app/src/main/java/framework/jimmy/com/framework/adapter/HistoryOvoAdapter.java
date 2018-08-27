package framework.jimmy.com.framework.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.DealOvoKategori;
import framework.jimmy.com.framework.model.HistoryOvoDetail;


public class HistoryOvoAdapter extends RecyclerView.Adapter<HistoryOvoAdapter.HistoryOvoViewHolder> {
    private static final DateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
    private Context mContext;
    private List<HistoryOvoDetail> listHistoryOvoDetail;
    private OnClickRecyclerViewListener listener;
private Date lastDate;


    public class HistoryOvoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_history_title)
        public TextView txtHistoryTitle;

        @BindView(R.id.txt_history_sub_title)
        public TextView txtHistorySubTitle;

        @BindView(R.id.txt_history_value)
        public TextView txtHistoryValue;

        @BindView(R.id.txt_history_date)
        public TextView txtHistoryDate;

        @BindView(R.id.divider)
        public View divider;

        public HistoryOvoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            listener.onRowClicked(getAdapterPosition());
        }
    }


    public HistoryOvoAdapter(Context mContext, OnClickRecyclerViewListener listener) {
        this.mContext = mContext;
        //this.listHistoryOvoDetail = listHistoryOvoDetail;
        this.listener = listener;
    }

    @Override
    public HistoryOvoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_history_ovo, parent, false);

        return new HistoryOvoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HistoryOvoViewHolder holder, final int position) {
        if (listHistoryOvoDetail == null) {
            return;
        }
        HistoryOvoDetail historyOvoDetail = listHistoryOvoDetail.get(position);
        if(position-1>=0 && listHistoryOvoDetail.get(position).getDate().getDate() ==  listHistoryOvoDetail.get(position - 1).getDate().getDate())
        {
            holder.divider.setVisibility(View.INVISIBLE);
            holder.txtHistoryDate.setVisibility(View.GONE);

        }
        else
        {
            holder.txtHistoryDate.setVisibility(View.VISIBLE);
            holder.txtHistoryDate.setText(sdf.format(historyOvoDetail.getDate().getTime()).toUpperCase());

        }

        if((position + 1 < listHistoryOvoDetail.size()
                && listHistoryOvoDetail.get(position).getDate().getDate() ==  listHistoryOvoDetail.get(position + 1).getDate().getDate())
                || position + 1 == listHistoryOvoDetail.size())
        {
            holder.divider.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.divider.setVisibility(View.GONE);
        }

        holder.txtHistoryTitle.setText(historyOvoDetail.getTitle());
        holder.txtHistorySubTitle.setText(historyOvoDetail.getSubTitle());
        String history_value = "";
        if(historyOvoDetail.getIsPlus() == true)
        {
            history_value += "+";
        }
        else
        {
            history_value += "-";
        }
        if (historyOvoDetail.getCashOvo() != 0)
        {
            history_value += "Rp" + NumberFormat.getNumberInstance(Locale.getDefault()).format(historyOvoDetail.getCashOvo());
            holder.txtHistoryValue.setTextColor(Color.GREEN);
        }
        else
        {
            history_value += "OVO Points "+NumberFormat.getNumberInstance(Locale.getDefault()).format(historyOvoDetail.getPointOvo());
            holder.txtHistoryValue.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }
        holder.txtHistoryValue.setText(history_value);
    }






    @Override
    public int getItemCount() {
        if (listHistoryOvoDetail != null)
            return listHistoryOvoDetail.size();
        else return 0;
    }


    public void setListHistoryOvoDetail(List<HistoryOvoDetail> listHistoryOvoDetail){
        this.listHistoryOvoDetail = listHistoryOvoDetail;
        notifyDataSetChanged();
    }

    public HistoryOvoDetail getItemListHistoryOvoDetail(int position){
        return this.listHistoryOvoDetail.get(position);
    }

}