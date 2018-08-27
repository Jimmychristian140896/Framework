package framework.jimmy.com.framework.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.activity.DetailTransaksiKeluarOvoActivity;
import framework.jimmy.com.framework.activity.DetailTransaksiOvoActivity;
import framework.jimmy.com.framework.activity.NotificationOvoActivity;
import framework.jimmy.com.framework.adapter.DealOvoPromoAdapter;
import framework.jimmy.com.framework.adapter.HistoryOvoAdapter;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.HistoryOvoDetail;
import framework.jimmy.com.framework.util.DividerItemDecoration;
import framework.jimmy.com.framework.util.MyDividerItemDecoration;
import framework.jimmy.com.framework.viewmodel.FrameworkViewModel;


public class HistoryOvoFragment extends Fragment {

    private FrameworkViewModel frameworkViewModel;

    @BindView(R.id.recycler_view_history)
    RecyclerView recyclerViewHistory;

    @OnClick(R.id.fab_add_topic)
    public void onClickFabAddTopic()
    {

        Calendar calendar = Calendar.getInstance();
        //calendar.set(2018, 7,17);

        Date date = new Date(calendar.getTimeInMillis());
        // Required empty public constructor
        HistoryOvoDetail historyOvoDetail1 = new HistoryOvoDetail(date, "Informa", "Payment", false, 0, 100);

        frameworkViewModel.insert(historyOvoDetail1);
    }

    HistoryOvoAdapter historyOvoAdapter;
    List<HistoryOvoDetail> listHistoryOvoDetail = new ArrayList<>();

    public HistoryOvoFragment() {
        /*Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 7,17);
        // Required empty public constructor
        HistoryOvoDetail historyOvoDetail1 = new HistoryOvoDetail(calendar, "Informa", "Payment", false, 0, 100);
        listHistoryOvoDetail.add(historyOvoDetail1);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2018, 7,17);
        HistoryOvoDetail historyOvoDetail2 = new HistoryOvoDetail(calendar1, "DCost", "Payment", false, 1000, 0);
        listHistoryOvoDetail.add(historyOvoDetail2);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2018, 7,18);
        HistoryOvoDetail historyOvoDetail3 = new HistoryOvoDetail(calendar2, "DCost", "Payment", false, 0, 1000);
        listHistoryOvoDetail.add(historyOvoDetail3);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2018, 7,18);
        HistoryOvoDetail historyOvoDetail4 = new HistoryOvoDetail(calendar3, "OVO Project Management", "Top Up", true, 0, 1000000);
        listHistoryOvoDetail.add(historyOvoDetail4);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2018, 7,19);
        HistoryOvoDetail historyOvoDetail5 = new HistoryOvoDetail(calendar4, "OVO Project Management", "Point Injection", true, 10000000, 0);
        listHistoryOvoDetail.add(historyOvoDetail5);*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frameworkViewModel = ViewModelProviders.of(this).get(FrameworkViewModel.class);
        frameworkViewModel.getAllHistory().observe(this, new Observer<List<HistoryOvoDetail>>() {
            @Override
            public void onChanged(@Nullable final List<HistoryOvoDetail> listHistoryOvoDetail) {
                // Update the cached copy of the words in the adapter.
                historyOvoAdapter.setListHistoryOvoDetail(listHistoryOvoDetail);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_ovo, container, false);
        ButterKnife.bind(this,v);
        setupRecyclerViewHistory();
        return v;
    }

    public void setupRecyclerViewHistory()
    {
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        historyOvoAdapter = new HistoryOvoAdapter(this.getContext(),  new OnClickRecyclerViewListener() {
            @Override
            public void onRowClicked(int position) {
                HistoryOvoDetail historyOvoDetail = historyOvoAdapter.getItemListHistoryOvoDetail(position);
                if(historyOvoDetail.getIsPlus())
                {
                    startActivity(new Intent(getContext(), DetailTransaksiOvoActivity.class));
                }
                else
                {
                    startActivity(new Intent(getContext(), DetailTransaksiKeluarOvoActivity.class));
                }

            }
        });
        recyclerViewHistory.setItemAnimator(new DefaultItemAnimator());
        //recyclerViewHistory.addItemDecoration(new MyDividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL, 16));
        recyclerViewHistory.setAdapter(historyOvoAdapter);
    }
}