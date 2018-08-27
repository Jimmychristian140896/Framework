package framework.jimmy.com.framework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.util.MyBottomSheet;


public class WalletOvoFragment extends Fragment {

    @OnClick(R.id.card_ovo_member)
    public void onClickCardOvoMember()
    {
        MyBottomSheet.showBottomSheetTest(this.getActivity());
    }

    public WalletOvoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wallet_ovo, container, false);
        ButterKnife.bind(this,v);
        return v;
    }
}