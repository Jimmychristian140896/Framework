package framework.jimmy.com.framework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.adapter.InstruksiOvoAdapter;
import framework.jimmy.com.framework.adapter.NotificationOvoAdapter;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.InstruksiOvo;
import framework.jimmy.com.framework.model.MessageEvent;
import framework.jimmy.com.framework.model.NotificationOvo;


public class TopUpOvoActivity extends AppCompatActivity {


    private Toolbar toolbar;

    @BindView(R.id.recycler_view_instruksi)
    RecyclerView recyclerViewInstruksi;

    InstruksiOvoAdapter instruksiOvoAdapter;
    List<InstruksiOvo> listInstruksiOvo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_ovo);
        ButterKnife.bind(this);
        setTitle("TOP UP");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();
        setupRecyclerViewNotification();

    }

    public void setupRecyclerViewNotification() {
        recyclerViewInstruksi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        instruksiOvoAdapter = new InstruksiOvoAdapter(this, listInstruksiOvo, new OnClickRecyclerViewListener() {
            @Override
            public void onRowClicked(int position) {

            }
        });
        recyclerViewInstruksi.setItemAnimator(new DefaultItemAnimator());
        //recyclerViewHistory.addItemDecoration(new MyDividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL, 16));
        recyclerViewInstruksi.setAdapter(instruksiOvoAdapter);
    }

    private void initValue() {
        InstruksiOvo instruksiOvo = new InstruksiOvo("Kunjungi jaringan <b>ATM Bersama / Prima</b>", "1", "2", "", false);
        listInstruksiOvo.add(instruksiOvo);

        InstruksiOvo instruksiOvo1 = new InstruksiOvo("Pilih menu <b>Transfer</b>", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo1);

        InstruksiOvo instruksiOvo2 = new InstruksiOvo("Pilih <b>Bank Nobu</b> atau masukkan kode bank <b>503</b>", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo2);

        InstruksiOvo instruksiOvo3 = new InstruksiOvo("Masukkan <b>9 + nomor telepon Anda</b>", "", "", "<b>9 - 0896 - 4900 - 4169</b>", true);
        listInstruksiOvo.add(instruksiOvo3);

        InstruksiOvo instruksiOvo4 = new InstruksiOvo("Masukkan <b>Nominal Isi Ulang</b>", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo4);


        /*
        InstruksiOvo instruksiOvo = new InstruksiOvo("Pilih menu <b>Transaksi Lain</b>", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo);

        InstruksiOvo instruksiOvo1 = new InstruksiOvo("Pilih menu <b>Isi Ulang</b>", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo1);

        InstruksiOvo instruksiOvo2 = new InstruksiOvo("Pilih menu <b>OVO</b>", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo2);

        InstruksiOvo instruksiOvo3 = new InstruksiOvo("Masukkan <b>Nomor Ponsel yang terdaftar pada aplikasi OVO</b>", "", "", "(Contoh: 08XX XXXX XXXX)", false);
        listInstruksiOvo.add(instruksiOvo3);

        InstruksiOvo instruksiOvo4 = new InstruksiOvo("Masukkan <b>Nominal Isi Ulang</b>", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo4);

        InstruksiOvo instruksiOvo5 = new InstruksiOvo("Konfirmasi", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo5);*/

        /*
        InstruksiOvo instruksiOvo = new InstruksiOvo("Kunjungi Merchant berlogo <b>OVO Zone</b>", "1", "2", "", false);
        listInstruksiOvo.add(instruksiOvo);

        InstruksiOvo instruksiOvo1 = new InstruksiOvo("Informasikan pada Kasir nominal <b>Isi Saldo Ovo Cash anda</b>", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo1);

        InstruksiOvo instruksiOvo2 = new InstruksiOvo("Berikan tunai sesuai nominal Isi Saldo pada Kasir", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo2);

        InstruksiOvo instruksiOvo3 = new InstruksiOvo("Informasikan nomor ponsel yang terdaftar pada OVO <b>(Contoh: 08xx xxxx xxxx)</b>", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo3);

        InstruksiOvo instruksiOvo4 = new InstruksiOvo("Anda akan menerima notifikasi Isi Saldo Ovo Cash telah berhasil", "", "", "", false);
        listInstruksiOvo.add(instruksiOvo4);

*/
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
