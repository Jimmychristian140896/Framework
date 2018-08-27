package framework.jimmy.com.framework.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.R;


public class UpgradeKeOvoPremierKtpActivity extends AppCompatActivity {


    @OnClick(R.id.btn_gunakan)
    public void onClickBtnGunakan()
    {
        startActivity(new Intent(this, UpgradeKeOvoPremierLokasiActivity.class));
    }

    @OnClick(R.id.lbl_ulangi)
    public void onClickLblUlangi()
    {
        finish();
    }

    @BindView(R.id.img_ktp)
    ImageView imgKtp;

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_ke_ovo_premier_ktp);
        ButterKnife.bind(this);
        setTitle("UPGRADE KE OVO PREMIER");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();

    }

    private void initValue()
    {
        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("image_ktp");
        if(bitmap != null) {
            imgKtp.setImageBitmap(bitmap);
        }
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