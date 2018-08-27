package framework.jimmy.com.framework.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.KategoriPengeluaran;
import framework.jimmy.com.framework.model.LokasiKiosOvo;


public class KategoriPengeluaranAdapter extends RecyclerView.Adapter<KategoriPengeluaranAdapter.KategoriPengeluaranViewHolder> {
    private Context mContext;
    private List<KategoriPengeluaran> listKategoriPengeluaran;
    private OnClickRecyclerViewListener listener;
    private String selectedKategoriPengeluaran;


    public class KategoriPengeluaranViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_kategori_pengeluaran)
        public TextView txtKategoriPengeluaran;

        @BindView(R.id.img_kategori_pengeluaran)
        public ImageView imgKategoriPengeluaran;





        public KategoriPengeluaranViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            listener.onRowClicked(getAdapterPosition());
        }
    }


    public KategoriPengeluaranAdapter(Context mContext, List<KategoriPengeluaran> listKategoriPengeluaran, String selectedKategoriPengeluaran, OnClickRecyclerViewListener listener) {
        this.mContext = mContext;
        this.listKategoriPengeluaran = listKategoriPengeluaran;
        this.listener = listener;
        this.selectedKategoriPengeluaran =selectedKategoriPengeluaran;
    }

    @Override
    public KategoriPengeluaranViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_kategori_pengeluaran, parent, false);

        return new KategoriPengeluaranViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final KategoriPengeluaranViewHolder holder, final int position) {
        KategoriPengeluaran kategoriPengeluaran = listKategoriPengeluaran.get(position);
        holder.txtKategoriPengeluaran.setText(kategoriPengeluaran.getNamaKategori());
        holder.imgKategoriPengeluaran.setImageResource(kategoriPengeluaran.getResourceKategori());
        if(selectedKategoriPengeluaran != "" && selectedKategoriPengeluaran.equalsIgnoreCase(kategoriPengeluaran.getNamaKategori()))
        {
            holder.imgKategoriPengeluaran.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        else
        {
            holder.imgKategoriPengeluaran.setColorFilter(null);
        }

    }






    @Override
    public int getItemCount() {
        return listKategoriPengeluaran.size();
    }





}