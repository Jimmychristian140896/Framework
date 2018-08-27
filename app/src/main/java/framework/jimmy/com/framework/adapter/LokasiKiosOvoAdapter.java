package framework.jimmy.com.framework.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import framework.jimmy.com.framework.model.DealOvoKategori;
import framework.jimmy.com.framework.model.LokasiKiosOvo;


public class LokasiKiosOvoAdapter extends RecyclerView.Adapter<LokasiKiosOvoAdapter.LokasiKiosOvoViewHolder> {
    private Context mContext;
    private List<LokasiKiosOvo> listLokasiKiosOvo;
    private OnClickRecyclerViewLocationListener listener;



    public class LokasiKiosOvoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_nama_lokasi_kios_ovo)
        public TextView txtNamaLokasiKiosOvo;

        @BindView(R.id.txt_alamat_lokasi_kios_ovo)
        public TextView txtAlamatLokasiKiosOvo;

        @BindView(R.id.txt_waktu_operasional_lokasi_kios_ovo)
        public TextView txtWaktuOperasionalLokasiKiosOvo;

        @BindView(R.id.img_location)
        public ImageView imgLocation;

        @BindView(R.id.img_call)
        public ImageView imgCall;


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


    public LokasiKiosOvoAdapter(Context mContext, List<LokasiKiosOvo> listLokasiKiosOvo, OnClickRecyclerViewLocationListener listener) {
        this.mContext = mContext;
        this.listLokasiKiosOvo = listLokasiKiosOvo;
        this.listener = listener;
    }

    @Override
    public LokasiKiosOvoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_lokasi_kios_ovo, parent, false);

        return new LokasiKiosOvoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final LokasiKiosOvoViewHolder holder, final int position) {
        LokasiKiosOvo lokasiKiosOvo = listLokasiKiosOvo.get(position);
        holder.txtNamaLokasiKiosOvo.setText(lokasiKiosOvo.getNamaLokasi());
        holder.txtAlamatLokasiKiosOvo.setText(lokasiKiosOvo.getAlamatLokasi());
        holder.txtWaktuOperasionalLokasiKiosOvo.setText("Jam : "+lokasiKiosOvo.getWaktuOperasionalLokasi());
        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onImgCallClicked(position);

            }
        });
        holder.imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onImgLocationClicked(position);
            }
        });
    }






    @Override
    public int getItemCount() {
        return listLokasiKiosOvo.size();
    }

    public interface OnClickRecyclerViewLocationListener
    {
        void onRowClicked(int position);
        void onImgCallClicked(int position);
        void onImgLocationClicked(int position);
    }



}