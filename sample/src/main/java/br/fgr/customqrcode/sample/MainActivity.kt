package br.fgr.customqrcode.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.fgr.customqrcode.CustomQrCode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val builder = CustomQrCode.Builder(this, imgQrCode,
                "F3F87A79EA756CA63E1F4F01A9E010679CE605B720A7DE0148BA4A28AC75555D7324e5502739d9e529154e995d823341e0f3c8056a34ce6e21d09b31f5f9a86c1faf25b29888e61b")
        builder.setSize(512).setResource(R.drawable.smiley)

        builder.build().execute()
    }
}
