package framework.jimmy.com.framework.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.FlexibleItemDecoration;
import eu.davidea.flexibleadapter.helpers.EmptyViewHelper;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.utils.Log;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.adapter.HeaderItem;
import framework.jimmy.com.framework.adapter.MyFlexibleItem;
import framework.jimmy.com.framework.adapter.ProgressItem;
import framework.jimmy.com.framework.model.NotificationOvo;

public class FlexibleActivity extends AppCompatActivity implements FlexibleAdapter.EndlessScrollListener, EmptyViewHelper.OnEmptyViewListener {
    public static final String TAG = FlexibleActivity.class.getSimpleName();
    private FlexibleAdapter<AbstractFlexibleItem> mAdapter;
    RecyclerView mRecyclerView;

    ArrayList<AbstractFlexibleItem> items;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_history_ovo);

        FlexibleAdapter.enableLogs(Log.Level.DEBUG); // Default is Level.SUPPRESS

        FlexibleAdapter.useTag("MenuAdapter");

        items = new ArrayList<AbstractFlexibleItem>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 7, 17);
        NotificationOvo notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");

        // Simulating success/failure
        //int totalItemsOfType = mAdapter.getItemCountOfTypes(R.layout.model);
        HeaderItem headerItem = new HeaderItem(calendar.getTime().toLocaleString());
        for (int i = 1; i <= 15; i++) {

            if(i==7)
            {
                calendar.add(Calendar.DAY_OF_MONTH,1);
                headerItem = new HeaderItem(calendar.getTime().toLocaleString());
            }
            items.add(new MyFlexibleItem(notificationOvo, headerItem));
        }
        /*FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(items);
        adapter.expandItemsAtStartUp()
                .setAutoCollapseOnExpand(true)
                .setMinCollapsibleLevel(1)
                .setAutoScrollOnExpand(true)
                .setAnimationOnForwardScrolling(true)
                .setAnimationOnReverseScrolling(true);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_history);
        recyclerView.setAdapter(adapter);

        adapter.setLongPressDragEnabled(true)
                .setHandleDragEnabled(true)
                .setSwipeEnabled(true)
                .setDisplayHeadersAtStartUp(true)
                .setStickyHeaders(true)
                .setEndlessScrollThreshold(1);
*/

        mAdapter = new FlexibleAdapter<>(items, this, true);
        mAdapter.addListener(this)
                .setAnimationOnForwardScrolling(true)
                .setAnimationOnReverseScrolling(true);

        mRecyclerView = findViewById(R.id.recycler_view_history);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new FlexibleItemDecoration(this).withSectionGapOffset(24));

        EmptyViewHelper.create(mAdapter, findViewById(R.id.txt_empty_notes_view));

        mAdapter.setDisplayHeadersAtStartUp(true)
                .setStickyHeaders(true)
                .setLoadingMoreAtStartUp(true)
                .setEndlessScrollListener(this, new ProgressItem())
                .setEndlessScrollThreshold(1);


    }

    public List<IFlexible> getDatabaseList() {
        List<IFlexible> list = new ArrayList<>();
        //list.add(new MyFlexibleItem("1", "Hello"));
        //list.add(new MyFlexibleItem("2", "World"));
        return list;
    }

    @Override
    public void noMoreLoad(int newItemsSize) {

    }

    @Override
    public void onLoadMore(int lastPosition, int currentPage) {
        new Handler().postDelayed(new Runnable() {
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                final List<AbstractFlexibleItem> newItems = new ArrayList<AbstractFlexibleItem>();
                Calendar calendar = Calendar.getInstance();
                calendar.set(2019, 7, 17);
                NotificationOvo notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");

                // Simulating success/failure
                //int totalItemsOfType = mAdapter.getItemCountOfTypes(R.layout.model);
                HeaderItem headerItem = new HeaderItem(calendar.getTime().toLocaleString());
                for (int i = 1; i <= 5; i++) {

                    if(i==7)
                    {
                        calendar.add(Calendar.DAY_OF_MONTH,1);
                        headerItem = new HeaderItem(calendar.getTime().toLocaleString());
                    }
                    newItems.add(new MyFlexibleItem(notificationOvo, headerItem));
                }
mAdapter.addItems(mAdapter.getItemCount(), newItems);
               /* Calendar calendar = Calendar.getInstance();
                calendar.set(2018, 7, 17);
                NotificationOvo notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");

                // Simulating success/failure
                //int totalItemsOfType = mAdapter.getItemCountOfTypes(R.layout.model);
                for (int i = 1; i <= 3; i++) {
                    newItems.add(new MyFlexibleItem(notificationOvo, new HeaderItem(calendar.getTime().toLocaleString())));
                }
*/
                // Callback the Adapter to notify the change
                // Items will be added to the end of the main list

                Log.d(TAG, "newItemsSize=" + newItems.size());
                Log.d(TAG, "EndlessCurrentPage=" + mAdapter.getEndlessCurrentPage());
                Log.d(TAG, "EndlessPageSize=" + mAdapter.getEndlessPageSize());
                Log.d(TAG, "EndlessTargetCount=" + mAdapter.getEndlessTargetCount());
//                mAdapter.onLoadMoreComplete(newItems);


                // Notify user

            }
        }, 3000);
    }

    @Override
    public void onUpdateEmptyDataView(int size) {

    }

    @Override
    public void onUpdateEmptyFilterView(int size) {

    }
}
