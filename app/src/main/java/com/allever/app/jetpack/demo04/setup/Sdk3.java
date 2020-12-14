package com.allever.app.jetpack.demo04.setup;

import android.content.Context;
import android.util.Log;

/**
 * @author allever
 */
public class Sdk3 {
    private static final String TAG = Sdk1.class.getSimpleName();

    private Sdk3() {
        log("init " + TAG);
    }

    private static class Holder {
        private static final Sdk3 INS = new Sdk3();
    }

    public static Sdk3 getIns() {
        return Holder.INS;
    }

    public static Sdk3 getIns(Context context) {
        return Holder.INS;
    }

    private void log(String msg) {
        Log.d(TAG, msg);
    }
}
