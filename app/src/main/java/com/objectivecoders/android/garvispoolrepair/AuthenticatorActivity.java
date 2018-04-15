package com.objectivecoders.android.garvispoolrepair;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.auth.ui.AuthUIConfiguration;


public class AuthenticatorActivity extends Activity {
    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //TODO get rid of this class after implementing firebase
    AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
        @Override
        public void onComplete(AWSStartupResult awsStartupResult) {

            AuthUIConfiguration config = new AuthUIConfiguration.Builder()
                    .userPools(true)
                    .backgroundColor(Color.BLUE)
                    .fontFamily("sans-serif-light")
                    //TODO: Add better Garvis logo.
                    .logoResId(R.drawable.garvis_logo)
                    .canCancel(true)
                    .build();
            SignInUI signin = (SignInUI) AWSMobileClient.getInstance().getClient(AuthenticatorActivity.this, SignInUI.class);
            signin.login(AuthenticatorActivity.this, HomePage.class).authUIConfiguration(config).execute();
        }
    }).execute();
}
}