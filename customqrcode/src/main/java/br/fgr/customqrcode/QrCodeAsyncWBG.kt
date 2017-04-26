package br.fgr.customqrcode

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import java.lang.ref.WeakReference

class QrCodeAsyncWBG(private val context: Context, private val message: String,
                     private val imageView: WeakReference<ImageView>, private val size: Int?,
                     private val drawable: Int) : AsyncTask<Void, Bitmap, Bitmap>() {

    override fun doInBackground(vararg params: Void): Bitmap? {
        try {
            val aux = Bitmap.createScaledBitmap(BitmapFactory
                    .decodeResource(context.resources, drawable),
                    (0.7 * size!!).toInt(), (0.7 * size).toInt(), false)

            val bitmapDrawable = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmapDrawable)

            val cw = bitmapDrawable.width - aux.width shr 1
            val ch = bitmapDrawable.height - aux.height shr 1

            canvas.drawBitmap(aux, cw.toFloat(), ch.toFloat(), null)

            val matrixDrawable = IntArray(size * size)
            bitmapDrawable.getPixels(matrixDrawable, 0, size, 0, 0, size, size)

            val qrCodeMatrix = MultiFormatWriter()
                    .encode(message, BarcodeFormat.QR_CODE, size, size, null)

            val pixels = IntArray(size * size)

            for (y in 0..size - 1) {
                for (x in 0..size - 1) {
                    val index = y * size + x
                    pixels[index] = if (qrCodeMatrix.get(x, y))
                        if (matrixDrawable[index] == 0) BLACK else matrixDrawable[index]
                    else WHITE
                }
            }

            val qrCode = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            qrCode.setPixels(pixels, 0, size, 0, 0, size, size)

            return qrCode

        } catch (e: Exception) {
            Log.d(TAG, "Error", e)
        }

        return null
    }

    override fun onPostExecute(bitmap: Bitmap) {
        super.onPostExecute(bitmap)
        imageView.get()?.setImageBitmap(bitmap)
    }

    companion object {
        private val TAG = "QrCodeAsyncWBG"
    }
}
