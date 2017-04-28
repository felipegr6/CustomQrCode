package br.fgr.customqrcode.sample

import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ExampleUnitTest {
    private var activity: MainActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .resume()
                .get()
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }

    @Test
    @Throws(Exception::class)
    fun shouldHaveWelcomeFragment() {
        assertNotNull(activity!!.findViewById(R.id.imgQrCode))
    }
}