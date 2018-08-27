package framework.jimmy.com.framework.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.transition.Fade;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.transition.TransitionValues;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.helper.HelperConvert;
import framework.jimmy.com.framework.helper.HelperPermission;
import framework.jimmy.com.framework.util.barcode.MyBarcodeUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MyProfileOvoActivity extends AppCompatActivity {

    private static final int PICK_CONTACT = 1000;
    private Toolbar toolbar;

    @BindView(R.id.img_qr_code)
    ImageView imgQrCode;

    @OnClick(R.id.img_qr_code)
    public void onClickImgQrCode() {
        MyBarcodeUtil.scan(this);

    }

    @OnClick(R.id.txt_customer_name_ovo)
    public void onClickTxtCustomerNameOvo() {
        MyBarcodeUtil.scanCustom(this);

    }


    @BindView(R.id.txt_customer_name_ovo) TextView txtCustomerNameOvo;

    @OnClick(R.id.btn_upgrade_sekarang)
    public void onClickUpgradeSekarang()
    {
        startActivity(new Intent(this, UpgradeKeOvoPremierStartActivity.class));
    }


    @OnClick(R.id.img_customer_ovo)
    public void onClickImgCustomerOvo()
    {
        if(HelperPermission.askForPermission(this, Manifest.permission.READ_CONTACTS, HelperPermission.CONTACTS_REQUEST_CODE) == true)
        {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
            startActivityForResult(intent, PICK_CONTACT);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == HelperPermission.CONTACTS_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onClickImgCustomerOvo();

            } else {

                Toast.makeText(this, permissions[0].toString()+ " permission denied", Toast.LENGTH_LONG).show();

            }

        }
    }



    public void onActivityResult(final int reqCode, final int resultCode, final Intent data) {
        MyBarcodeUtil.parseActivityResult(this, reqCode, resultCode, data, new MyBarcodeUtil.BarcodeResult() {
            @Override
            public void success(String result) {
                txtCustomerNameOvo.setText(result);
            }

            @Override
            public void failed() {
                Toast.makeText(MyProfileOvoActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void notBarcode() {
                MyProfileOvoActivity.this.notBarcode(reqCode, resultCode, data);
            }
        });

        /*IntentResult result = IntentIntegrator.parseActivityResult(reqCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.e("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                // dapetin hasil scannya
                Log.e("MainActivity", "Scanned: " + result.getContents());
               // wishlistCheckProduct(result.getContents());

            }
        }
        else {
            super.onActivityResult(reqCode, resultCode, data);

            switch (reqCode) {
                case (PICK_CONTACT):
                    if (resultCode == Activity.RESULT_OK) {
                        Uri contactData = data.getData();
                        String id = contactData.getLastPathSegment();
                        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id}, null);
                        int phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                        if (cursor.moveToFirst()) {
                            while (cursor.isAfterLast() == false) {


                                String name = cursor
                                        .getString(cursor
                                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                                String phoneNumber = cursor
                                        .getString(cursor
                                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace("-", "");
                                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG)
                                        .show();

                                txtCustomerNameOvo.setText(name + " : " + phoneNumber);
                                break;
                            }
                        } else {
                            //no results actions
                            Toast.makeText(getApplicationContext(), "Number not found", Toast.LENGTH_LONG)
                                    .show();
                        }


                        cursor.close();
                    }
                    break;
            }
        }*/

    }

    private void notBarcode(int reqCode, int resultCode, Intent data)
    {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    String id = contactData.getLastPathSegment();
                    Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id}, null);
                    int phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                    if (cursor.moveToFirst()) {
                        while (cursor.isAfterLast() == false) {


                            String name = cursor
                                    .getString(cursor
                                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                            String phoneNumber = cursor
                                    .getString(cursor
                                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace("-", "");
                            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG)
                                    .show();

                            txtCustomerNameOvo.setText(name + " : " + phoneNumber);
                            break;
                        }
                    } else {
                        //no results actions
                        Toast.makeText(getApplicationContext(), "Number not found", Toast.LENGTH_LONG)
                                .show();
                    }


                    cursor.close();
                }
                break;
        }
    }

    @BindView(R.id.layout_ovo_premier_detail)
    ViewGroup layoutOvoPremierDetail;

    @BindView(R.id.layout_ovo_club_detail)
    ViewGroup layoutOvoClubDetail;

    @BindView(R.id.img_expand_ovo_premier)
    ImageView imgExpandOvoPremier;

    @BindView(R.id.img_expand_ovo_club)
    ImageView imgExpandOvoClub;

    @OnClick({R.id.img_ovo_premier, R.id.img_expand_ovo_premier, R.id.lbl_ovo_premier})
    public void onClickPremier()
    {

        TransitionManager.beginDelayedTransition(layoutOvoPremierDetail);
        if(layoutOvoPremierDetail.getVisibility() == View.VISIBLE)
        {
            layoutOvoPremierDetail.setVisibility(View.GONE);
            imgExpandOvoPremier.animate().rotation(0).setDuration(100).start();

        }
        else
        {
            layoutOvoPremierDetail.setVisibility(View.VISIBLE);

            imgExpandOvoPremier.animate().rotation(180).setDuration(100).start();
        }
    }

    @OnClick({R.id.img_ovo_club, R.id.img_expand_ovo_club, R.id.lbl_ovo_club})
    public void onClickClub()
    {

        TransitionManager.beginDelayedTransition(layoutOvoClubDetail);
        if(layoutOvoClubDetail.getVisibility() == View.VISIBLE)
        {
            layoutOvoClubDetail.setVisibility(View.GONE);
            imgExpandOvoClub.animate().rotation(0).setDuration(100).start();
        }
        else
        {
            layoutOvoClubDetail.setVisibility(View.VISIBLE);
            imgExpandOvoClub.animate().rotation(180).setDuration(100).start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_ovo);
        ButterKnife.bind(this);
        setTitle("ABOUT US");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initValue();

    }

    private void initValue()
    {
        /*Observable.just("089649004169")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        return HelperConvert.convertStringToQRCode(s);
                    }
                })
                .subscribeWith(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        imgQrCode.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }


                });*/
        imgQrCode.setImageBitmap(HelperConvert.convertStringToQRCode("089649004169"));
        //new TaskQrCode().execute("089649004169");
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
    class TaskQrCode extends AsyncTask<String, Void, Bitmap>
    {
        public TaskQrCode() {}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... arg0) {
            return HelperConvert.convertStringToQRCode(arg0[0]);

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imgQrCode.setImageBitmap(result);
            // Dismiss the progress dialog

        }
    }
}