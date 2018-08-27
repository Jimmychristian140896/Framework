package framework.jimmy.com.framework.dependencyinjection.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import framework.jimmy.com.framework.activity.MainMvpDaggerActivity;
import framework.jimmy.com.framework.dependencyinjection.qualifier.ActivityContext;
import framework.jimmy.com.framework.dependencyinjection.scopes.ActivityScope;

@Module
public class MainActivityContextModule {
    private MainMvpDaggerActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainMvpDaggerActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainMvpDaggerActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
