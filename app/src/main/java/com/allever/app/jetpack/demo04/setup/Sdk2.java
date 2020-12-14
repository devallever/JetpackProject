package com.allever.app.jetpack.demo04.setup;

import android.content.Context;
import android.util.Log;

/**
 * @author allever
 */
public class Sdk2 {
    private static final String TAG = Sdk2.class.getSimpleName();

    private Sdk2() {
        log("init " + TAG);
    }

    private static class Holder {
        private static final Sdk2 INS = new Sdk2();
    }

    public static Sdk2 getIns() {
        return Holder.INS;
    }

    public static Sdk2 getIns(Context context) {
        return Holder.INS;
    }

    private void log(String msg) {
        Log.d(TAG, msg);
    }
}
