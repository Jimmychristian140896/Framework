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
import framework.jimmy.com.framework.adapter.NotificationOvoAdapter;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.MessageEvent;
import framework.jimmy.com.framework.model.NotificationOvo;


public class NotificationOvoActivity extends AppCompatActivity {


    private Toolbar toolbar;

    @BindView(R.id.recycler_view_notification)
    RecyclerView recyclerViewNotification;

    NotificationOvoAdapter notificationOvoAdapter;
    List<NotificationOvo> listNotificationOvoDetail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        setTitle("NOTIFICATION");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();
        setupRecyclerViewNotification();

    }

    public void setupRecyclerViewNotification() {
        recyclerViewNotification.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        notificationOvoAdapter = new NotificationOvoAdapter(this, listNotificationOvoDetail, new OnClickRecyclerViewListener() {
            @Override
            public void onRowClicked(int position) {
                listNotificationOvoDetail.get(position).setRead(true);
                notificationOvoAdapter.notifyItemChanged(position);
                if (listNotificationOvoDetail.get(position).getReference() == "true") {
                    startActivity(new Intent(NotificationOvoActivity.this, DetailTransaksiOvoActivity.class));
                }
            }
        });
        recyclerViewNotification.setItemAnimator(new DefaultItemAnimator());
        //recyclerViewHistory.addItemDecoration(new MyDividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL, 16));
        recyclerViewNotification.setAdapter(notificationOvoAdapter);
    }

    private void initValue() {
        EventBus.getDefault().post(new MessageEvent(this.getClass().getSimpleName()));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 7, 17);
        // Required empty public constructor
        NotificationOvo notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);


        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2018, 7, 18);
        NotificationOvo notificationOvo1 = new NotificationOvo(calendar, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);

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
