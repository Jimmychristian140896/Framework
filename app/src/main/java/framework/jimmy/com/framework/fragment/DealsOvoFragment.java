package framework.jimmy.com.framework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.adapter.DealOvoKategoriAdapter;
import framework.jimmy.com.framework.adapter.DealOvoPromoAdapter;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.DealOvoKategori;


public class DealsOvoFragment extends Fragment {
    @BindView(R.id.recycler_view_promo)
    RecyclerView recyclerViewPromo;

    DealOvoPromoAdapter dealOvoPromoAdapter;
    List<String> listPromo = new ArrayList<>();

    @BindView(R.id.recycler_view_kategori)
    RecyclerView recyclerViewKategori;

    DealOvoKategoriAdapter dealOvoKategoriAdapter;
    List<DealOvoKategori> listDealOvoKategori = new ArrayList<>();
    public DealsOvoFragment() {
        // Required empty public constructor
        listPromo.add("1");
        listPromo.add("2");
        listPromo.add("3");
        listPromo.add("4");
        listPromo.add("5");
        listPromo.add("6");

        listDealOvoKategori.add(new DealOvoKategori("health", ""));
        listDealOvoKategori.add(new DealOvoKategori("entertaiment", ""));
        listDealOvoKategori.add(new DealOvoKategori("food & beverages", ""));
        listDealOvoKategori.add(new DealOvoKategori("shopping", ""));
        listDealOvoKategori.add(new DealOvoKategori("utilities", ""));
        listDealOvoKategori.add(new DealOvoKategori("others", ""));
        listDealOvoKategori.add(new DealOvoKategori("groceries", ""));
        listDealOvoKategori.add(new DealOvoKategori("transport", ""));
        listDealOvoKategori.add(new DealOvoKategori("cash", ""));
        listDealOvoKategori.add(new DealOvoKategori("education", ""));
        listDealOvoKategori.add(new DealOvoKategori("gift", ""));
        listDealOvoKategori.add(new DealOvoKategori("sport", ""));
        listDealOvoKategori.add(new DealOvoKategori("exercise", ""));
        listDealOvoKategori.add(new DealOvoKategori("fruits", ""));
        listDealOvoKategori.add(new DealOvoKategori("vegetables", ""));
    }

    public void setupRecyclerViewPromo()
    {
        recyclerViewPromo.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        dealOvoPromoAdapter = new DealOvoPromoAdapter(this.getContext(), listPromo, new OnClickRecyclerViewListener() {
            @Override
            public void onRowClicked(int position) {

            }
        });
        recyclerViewPromo.setAdapter(dealOvoPromoAdapter);
    }

    public void setupRecyclerViewKategori()
    {
        recyclerViewKategori.setLayoutManager(new GridLayoutManager(this.getContext(), 3, GridLayoutManager.VERTICAL, false));
        dealOvoKategoriAdapter = new DealOvoKategoriAdapter(this.getContext(), listDealOvoKategori, new OnClickRecyclerViewListener() {
            @Override
            public void onRowClicked(int position) {

            }
        });
        recyclerViewKategori.setAdapter(dealOvoKategoriAdapter);
        recyclerViewKategori.setNestedScrollingEnabled(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_deals_ovo, container, false);
        ButterKnife.bind(this,v);
        setupRecyclerViewPromo();
        setupRecyclerViewKategori();
        return v;
    }
}
