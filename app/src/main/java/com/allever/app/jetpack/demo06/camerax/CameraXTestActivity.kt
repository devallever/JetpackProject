package com.allever.app.jetpack.demo06.camerax

import android.graphics.*
import android.media.Image
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.allever.app.jetpack.R
import com.allever.app.jetpack.ext.log
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.activity_main_demo_06_camerax.*
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.concurrent.Executors


class CameraXTestActivity : AppCompatActivity() {

    private val mExecutor = Executors.newSingleThreadExecutor()
    private val mPaint = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_demo_06_camerax)
    }

    fun onClickOpenCamera(view: View?) {
        openCamera()
    }

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    private val mCameraRunnable = Runnable {
        val cameraProvider = cameraProviderFuture.get()
        val preview = Preview.Builder().build()
        val imageAnalysis = ImageAnalysis.Builder().build()

        val cameraSelector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()


        var canvas: Canvas? = null
        try {
            cameraProvider.unbindAll()
            val camera =
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
            imageAnalysis.setAnalyzer(mExecutor, object : ImageAnalysis.Analyzer {
                override fun analyze(image: ImageProxy) {
//                    logd("analyze: $image")

                    val imageInfo = image.imageInfo

                    log("format = ${image.format} ")


                    try {
                        canvas = surfaceView.surfaceHolder.lockCanvas()

                        val rotateBitmap = image2Bitmap(image)
                        rotateBitmap?.let {
                            canvas?.drawBitmap(rotateBitmap, 0f, 0f, mPaint)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        canvas?.let {
                            surfaceView.surfaceHolder.unlockCanvasAndPost(it)
                        }
                    }

                    //关闭后才能返回
                    image.close()
                }
            })

            preview.setSurfaceProvider(previewView.surfaceProvider)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun rotateBitmap(origin: Bitmap?, alpha: Float): Bitmap? {
        if (origin == null) {
            return null
        }
        val width = origin.width
        val height = origin.height
        val matrix = Matrix()
        matrix.setRotate(alpha)
        // 围绕原地进行旋转
        val newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
        if (newBM.equals(origin)) {
            return newBM
        }
        origin.recycle()
        return newBM
    }

    private fun openCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(mCameraRunnable, ContextCompat.getMainExecutor(this))
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()    // Rewind the buffer to zero
        val data = ByteArray(remaining())
        get(data)   // Copy the buffer into a byte array
        return data // Return the byte array
    }

    private fun image2Bitmap(image: ImageProxy): Bitmap? {
        val planes = image.planes

        for (i in planes.indices) {
            log( "pixelStride  " + planes[i].pixelStride)
            log(  "rowStride   " + planes[i].rowStride)
            log(  "width  " + image.width)
            log(  "height  " + image.height)
            log(  "Finished reading data from plane  $i")
        }

        //cameraX 获取yuv
        val yBuffer: ByteBuffer = planes[0].buffer
        val uBuffer: ByteBuffer = planes[1].buffer
        val vBuffer: ByteBuffer = planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        yBuffer[nv21, 0, ySize]
        vBuffer[nv21, ySize, vSize]
        uBuffer[nv21, ySize + vSize, uSize]

        //获取yuvImage
        //获取yuvImage
        val yuvImage =
            YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
        //输出流
        //输出流
        val out = ByteArrayOutputStream()
        //压缩写入out
        //压缩写入out
        yuvImage.compressToJpeg(
            Rect(0, 0, yuvImage.width, yuvImage.height),
            50,
            out
        )
        //转数组
        //转数组
        val imageBytes = out.toByteArray()
        //生成bitmap
        //生成bitmap
        val bitmap =
            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        val rotateBitmap: Bitmap? = rotateBitmap(bitmap, 90f)
        return rotateBitmap;
    }

}