package framework.jimmy.com.framework.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.model.LokasiKiosOvo;
import framework.jimmy.com.framework.util.IntentUtil;

public class UpgradeKeOvoPremierLokasiActivity extends AppCompatActivity {

    public static int REQUEST_LOCATION = 0;
    LokasiKiosOvo selectedKiosOvo;

    @OnClick(R.id.txt_lokasi)
    public void onClickTxtLokasi()
    {
        Intent intent = new Intent(this, KiosOvoLokasiActivity.class);
        intent.putExtra("selected_lokasi", selectedKiosOvo) ;
        startActivityForResult(intent, REQUEST_LOCATION);
    }

    @OnClick(R.id.btn_kembali_ke_home)
    public void onClickBtnKembaliKeHome()
    {
        Intent intent = new Intent(getApplicationContext(), MainOvoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @BindView(R.id.btn_kembali_ke_home)
    Button btnKembaliKeHome;
    @BindView(R.id.txt_lokasi)
    EditText txtLokasi;

    @BindView(R.id.txt_nama_lokasi_kios_ovo)
    TextView txtNamaLokasiKiosOvo;

    @BindView(R.id.txt_alamat_lokasi_kios_ovo)
    TextView txtAlamatLokasiKiosOvo;

    @BindView(R.id.txt_waktu_operasional_lokasi_kios_ovo)
    TextView txtWaktuOperasionalLokasiKiosOvo;

    @BindView(R.id.layout_selected_lokasi)
    ViewGroup layoutSelectedLokasi;

    @OnClick(R.id.img_location)
    public void onClickImgLocation()
    {


        IntentUtil.mapNavigationIntent(this, selectedKiosOvo.getLatitudeLokasi(), selectedKiosOvo.getLongitudeLokasi() );
    }

    @OnClick(R.id.img_call)
    public void onClickImgCall()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", selectedKiosOvo.getNotelpLokasi(), null));
        startActivity(intent);

    }

    private Toolbar toolbar;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_LOCATION && resultCode == 0)
        {
            selectedKiosOvo = (LokasiKiosOvo)data.getSerializableExtra("selected_lokasi");
            txtLokasi.setText(selectedKiosOvo.getNamaLokasi());
            txtNamaLokasiKiosOvo.setText(selectedKiosOvo.getNamaLokasi());
            txtAlamatLokasiKiosOvo.setText(selectedKiosOvo.getAlamatLokasi());
            txtWaktuOperasionalLokasiKiosOvo.setText("Jam : "+selectedKiosOvo.getWaktuOperasionalLokasi());
            layoutSelectedLokasi.setVisibility(View.VISIBLE);
            btnKembaliKeHome.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_ke_ovo_premier_lokasi);
        ButterKnife.bind(this);
        setTitle("UPGRADE KE OVO PREMIER");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();

    }

    private void initValue()
    {
        layoutSelectedLokasi.setVisibility(View.INVISIBLE);
        btnKembaliKeHome.setVisibility(View.INVISIBLE);
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