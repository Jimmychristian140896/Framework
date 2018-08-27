package framework.jimmy.com.framework.dependencyinjection.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import framework.jimmy.com.framework.dependencyinjection.qualifier.ApplicationContext;
import framework.jimmy.com.framework.dependencyinjection.scopes.ApplicationScope;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
