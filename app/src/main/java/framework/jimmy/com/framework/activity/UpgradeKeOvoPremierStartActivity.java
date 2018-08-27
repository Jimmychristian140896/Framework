package framework.jimmy.com.framework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.R;


public class UpgradeKeOvoPremierStartActivity extends AppCompatActivity {


    @OnClick(R.id.btn_mulai)
    public void onClickBtnMulai()
    {
        startActivity(new Intent(this, CameraKtpActivity.class));
    }
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_ke_ovo_premier_start);
        ButterKnife.bind(this);
        setTitle("UPGRADE KE OVO PREMIER");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();

    }

    private void initValue()
    {

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