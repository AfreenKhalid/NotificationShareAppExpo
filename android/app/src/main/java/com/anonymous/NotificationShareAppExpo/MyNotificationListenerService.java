package com.anonymous.NotificationShareAppExpo;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.ReactApplication;

public class MyNotificationListenerService extends NotificationListenerService {
    private static final String TAG = "NotificationListener";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i(TAG, "Notification posted: " + sbn.getPackageName());
        sendEvent("notificationPosted", sbn.getPackageName());
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i(TAG, "Notification removed: " + sbn.getPackageName());
        sendEvent("notificationRemoved", sbn.getPackageName());
    }

    private void sendEvent(String eventName, String params) {
        ReactContext reactContext = ((ReactApplication) getApplication()).getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
        if (reactContext != null) {
            reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
        }
    }
}
