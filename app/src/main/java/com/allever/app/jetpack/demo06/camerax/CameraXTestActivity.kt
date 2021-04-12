package com.allever.app.jetpack.demo06.camerax

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.allever.app.jetpack.R
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.activity_main_demo_06_camerax.*
import java.util.concurrent.Executors

class CameraXTestActivity : AppCompatActivity() {

    private val mExecutor = Executors.newCachedThreadPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_demo_06_camerax)
    }

    fun onClickOpenCamera(view: View?) {
        openCamera()
    }

    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    private val mCameraRunnable = Runnable {
        val cameraProvider = cameraProviderFuture.get()
        val preview = Preview.Builder().build()
        val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

        try {
            cameraProvider.unbindAll()
            val camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            preview.setSurfaceProvider(previewView.surfaceProvider)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun openCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(mCameraRunnable, ContextCompat.getMainExecutor(this))
    }
}