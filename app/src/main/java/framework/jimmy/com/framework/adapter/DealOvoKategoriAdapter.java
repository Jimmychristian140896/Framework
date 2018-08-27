package framework.jimmy.com.framework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.DealOvoKategori;


public class DealOvoKategoriAdapter extends RecyclerView.Adapter<DealOvoKategoriAdapter.DealOvoKategoriViewHolder> {
    private Context mContext;
    private List<DealOvoKategori> listOvoKategori;
    private OnClickRecyclerViewListener listener;



    public class DealOvoKategoriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

@BindView(R.id.txt_kategori_nama)
        public TextView txtKategoriNama;
        public DealOvoKategoriViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            listener.onRowClicked(getAdapterPosition());
        }
    }


    public DealOvoKategoriAdapter(Context mContext, List<DealOvoKategori> listOvoKategori, OnClickRecyclerViewListener listener) {
        this.mContext = mContext;
        this.listOvoKategori = listOvoKategori;
        this.listener = listener;
    }

    @Override
    public DealOvoKategoriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_deal_ovo_kategori, parent, false);

        return new DealOvoKategoriViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DealOvoKategoriViewHolder holder, final int position) {
        DealOvoKategori dealOvoKategori = listOvoKategori.get(position);
        holder.txtKategoriNama.setText(dealOvoKategori.getKategori_name());
    }






    @Override
    public int getItemCount() {
        return listOvoKategori.size();
    }




}