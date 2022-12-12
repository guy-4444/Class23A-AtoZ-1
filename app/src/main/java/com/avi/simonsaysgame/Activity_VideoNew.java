package com.avi.simonsaysgame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.pictrivia.common.CommonConstants;
import com.pictrivia.common.activities.Activity_Base;

public class Activity_VideoNew extends AppCompatActivity {

    public static final String TAG = "PTTT_Activity_VideoNew";

    private MaterialButton action_a;


    VideoAd coinVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        action_a.setOnClickListener(view -> start());

        initAds();
    }

    private void start() {
        coinVideo.show();
    }

    VideoAd.CallBack callBack = new VideoAd.CallBack() {
        @Override
        public void unitLoaded() {

        }

        @Override
        public void earned() {

        }

        @Override
        public void canceled() {

        }

        @Override
        public void failed() {

        }
    };

    private void initAds() {
        coinVideo = new VideoAd(this, YOUR_ID, callBack);
    }



    private void findViews() {
        action_a = findViewById(R.id.action_a);
    }

}