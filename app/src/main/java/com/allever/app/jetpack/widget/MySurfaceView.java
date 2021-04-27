package com.allever.app.jetpack.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author allever
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    public SurfaceHolder getSurfaceHolder() {
        return mSurfaceHolder;
    }

    public Canvas getCanvas() {
        return mCanvas;
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        log("surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        log("surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        log("surfaceDestroyed");
    }

    @Override
    public void run() {

    }

    private static final String TAG = MySurfaceView.class.getSimpleName();
    private void log(String msg) {
        Log.d(TAG, msg);
    }
}
