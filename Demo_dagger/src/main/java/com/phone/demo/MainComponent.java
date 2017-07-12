package com.phone.demo;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Phone on 2017/7/7.
 */

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
