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
        val builder = CustomQrCode.Builder(this, imgQrCode, "Hello World")
        builder.setSize(512).setResource(R.drawable.smiley)

        builder.build().execute()
    }
}
