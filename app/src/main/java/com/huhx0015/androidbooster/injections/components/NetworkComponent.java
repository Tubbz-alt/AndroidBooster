package com.huhx0015.androidbooster.injections.components;

import com.huhx0015.androidbooster.architecture.base.BaseActivity;
import com.huhx0015.androidbooster.architecture.base.BaseFragment;
import com.huhx0015.androidbooster.injections.modules.ApplicationModule;
import com.huhx0015.androidbooster.injections.modules.NetworkModule;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by Michael Yoon Huh on 6/22/2017.
 */

@Singleton
@Component(modules={ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {
    void inject(BaseActivity activity);
    void inject(BaseFragment fragment);
}