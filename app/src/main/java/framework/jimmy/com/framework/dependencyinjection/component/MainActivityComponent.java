package framework.jimmy.com.framework.dependencyinjection.component;

import android.content.Context;

import dagger.Component;
import framework.jimmy.com.framework.activity.MainMvpDaggerActivity;
import framework.jimmy.com.framework.dependencyinjection.module.AdapterModule;
import framework.jimmy.com.framework.dependencyinjection.module.MainActivityMvpModule;
import framework.jimmy.com.framework.dependencyinjection.qualifier.ActivityContext;
import framework.jimmy.com.framework.dependencyinjection.scopes.ActivityScope;

@ActivityScope
@Component(modules = {AdapterModule.class, MainActivityMvpModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();
    void injectMainActivity(MainMvpDaggerActivity mainActivity);
}
