package com.phone.demo;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Phone on 2017/7/7.
 */

@Module
public class MainModule {

    @Singleton
    @Provides
    public Cloth getCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("red");
        return cloth;
    }

    @Provides
    public Clothes getClothes(Cloth cloth) {
        return new Clothes(cloth);
    }

    @Provides
    @Named("red")
    public Cloth getRedCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @Provides
    @Named("blue")
    public Cloth getBlueCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("蓝色");
        return cloth;
    }
}
