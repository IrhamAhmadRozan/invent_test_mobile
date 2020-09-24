package com.developers.rozan.inventtestmobile.feature.FirstActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.developers.rozan.inventtestmobile.R;
import com.developers.rozan.inventtestmobile.feature.MainActivity.MainActivity;
import com.invent.cashpickup.feature.splashscreen.CP_SplashScreenActivity;
import com.invent.cashpickup.utils.constant.CP_SCashPickup;
import com.invent.cashpickup.utils.model.CP_UserData;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        findViewById(R.id.buttonCP).setOnClickListener(view -> {
            Intent i = new Intent(FirstActivity.this, CP_SplashScreenActivity.class);
            i.putExtra(CP_SCashPickup.USER_DATA, setUserData());
            startActivity(i);
        });

        findViewById(R.id.buttonITM).setOnClickListener(view -> {
            Intent i = new Intent(FirstActivity.this, MainActivity.class);
            FirstActivity.this.startActivity(i);
        });
    }

    private CP_UserData setUserData() {
        CP_UserData CPUserData = new CP_UserData();
        CPUserData.setCodeCompany("csp1");
        CPUserData.setUserId("kahfi");
        return CPUserData;
    }
}