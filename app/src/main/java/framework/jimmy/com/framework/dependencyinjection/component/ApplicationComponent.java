package framework.jimmy.com.framework.dependencyinjection.component;

import android.content.Context;

import dagger.Component;
import framework.jimmy.com.framework.FrameworkApplication;
import framework.jimmy.com.framework.dependencyinjection.module.ContextModule;
import framework.jimmy.com.framework.dependencyinjection.module.RetrofitModule;
import framework.jimmy.com.framework.dependencyinjection.qualifier.ApplicationContext;
import framework.jimmy.com.framework.dependencyinjection.scopes.ApplicationScope;
import framework.jimmy.com.framework.retrofit.APIInterface;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(FrameworkApplication myApplication);
}
