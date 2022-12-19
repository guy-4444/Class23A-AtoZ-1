package com.avi.simonsaysgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
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



        loadVideoAd();


        String url = BuildConfig.BaseUrl;
    }


    private void showBanner() {
        String UNIT_ID = BuildConfig.ADMOB_BANNER_AD_ID;

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
        showBanner();
    }

    private void actionB() {
        showVideoAd();
    }





    ///////////////////////////////////////////////
    private RewardedAd mRewardedAd;


    private void loadVideoAd() {
        action_b.setEnabled(false);
        String UNIT_ID = BuildConfig.ADMOB_VIDEO_AD_ID;

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, UNIT_ID,
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("pttt", loadAdError.toString());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        action_b.setEnabled(true);
                        Log.d("pttt", "Ad was loaded.");
                    }
                });
    }

    private void showVideoAd() {
        mRewardedAd.show(this, new OnUserEarnedRewardListener() {
            @Override
            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                Toast.makeText(MainActivity.this, "Congr... +1 Live", Toast.LENGTH_SHORT).show();
                loadVideoAd();
            }
        });
    }

}