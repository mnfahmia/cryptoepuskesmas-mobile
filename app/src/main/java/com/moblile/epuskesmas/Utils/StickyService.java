package com.moblile.epuskesmas.Utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.moblile.epuskesmas.Session.SessionManager;

public class StickyService extends Service {
    SessionManager mSession;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        mSession = new SessionManager(getApplicationContext());
        Log.d(getClass().getName(), "App just got removed from Recents!");
        mSession.exit();

    }


}
