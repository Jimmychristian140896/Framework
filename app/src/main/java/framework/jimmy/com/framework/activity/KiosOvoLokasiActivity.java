package framework.jimmy.com.framework.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.adapter.LokasiKiosOvoAdapter;
import framework.jimmy.com.framework.adapter.LokasiKiosOvoCheckedAdapter;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.LokasiKiosOvo;


public class KiosOvoLokasiActivity extends AppCompatActivity {

    LokasiKiosOvo selectedKiosOvo;
    private Toolbar toolbar;

    @BindView(R.id.recycler_view_lokasi_ovo)
    RecyclerView recyclerViewLokasiOvo;

    LokasiKiosOvoCheckedAdapter lokasiKiosOvoAdapter;
    List<LokasiKiosOvo> listLokasiKiosOvo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kios_ovo_lokasi);
        ButterKnife.bind(this);
        setTitle("Pilih Lokasi");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();
        setupRecyclerViewLokasiKiosOvo();

    }

    public void setupRecyclerViewLokasiKiosOvo() {
        recyclerViewLokasiOvo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        selectedKiosOvo = (LokasiKiosOvo) getIntent().getSerializableExtra("selected_lokasi");
        lokasiKiosOvoAdapter = new LokasiKiosOvoCheckedAdapter(this, listLokasiKiosOvo, selectedKiosOvo, new OnClickRecyclerViewListener() {
            @Override
            public void onRowClicked(int position) {
                Intent intentData = new Intent();
                intentData.putExtra("selected_lokasi", listLokasiKiosOvo.get(position));
                setResult(0, intentData);
                finish();
            }


        });
        recyclerViewLokasiOvo.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLokasiOvo.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewLokasiOvo.setAdapter(lokasiKiosOvoAdapter);
        recyclerViewLokasiOvo.setNestedScrollingEnabled(false);
    }

    private void initValue() {
        LokasiKiosOvo lokasiKiosOvo = new LokasiKiosOvo("Siloam Hospitals Lippo Village", "Siloam Hospital Lippo Village, Lt.2, Jl. Siloam No.6, Lippo Karawaci 1600, Tangerang 15811", "Mon - Sat 07.00 AM - 09.00 PM", "1500696", 106.772511955351, -6.18771120874929);
        listLokasiKiosOvo.add(lokasiKiosOvo);

        LokasiKiosOvo lokasiKiosOvo1 = new LokasiKiosOvo("Supermall Karawaci", "Matahari Dept Store Area lt LG, Jl. Boulevar Diponegoro No. 105, Kelapa Dua 15911 Tangerang", "Mon - Sat 10.00 AM - 10.00 PM", "1500696", 106.782511955351, -6.28771120874929);
        listLokasiKiosOvo.add(lokasiKiosOvo1);

        LokasiKiosOvo lokasiKiosOvo2 = new LokasiKiosOvo("Lippo Mall Kemang", "Lantai LG, Jl. Pangeran Antasari No. 36, Mampang Prapatan jakarta Selatan 12150", "Mon - Sat 07.00 AM - 09.00 PM", "1500696", 106.792511955351, -6.38771120874929);
        listLokasiKiosOvo.add(lokasiKiosOvo2);

        LokasiKiosOvo lokasiKiosOvo3 = new LokasiKiosOvo("MaxxBox Lippo Village", "Lantai G, Jl. Boulevard jend Sudirman No. 1110, Lippo Village 1200 Tangerang 15811", "Mon - Sat 07.00 AM - 09.00 PM", "1500696", 106.702511955351, -6.48771120874929);
        listLokasiKiosOvo.add(lokasiKiosOvo3);



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
