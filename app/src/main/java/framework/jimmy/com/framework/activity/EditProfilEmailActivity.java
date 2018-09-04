package framework.jimmy.com.framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.model.MessageEvent;
import framework.jimmy.com.framework.util.BadgeUtil;
import me.leolin.shortcutbadger.ShortcutBadger;


public class EditProfilEmailActivity extends AppCompatActivity {


    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil_email_ovo);
        ButterKnife.bind(this);
        setTitle("EDIT PROFIL");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();

    }

    private void initValue()
    {
        EventBus.getDefault().postSticky(new MessageEvent(this.getClass().getSimpleName()));
        int badgeCount = 1;
        BadgeUtil.showIconBadge(getApplicationContext(), badgeCount);
        //ShortcutBadger.applyCount(getApplicationContext(), badgeCount); //for 1.1.4+
        //ShortcutBadger.with(getApplicationContext()).count(badgeCount); //for 1.1.3
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