package framework.jimmy.com.framework.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.adapter.KategoriPengeluaranAdapter;
import framework.jimmy.com.framework.helper.HelperDialog;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.KategoriPengeluaran;


public class DetailTransaksiKeluarOvoActivity extends AppCompatActivity {

    List<KategoriPengeluaran> listKategoriPengeluaran;
    private Toolbar toolbar;

    @BindView(R.id.txt_no_referensi)
    EditText txtNoReferensi;

    @BindView(R.id.txt_tanggal_waktu)
    EditText txtTanggalWaktu;

    @BindView(R.id.txt_ke)
    EditText txtKe;

    @BindView(R.id.txt_pesan)
    EditText txtPesan;

    @BindView(R.id.txt_kategori)
    TextView txtKategori;

    @BindView(R.id.img_atur_kategori_pengeluaran)
    ImageView imgAturKategoriPengeluaran;

    @BindView(R.id.img_detail_transaksi_header)
    ImageView imgDetailTransaksiHeader;

    @BindView(R.id.txt_detail_transaksi_title)
    TextView txtDetailTransaksiTitle;

    @BindView(R.id.txt_detail_transaksi_sub_title)
    TextView txtDetailTransaksiSubTitle;

    @BindView(R.id.txt_detail_transaksi_value)
    TextView txtDetailTransaksiValue;

    @OnClick(R.id.layout_edit_kategori_pengeluaran)
    public void onClickLayoutEditKategoriPengeluaran()
    {
        showDialogKategoriPicker(this, txtKategori.getText().toString());
    }

    @OnClick(R.id.btn_atur_budget)
    public void onClickAturBudget()
    {
        startActivity(new Intent(this, DetailBudgetOvoActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi_keluar_ovo);
        ButterKnife.bind(this);
        setTitle("DETAIL TRANSAKSI");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();

    }

    private void initValue()
    {
        txtDetailTransaksiTitle.setText("OVO Project Management");
        txtDetailTransaksiSubTitle.setText("Top Up");
        txtDetailTransaksiValue.setText("+Rp1.000.000");
        txtDetailTransaksiValue.setTextColor(Color.GREEN);
        txtPesan.setText("");
        txtNoReferensi.setEnabled(false);
        txtTanggalWaktu.setEnabled(false);
        txtKe.setEnabled(false);
        txtPesan.setEnabled(false);
        txtNoReferensi.setFocusable(false);
        txtTanggalWaktu.setFocusable(false);
        txtKe.setFocusable(false);
        txtPesan.setFocusable(false);

        txtKategori.setText("Others");

         listKategoriPengeluaran = new ArrayList<KategoriPengeluaran>();
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_account_balance_wallet_black_24dp,"F & B"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_add_black_24dp,"Transport"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_check_black_24dp,"Groceries"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_deals_black_24dp,"Shopping"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_expand_more_black_24dp,"Entertaiment"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_finance_black_24dp,"Utilities"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_history_black_24dp,"Personal"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_notifications_black_24dp,"Education"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_notifications_none_black_24dp,"Gift"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_ovo_24dp,"Cash"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_search_black_24dp,"Health"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_settings_applications_black_24dp,"Others"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDialogKategoriPicker(Activity activity, String selectedKategoriPengeluaran)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Pilih Kategori");
        View v = activity.getLayoutInflater().inflate(R.layout.dialog_kategori_picker, null);
        builder.setView(v);

        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view_kategori);


        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));




        final AlertDialog dialog = builder.create();
        KategoriPengeluaranAdapter adapter = new KategoriPengeluaranAdapter(activity, listKategoriPengeluaran, selectedKategoriPengeluaran, new OnClickRecyclerViewListener() {
            @Override
            public void onRowClicked(int position) {
                KategoriPengeluaran kategoriPengeluaran = listKategoriPengeluaran.get(position);
                txtKategori.setText(kategoriPengeluaran.getNamaKategori());
                imgAturKategoriPengeluaran.setImageResource(kategoriPengeluaran.getResourceKategori());
                dialog.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

}