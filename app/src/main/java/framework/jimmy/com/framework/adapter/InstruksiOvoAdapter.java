package framework.jimmy.com.framework.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.helper.HelperApp;
import framework.jimmy.com.framework.helper.HelperConvert;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.InstruksiOvo;
import framework.jimmy.com.framework.util.MySnackbar;


public class InstruksiOvoAdapter extends RecyclerView.Adapter<InstruksiOvoAdapter.InstruksiOvoViewHolder> {
    private Context mContext;
    private List<InstruksiOvo> listInstruksiOvo;
    private OnClickRecyclerViewListener listener;



    public class InstruksiOvoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.timeline_instruksi)
        public TimelineView timelineInstruksi;
        @BindView(R.id.txt_instruksi)
        public TextView txtInstruksi;

        @BindView(R.id.txt_sub_instruksi)
        public TextView txtSubInstruksi;

        @BindView(R.id.txt_salin)
        public TextView txtSalin;

        @BindView(R.id.layout_image)
        public LinearLayout layoutImage;

        @BindView(R.id.img_2)
        public ImageView img2;

        @BindView(R.id.img_1)
        public ImageView img1;


        public InstruksiOvoViewHolder(View view, int viewType) {
            super(view);
            ButterKnife.bind(this, view);
            timelineInstruksi.initLine(viewType);
            view.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            listener.onRowClicked(getAdapterPosition());
        }
    }


    public InstruksiOvoAdapter(Context mContext, List<InstruksiOvo> listInstruksiOvo, OnClickRecyclerViewListener listener) {
        this.mContext = mContext;
        this.listInstruksiOvo = listInstruksiOvo;
        this.listener = listener;
    }

    @Override
    public InstruksiOvoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_instruksi, parent, false);

        final InstruksiOvoViewHolder holder = new InstruksiOvoViewHolder(itemView, viewType);
        holder.txtSalin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperApp.copyToClipboard(mContext, "Copy", listInstruksiOvo.get(holder.getLayoutPosition()).getSubInstruksi());
                /*Snackbar snackbar = Snackbar.make(view,"Disalin ke Clipboard", Snackbar.LENGTH_SHORT);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                snackbar.show();*/

                MySnackbar.showSnackbarColored(mContext, view, "Disalin ke Clipboard", Snackbar.LENGTH_SHORT);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final InstruksiOvoViewHolder holder, final int position) {
        final InstruksiOvo instruksiOvo = listInstruksiOvo.get(position);
        holder.txtInstruksi.setText(HelperConvert.convertFromHtml(instruksiOvo.getInstruksi()));
        if(instruksiOvo.getSubInstruksi().equalsIgnoreCase(""))
        {
            holder.txtSubInstruksi.setVisibility(View.GONE);
        }
        else
        {
            holder.txtSubInstruksi.setVisibility(View.VISIBLE);
            holder.txtSubInstruksi.setText(HelperConvert.convertFromHtml(instruksiOvo.getSubInstruksi()));
        }
        if(instruksiOvo.isShowSalin())
        {
            holder.txtSalin.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.txtSalin.setVisibility(View.GONE);
        }

        if(instruksiOvo.getUrlImage1().equalsIgnoreCase(""))
        {
            holder.img1.setVisibility(View.GONE);
        }
        else
        {
            holder.img1.setVisibility(View.VISIBLE);
        }

        if(instruksiOvo.getUrlImage2().equalsIgnoreCase(""))
        {
            holder.img2.setVisibility(View.GONE);
        }
        else
        {
            holder.img2.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }




    @Override
    public int getItemCount() {
        return listInstruksiOvo.size();
    }




}