package framework.jimmy.com.framework.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.FrameworkApplication;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.model.LocationReceivedEvent;
import framework.jimmy.com.framework.model.MessageEvent;
import framework.jimmy.com.framework.util.AuthGoogleUtil;


public class SettingOvoActivity extends AppCompatActivity {

    AuthGoogleUtil authGoogleUtil;
    /*@Override
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
    public void onEvent(LocationReceivedEvent event) {
        lblEditProfil.setText("Long : "+event.getLongitude()+", Lat : "+event.getLatitude());
    }*/
    private Toolbar toolbar;

    @BindView(R.id.lbl_edit_profil)
    TextView lblEditProfil;

    @OnClick(R.id.lbl_edit_profil)
    public void onClickLblEditProfil()
    {
        startActivity(new Intent(this, EditProfilOvoActivity.class));
    }

    @OnClick(R.id.lbl_kode_promo)
    public void onClickLblKodePromo()
    {
        startActivity(new Intent(this, KlaimKodePromoOvoActivity.class));
    }

    @OnClick(R.id.lbl_tentang_ovo)
    public void onClickLblAboutUsOvo()
    {
        startActivity(new Intent(this, AboutUsOvoActivity.class));
    }

    @OnClick(R.id.lbl_saran)
    public void onClickLblSaranOvo()
    {
        startActivity(new Intent(this, SaranOvoActivity.class));
    }

    @OnClick(R.id.lbl_hubungi_ovo)
    public void onClickLblHubungiOvo()
    {
        startActivity(new Intent(this, HubungiOvoActivity.class));
    }

    @OnClick(R.id.btn_sign_out)
    public void onClickBtnSignOut()
    {




        authGoogleUtil.signOut();
        showUserGoogle();
        authGoogleUtil.signIn();

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        authGoogleUtil.signInResult(requestCode,resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        showUserGoogle();
    }



    @OnClick(R.id.lbl_informasi)
    public void onClickLblInformasi()
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_ovo);
        ButterKnife.bind(this);
        setTitle("SETTINGS");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setAuthGoogleUtil();
        initValue();

    }

    public void setAuthGoogleUtil() {
        this.authGoogleUtil = new AuthGoogleUtil(this);
        showUserGoogle();
    }

    public void showUserGoogle()
    {
        if(authGoogleUtil.getUser() != null)
        {
            String text = "Display Name : "+authGoogleUtil.getUser().getDisplayName()
                    +"\nEmail : "+authGoogleUtil.getUser().getEmail()
                    +"\nPhone Number : "+authGoogleUtil.getUser().getPhoneNumber()
                    +"\nProvider Id : "+authGoogleUtil.getUser().getProviderId()
                    +"\nUid : "+authGoogleUtil.getUser().getUid()
                    +"\nMetadata : "+authGoogleUtil.getUser().getMetadata()
                    +"\nPhoto Url : "+authGoogleUtil.getUser().getPhotoUrl();
            Log.d(SettingOvoActivity.this.getClass().getSimpleName(), text);
            Toast.makeText(this,text,Toast.LENGTH_LONG).show();
        }
    }

    private void initValue()
    {
        EventBus.getDefault().postSticky(new MessageEvent(this.getClass().getSimpleName()));
        ((FrameworkApplication) getApplication())
                .bus().send(new MessageEvent(this.getClass().getSimpleName()));

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