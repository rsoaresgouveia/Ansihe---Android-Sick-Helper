package com.example.sickhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

@SuppressWarnings("unused")
public class Splash_activity extends Activity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        
        Handler SplashScreen = new Handler();
        SplashScreen.postDelayed(Splash_activity.this, 3000);
    }
    
    public void run(){
    	startActivity(new Intent (Splash_activity.this, Select_user.class));
    	finish();
    }
}
