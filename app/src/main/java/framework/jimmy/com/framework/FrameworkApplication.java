package framework.jimmy.com.framework;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import framework.jimmy.com.framework.dependencyinjection.ApiComponent;
import framework.jimmy.com.framework.dependencyinjection.ApiModule;
import framework.jimmy.com.framework.dependencyinjection.AppModule;
import framework.jimmy.com.framework.dependencyinjection.DaggerApiComponent;
import framework.jimmy.com.framework.dependencyinjection.component.ApplicationComponent;
import framework.jimmy.com.framework.dependencyinjection.component.DaggerApplicationComponent;
import framework.jimmy.com.framework.dependencyinjection.module.ContextModule;
import framework.jimmy.com.framework.util.LogUtil;
import framework.jimmy.com.framework.util.RxBus;

public class FrameworkApplication extends Application {
    ApplicationComponent applicationComponent;
    private ApiComponent mApiComponent;
    private RxBus bus;



    private static FrameworkApplication mInstance;
    public static synchronized FrameworkApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        mInstance = this;

        bus = new RxBus();
        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("https://simplifiedcoding.net/demos/"))
                .build();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

        LogUtil.init();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }

    public RxBus bus() {
        return bus;
    }

    public static FrameworkApplication get(Activity activity){
        return (FrameworkApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
