package br.fgr.customqrcode

import android.content.Context
import android.support.annotation.DrawableRes
import android.widget.ImageView

import java.lang.ref.WeakReference

class CustomQrCode private constructor(private val context: Context,
                                       private val view: WeakReference<ImageView>,
                                       private val message: String, private val size: Int?,
                                       @param:DrawableRes private val drawable: Int?) {
    fun execute() {
        if (drawable != null) {
            QrCodeAsyncWBG(context, message, view, size, drawable).execute()
        } else {
            QrCodeAsyncWOBg(context, message, view, size).execute()
        }
    }

    // Public API.
    class Builder(context: Context, imageView: ImageView,
                  private val message: String) {

        private val context: Context
        private val view: WeakReference<ImageView>

        private var size: Int? = null
        private var drawable: Int? = null

        init {
            if (context == null) {
                throw IllegalArgumentException("Context must not be null.")
            }
            if (imageView == null) {
                throw IllegalArgumentException("ImageView must not be null.")
            }
            if (message == null || message.isEmpty()) {
                throw IllegalArgumentException("Message must not be null or empty.")
            }

            this.context = context.applicationContext
            this.view = WeakReference(imageView)
        }

        fun setSize(size: Int): Builder {
            if (size == null) {
                throw IllegalArgumentException("Size must not be null.")
            }
            if (size <= 0) {
                throw IllegalArgumentException("Size must be positive.")
            }

            this.size = size

            return this
        }

        fun setResource(@DrawableRes drawable: Int): Builder {
            if (drawable == null) {
                throw IllegalArgumentException("Resource must not be null.")
            }
            if (drawable === 0) {
                throw IllegalArgumentException("Resource must not be 0.")
            }

            this.drawable = drawable

            return this
        }

        fun build(): CustomQrCode {
            val context = this.context
            val view = this.view
            val message = this.message
            val size = if (this.size != null) this.size else 300
            val drawable = this.drawable

            return CustomQrCode(context, view, message, size, drawable)
        }
    }

    companion object {
        val TAG = "CustomQrCode"
    }
}
