package framework.jimmy.com.framework.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.activity.BarcodeCaptureActivity;
import framework.jimmy.com.framework.activity.BarcodeConverterActivity;
import framework.jimmy.com.framework.activity.FlexibleActivity;
import framework.jimmy.com.framework.activity.GalaxyActivity;
import framework.jimmy.com.framework.activity.MainOvoActivity;
import framework.jimmy.com.framework.activity.MyProfileOvoActivity;
import framework.jimmy.com.framework.activity.PostOfficeActivity;
import framework.jimmy.com.framework.activity.TopUpOvoActivity;
import framework.jimmy.com.framework.adapter.DealOvoKategoriAdapter;
import framework.jimmy.com.framework.adapter.DealOvoPromoAdapter;
import framework.jimmy.com.framework.interfaces.OnClickRecyclerViewListener;
import framework.jimmy.com.framework.model.DealOvoKategori;
import framework.jimmy.com.framework.model.LocationReceivedEvent;
import framework.jimmy.com.framework.model.MessageEvent;
import framework.jimmy.com.framework.util.GPSTracker;
import ru.dimorinny.showcasecard.position.Center;
import ru.dimorinny.showcasecard.step.ShowCaseStep;
import ru.dimorinny.showcasecard.step.ShowCaseStepDisplayer;


public class HomeNewOvoFragment extends Fragment {
    private static final String TAG = "BarcodeMain";
    private static final int RC_BARCODE_CAPTURE = 9001;
    GPSTracker gpsTracker;
    @BindView(R.id.recycler_view_iklan)
    RecyclerView recyclerViewIklan;

    DealOvoPromoAdapter dealOvoPromoAdapter;
    List<String> listPromo = new ArrayList<>();

    @BindView(R.id.txt_ovo_cash)
    TextView txtOvoCash;

    @BindView(R.id.txt_ovo_point)
    TextView txtOvoPoint;

    @OnClick(R.id.lbl_ovo_cash)
    public void onClickLblOvoCash() {
        Intent intent = new Intent(this.getActivity(), BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
        intent.putExtra(BarcodeCaptureActivity.UseFlash, false);

        startActivityForResult(intent, RC_BARCODE_CAPTURE);
        //startActivity(new Intent(this.getActivity(), BarcodeCaptureActivity.class));
    }

    @OnClick(R.id.lbl_ovo_point)
    public void onClickLblOvoPoint() {
        startActivity(new Intent(this.getActivity(), BarcodeConverterActivity.class));
    }

    @OnClick(R.id.txt_ovo_cash)
    public void onClickTxtOvoCash() {
        startActivity(new Intent(this.getActivity(), FlexibleActivity.class));
    }

    @OnClick(R.id.txt_ovo_point)
    public void onClickTxtOvoPoint() {
        startActivity(new Intent(this.getActivity(), PostOfficeActivity.class));
    }

    @OnClick(R.id.btn_top_up)
    public void onBtnTopUp()
    {
        startActivity(new Intent(this.getActivity(), TopUpOvoActivity.class));
    }

    public HomeNewOvoFragment() {
        // Required empty public constructor
        listPromo.add("1");
        listPromo.add("2");
        listPromo.add("3");
        listPromo.add("4");
        listPromo.add("5");
        listPromo.add("6");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_new_ovo, container, false);
        ButterKnife.bind(this, v);
        setupRecyclerViewIklan();
        setupLocation();
        showCase();
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser)
        {
            Log.d("MyFragment", "Fragment is visible.");

        }
        else
            Log.d("MyFragment", "Fragment is not visible.");
    }

    public void showCase()
    {
        /*new ShowCaseStepDisplayer.Builder(this.getActivity())
                .addStep(new ShowCaseStep(new Center(), "Message at center"))
                .addStep(new ShowCaseStep(txtOvoCash, "Ovo Cash"))
                .build().start();
                */
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        final SpannableString sassyDesc = new SpannableString("It allows you to go back, sometimes");
        sassyDesc.setSpan(new StyleSpan(Typeface.ITALIC), sassyDesc.length() - "sometimes".length(), sassyDesc.length(), 0);
        new TapTargetSequence(this.getActivity())
                .targets(
                        TapTarget.forView(txtOvoCash, "Ovo Cash").id(1),
                        TapTarget.forView(txtOvoPoint, "Ovo Point", "Click Here")
                                .dimColor(android.R.color.holo_blue_bright)
                                .outerCircleColor(R.color.dark_green_pastel)
                                .targetCircleColor(R.color.colorAccent)
                                .textColor(android.R.color.holo_purple).id(2),

                        //TapTarget.forToolbarNavigationIcon(toolbar, "This is the back button", sassyDesc).id(3),
                        // Likewise, this tap target will target the search button
                        TapTarget.forToolbarMenuItem(toolbar, R.id.action_setting, "This is a search icon", "As you can see, it has gotten pretty dark around here...")
                        .dimColor(android.R.color.black)
                        .outerCircleColor(R.color.colorAccent)
                        .targetCircleColor(android.R.color.black)
                        .transparentTarget(true)
                        .textColor(android.R.color.black)
                        .id(4))
                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        // Yay
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }



                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                    }
                }).start();
    }

    public void setupLocation() {
        gpsTracker = new GPSTracker(this.getContext());
        if (gpsTracker.canGetLocation()) {
            Toast.makeText(this.getContext(), "Long : " + gpsTracker.getLongitude() + ", Lat : " + gpsTracker.getLatitude(), Toast.LENGTH_LONG).show();
            EventBus.getDefault().postSticky(new LocationReceivedEvent(gpsTracker.getLongitude(), gpsTracker.getLatitude()));
            //EventBus.getDefault().post(new MessageEvent("Long : " + gpsTracker.getLongitude() + ", Lat : " + gpsTracker.getLatitude()));
        }
    }

    public void setupRecyclerViewIklan() {
        recyclerViewIklan.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        dealOvoPromoAdapter = new DealOvoPromoAdapter(this.getContext(), listPromo, new OnClickRecyclerViewListener() {
            @Override
            public void onRowClicked(int position) {

            }
        });
        recyclerViewIklan.setAdapter(dealOvoPromoAdapter);
    }

    @Override
    public void onDestroy() {
        gpsTracker.stopUsingGPS();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    txtOvoPoint.setText(R.string.barcode_success);
                    txtOvoCash.setText(barcode.displayValue);
                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    txtOvoPoint.setText(R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                txtOvoPoint.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

