package framework.jimmy.com.framework.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.FrameworkApplication;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.fragment.DealsOvoFragment;
import framework.jimmy.com.framework.fragment.FinanceOvoFragment;
import framework.jimmy.com.framework.fragment.HistoryOvoFragment;
import framework.jimmy.com.framework.fragment.HomeNewOvoFragment;
import framework.jimmy.com.framework.fragment.HomeOvoFragment;
import framework.jimmy.com.framework.fragment.WalletOvoFragment;
import framework.jimmy.com.framework.model.MessageEvent;
import framework.jimmy.com.framework.util.DateUtil;
import io.fabric.sdk.android.Fabric;
import retrofit2.Retrofit;

public class MainOvoActivity extends AppCompatActivity {
    @Inject
    Retrofit retrofit;
    @BindView(R.id.editSearch)
    EditText editSearch;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_ovo_24dp,
            R.drawable.ic_deals_black_24dp,
            R.drawable.ic_finance_black_24dp,
            R.drawable.ic_account_balance_wallet_black_24dp,
            R.drawable.ic_history_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.layout_activity_main_ovo);


        FrameworkApplication.getInstance().getNetComponent().inject(this);
        ButterKnife.bind(this);

        setTitle("OVO");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        Retrofit retrofit1 = retrofit;
        logUser();
        initValue();
    }

    public void initValue()
    {
        editSearch.setText(DateUtil.toString(DateUtil.toDate("2018-08-20")));
    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }


    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_main_ovo, null);
        tabOne.setText("Home");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_ovo_24dp, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_main_ovo, null);
        tabTwo.setText("Deals");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_deals_black_24dp, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_main_ovo, null);
        tabThree.setText("Finance");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_finance_black_24dp, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_main_ovo, null);
        tabFour.setText("Wallet");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_account_balance_wallet_black_24dp, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);

        TextView tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_main_ovo, null);
        tabFive.setText("History");
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_history_black_24dp, 0, 0);
        tabLayout.getTabAt(4).setCustomView(tabFive);

        TextView tabSix = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_main_ovo, null);
        tabSix.setText("Home");
        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_ovo_24dp, 0, 0);
        tabLayout.getTabAt(5).setCustomView(tabSix);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeOvoFragment(), "Home");
        adapter.addFragment(new DealsOvoFragment(), "Deals");
        adapter.addFragment(new FinanceOvoFragment(), "Finance");
        adapter.addFragment(new WalletOvoFragment(), "Wallet");
        adapter.addFragment(new HistoryOvoFragment(), "History");
        adapter.addFragment(new HomeNewOvoFragment(), "Home");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_ovo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_notification:
                startActivity(new Intent(this, NotificationOvoActivity.class));
                return true;
            case R.id.action_setting:
                startActivity(new Intent(this, SettingOvoActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void switchTab(int index)
    {
        tabLayout.getTabAt(index).select();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        editSearch.setText( "Hey, my message" + event.getMessage());
        Toast.makeText(this, "Hey, my message" + event.getMessage(), Toast.LENGTH_LONG).show();

    }
}
