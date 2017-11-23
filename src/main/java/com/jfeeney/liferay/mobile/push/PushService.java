package com.jfeeney.liferay.mobile.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.jfeeney.liferay.mobile.R;
import com.liferay.mobile.push.PushNotificationsService;

import org.json.JSONException;
import org.json.JSONObject;

public class PushService extends PushNotificationsService {

    @Override
    public void onPushNotification(JSONObject jsonObject) {
        super.onPushNotification(jsonObject);

        try {

            jsonObject = new JSONObject(jsonObject.getString("body"));

            String title = getString(jsonObject, "title");
            String description = getString(jsonObject, "description");

            createGlobalNotification(title, description);
        } catch (JSONException e) {
            Log.e("Error", e.getMessage());
        }
    }

    private void createGlobalNotification(String title, String description) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setSound(uri)
                .setVibrate(new long[]{2000, 1000, 2000, 1000})
                .setSmallIcon(R.drawable.liferay_glyph);

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }


    private String getString(final JSONObject json, final String element) throws JSONException {
        return json.has(element) ? json.getString(element) : "";
    }
}
