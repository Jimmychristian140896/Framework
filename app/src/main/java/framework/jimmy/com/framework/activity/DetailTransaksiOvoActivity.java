package framework.jimmy.com.framework.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.adapter.NotificationOvoAdapter;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.NotificationOvo;


public class DetailTransaksiOvoActivity extends AppCompatActivity {


    private Toolbar toolbar;

    @BindView(R.id.txt_no_referensi)
    EditText txtNoReferensi;

    @BindView(R.id.txt_tanggal_waktu)
    EditText txtTanggalWaktu;

    @BindView(R.id.txt_dari)
    EditText txtDari;

    @BindView(R.id.txt_pesan)
    EditText txtPesan;



    @BindView(R.id.img_detail_transaksi_header)
    ImageView imgDetailTransaksiHeader;

    @BindView(R.id.txt_detail_transaksi_title)
    TextView txtDetailTransaksiTitle;

    @BindView(R.id.txt_detail_transaksi_sub_title)
    TextView txtDetailTransaksiSubTitle;

    @BindView(R.id.txt_detail_transaksi_value)
    TextView txtDetailTransaksiValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi_ovo);
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
        txtDari.setEnabled(false);
        txtPesan.setEnabled(false);
        txtNoReferensi.setFocusable(false);
        txtTanggalWaktu.setFocusable(false);
        txtDari.setFocusable(false);
        txtPesan.setFocusable(false);
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

}