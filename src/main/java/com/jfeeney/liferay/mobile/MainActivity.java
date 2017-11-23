package com.jfeeney.liferay.mobile;

import android.os.Bundle;

import com.liferay.mobile.screens.push.PushScreensActivity;
import com.liferay.mobile.screens.util.LiferayLogger;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.context.SessionContext;

import org.json.JSONObject;

public class MainActivity extends PushScreensActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Session getDefaultSession() {
        return SessionContext.createSessionFromCurrentSession();
    }

    @Override
    protected void onPushNotificationReceived(JSONObject jsonObject) {
        LiferayLogger.d("Received! " + jsonObject.toString());
    }

    @Override
    protected void onErrorRegisteringPush(String message, Exception e) {
        LiferayLogger.e("Error registering!", e);
    }

    @Override
    protected String getSenderId() {
        return this.getString(R.string.push_sender_id);
    }

}
