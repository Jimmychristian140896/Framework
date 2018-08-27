package framework.jimmy.com.framework.dependencyinjection.module;

import dagger.Module;
import dagger.Provides;
import framework.jimmy.com.framework.dependencyinjection.scopes.ActivityScope;
import framework.jimmy.com.framework.mvp.MainActivityContract;

@Module
public class MainActivityMvpModule {
    private final MainActivityContract.View mView;


    public MainActivityMvpModule(MainActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    MainActivityContract.View provideView() {
        return mView;
    }


}
