package framework.jimmy.com.framework.dialog;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.adapter.KategoriPengeluaranAdapter;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.KategoriPengeluaran;

public class TestDialog extends DialogFragment {
    private static final String SELECTED = "SELECTED";
    int mNum;
    List<KategoriPengeluaran> listKategoriPengeluaran;
    String selectedKategoriPengeluaran;
    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    public static TestDialog newInstance(String selectedKategoriPengeluaran) {
        TestDialog f = new TestDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString(SELECTED, selectedKategoriPengeluaran);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedKategoriPengeluaran = getArguments().getString(SELECTED);
        listKategoriPengeluaran = new ArrayList<KategoriPengeluaran>();
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_account_balance_wallet_black_24dp,"F & B"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_add_black_24dp,"Transport"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_check_black_24dp,"Groceries"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_deals_black_24dp,"Shopping"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_expand_more_black_24dp,"Entertaiment"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_finance_black_24dp,"Utilities"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_history_black_24dp,"Personal"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_notifications_black_24dp,"Education"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_notifications_none_black_24dp,"Gift"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_ovo_24dp,"Cash"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_search_black_24dp,"Health"));
        listKategoriPengeluaran.add(new KategoriPengeluaran(R.drawable.ic_settings_applications_black_24dp,"Others"));

        // Pick a style based on the num.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_kategori_picker, container, false);

        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view_kategori);


        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));




        KategoriPengeluaranAdapter adapter = new KategoriPengeluaranAdapter(this.getContext(), listKategoriPengeluaran, selectedKategoriPengeluaran, new OnClickRecyclerViewListener() {
            @Override
            public void onRowClicked(int position) {
                KategoriPengeluaran kategoriPengeluaran = listKategoriPengeluaran.get(position);

                TestDialogListener activity = (TestDialogListener) getActivity();
                activity.onTestDialogListener(kategoriPengeluaran);
                TestDialog.this.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);



        return v;
    }
    public interface TestDialogListener {
        void onTestDialogListener(KategoriPengeluaran kategoriPengeluaran);
    }
}