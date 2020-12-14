package com.allever.app.jetpack.demo04.setup;

import android.content.Context;
import android.util.Log;

import androidx.startup.Initializer;

/**
 * @author allever
 */
public class Sdk1 {
    private static final String TAG = Sdk1.class.getSimpleName();

    private Sdk1() {
        log("init " + TAG);
    }

    private static class Holder {
        private static final Sdk1 INS = new Sdk1();
    }

    public static Sdk1 getIns() {
        return Holder.INS;
    }

    public static Sdk1 getIns(Context context) {
        return Holder.INS;
    }

    private void log(String msg) {
        Log.d(TAG, msg);
    }
}
