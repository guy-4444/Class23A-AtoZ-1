package com.avi.simonsaysgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {

    private MaterialButton action_a;
    private MaterialButton action_b;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FrameLayout main_LAY_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_LAY_banner = findViewById(R.id.main_LAY_banner);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setUserId("test0");
        mFirebaseAnalytics.setUserProperty("favorite_food", "fries.");

        action_a = findViewById(R.id.action_a);
        action_b = findViewById(R.id.action_b);

        action_a.setOnClickListener(v -> actionA());
        action_b.setOnClickListener(v -> actionB());


//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });



        showBanner();
    }

    private void showBanner() {
        String UNIT_ID = "ca-app-pub-3940256099942544/6300978111";
        if (BuildConfig.DEBUG) {
            UNIT_ID = "ca-app-pub-3940256099942544/6300978111";
        }

        AdView adView = new AdView(this);
        adView.setAdUnitId(UNIT_ID);
        main_LAY_banner.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }


    private void actionA() {
        //Bundle bundle = new Bundle();
        //bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Action");
        //bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "A");
        //bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Clicked");
        //mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "game_level");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Level 1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Selected");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    private void actionB() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "game_level");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Level 2");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Selected");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

}