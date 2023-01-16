package com.avi.simonsaysgame;

import android.app.Application;

import com.avi.simonsaysgame.income.InApp_ID;
import com.avi.simonsaysgame.income.MyInApp;
import com.google.android.gms.ads.MobileAds;
import com.avi.simonsaysgame.income.MyInApp.Item;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MobileAds.initialize(this);


        String LICENSE_KEY = "sdsdfdsfdsfffds";
        Item[] items = new Item[] {
                new Item(MyInApp.TYPE.Subscription, InApp_ID.PRO_ID),
                new Item(MyInApp.TYPE.OneTimeInApp, InApp_ID.NO_ADS_ID),
                new Item(MyInApp.TYPE.RepurchaseInApp, InApp_ID.COINS_2_ID),
                new Item(MyInApp.TYPE.RepurchaseInApp, InApp_ID.COINS_1_ID),
        };
        MyInApp.initHelper(this, LICENSE_KEY, items);
    }
}
