package framework.jimmy.com.framework.adapter;

import android.content.Context;
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
import framework.jimmy.com.framework.model.LokasiKiosOvo;


public class LokasiKiosOvoCheckedAdapter extends RecyclerView.Adapter<LokasiKiosOvoCheckedAdapter.LokasiKiosOvoViewHolder> {
    private Context mContext;
    private List<LokasiKiosOvo> listLokasiKiosOvo;
    private OnClickRecyclerViewListener listener;
    private LokasiKiosOvo selectedKiosOvo;


    public class LokasiKiosOvoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_nama_lokasi_kios_ovo)
        public TextView txtNamaLokasiKiosOvo;

        @BindView(R.id.txt_alamat_lokasi_kios_ovo)
        public TextView txtAlamatLokasiKiosOvo;

        @BindView(R.id.txt_waktu_operasional_lokasi_kios_ovo)
        public TextView txtWaktuOperasionalLokasiKiosOvo;

        @BindView(R.id.img_checked)
        public ImageView imgChecked;



        public LokasiKiosOvoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            listener.onRowClicked(getAdapterPosition());
        }
    }


    public LokasiKiosOvoCheckedAdapter(Context mContext, List<LokasiKiosOvo> listLokasiKiosOvo, LokasiKiosOvo selectedKiosOvo, OnClickRecyclerViewListener listener) {
        this.mContext = mContext;
        this.listLokasiKiosOvo = listLokasiKiosOvo;
        this.listener = listener;
        this.selectedKiosOvo =selectedKiosOvo;
    }

    @Override
    public LokasiKiosOvoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_lokasi_kios_ovo_checked, parent, false);

        return new LokasiKiosOvoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final LokasiKiosOvoViewHolder holder, final int position) {
        LokasiKiosOvo lokasiKiosOvo = listLokasiKiosOvo.get(position);
        holder.txtNamaLokasiKiosOvo.setText(lokasiKiosOvo.getNamaLokasi());
        holder.txtAlamatLokasiKiosOvo.setText(lokasiKiosOvo.getAlamatLokasi());
        holder.txtWaktuOperasionalLokasiKiosOvo.setText("Jam : "+lokasiKiosOvo.getWaktuOperasionalLokasi());
        if(selectedKiosOvo!= null && lokasiKiosOvo.getNamaLokasi().equalsIgnoreCase(selectedKiosOvo.getNamaLokasi()))
        {
            holder.imgChecked.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.imgChecked.setVisibility(View.INVISIBLE);
        }

    }






    @Override
    public int getItemCount() {
        return listLokasiKiosOvo.size();
    }





}