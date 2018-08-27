package framework.jimmy.com.framework.dependencyinjection.module;

import dagger.Module;
import dagger.Provides;
import framework.jimmy.com.framework.activity.MainMvpDaggerActivity;
import framework.jimmy.com.framework.adapter.RecyclerViewAdapter;
import framework.jimmy.com.framework.dependencyinjection.scopes.ActivityScope;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCoinList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainMvpDaggerActivity mainActivity) {
        return mainActivity;
    }
}
