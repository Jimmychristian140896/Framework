package framework.jimmy.com.framework.dependencyinjection;


import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;
import framework.jimmy.com.framework.activity.MainActivity;
import framework.jimmy.com.framework.activity.MainOvoActivity;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity activity);
    void inject(MainOvoActivity activity);
}