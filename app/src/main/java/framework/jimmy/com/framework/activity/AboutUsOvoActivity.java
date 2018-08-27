package framework.jimmy.com.framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.helper.HelperConvert;


public class AboutUsOvoActivity extends AppCompatActivity {


    private Toolbar toolbar;

    @BindView(R.id.lbl_about_us_ovo)
    TextView lblAboutUsOvo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_ovo);
        ButterKnife.bind(this);
        setTitle("ABOUT US");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();

    }

    private void initValue()
    {
        lblAboutUsOvo.setText(HelperConvert.convertFromHtml("OVO merupakan sarana digital untuk memudahkan hidup Anda dengan menyediakan beragam penawaran menarik melalui berbagai <i>merchant</i> rekanan, pembayaran yang mudah, dan layanan finansial yang cerdas."));
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