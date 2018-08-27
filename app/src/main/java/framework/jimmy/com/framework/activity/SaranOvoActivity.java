package framework.jimmy.com.framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.helper.HelperConvert;


public class SaranOvoActivity extends AppCompatActivity {


    private Toolbar toolbar;

    @BindView(R.id.radio_button_saran)
    RadioButton radioButtonSaran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saran_ovo);
        ButterKnife.bind(this);
        setTitle("ABOUT US");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();

    }

    private void initValue()
    {
        radioButtonSaran.setChecked(true);
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