package framework.jimmy.com.framework.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import framework.jimmy.com.framework.FrameworkApplication;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.adapter.RecyclerViewAdapter;
import framework.jimmy.com.framework.dependencyinjection.component.ApplicationComponent;
import framework.jimmy.com.framework.dependencyinjection.component.DaggerMainActivityComponent;
import framework.jimmy.com.framework.dependencyinjection.component.MainActivityComponent;
import framework.jimmy.com.framework.dependencyinjection.module.MainActivityContextModule;
import framework.jimmy.com.framework.dependencyinjection.module.MainActivityMvpModule;
import framework.jimmy.com.framework.dependencyinjection.qualifier.ActivityContext;
import framework.jimmy.com.framework.dependencyinjection.qualifier.ApplicationContext;
import framework.jimmy.com.framework.mvp.MainActivityContract;
import framework.jimmy.com.framework.mvp.PresenterImpl;
import framework.jimmy.com.framework.pojo.CryptoData;

public class MainMvpDaggerActivity extends AppCompatActivity implements MainActivityContract.View, RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvp_dagger);


        ApplicationComponent applicationComponent = FrameworkApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar = findViewById(R.id.progressBar);

        presenter.loadData();


    }

    @Override
    public void launchIntent(String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        // startActivity(new Intent(activityContext, DetailActivity.class).putExtra("name", name));
    }

    @Override
    public void showData(List<CryptoData> data) {
        recyclerViewAdapter.setData(data);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
