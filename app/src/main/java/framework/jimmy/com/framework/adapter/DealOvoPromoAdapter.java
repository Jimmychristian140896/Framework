package framework.jimmy.com.framework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.Message;



public class DealOvoPromoAdapter extends RecyclerView.Adapter<DealOvoPromoAdapter.DealOvoPromoViewHolder> {
    private Context mContext;
    private List<String> listPromo;
    private OnClickRecyclerViewListener listener;



    public class DealOvoPromoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public DealOvoPromoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            listener.onRowClicked(getAdapterPosition());
        }
    }


    public DealOvoPromoAdapter(Context mContext, List<String> listPromo, OnClickRecyclerViewListener listener) {
        this.mContext = mContext;
        this.listPromo = listPromo;
        this.listener = listener;
    }

    @Override
    public DealOvoPromoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_deal_ovo_promo, parent, false);

        return new DealOvoPromoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DealOvoPromoViewHolder holder, final int position) {
        String promo = listPromo.get(position);
    }






    @Override
    public int getItemCount() {
        return listPromo.size();
    }




}
