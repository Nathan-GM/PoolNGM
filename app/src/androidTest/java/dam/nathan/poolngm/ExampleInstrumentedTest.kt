package dam.nathan.poolngm

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import kotlin.jvm.Throws

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val APP_PACKAGE = "dam.nathan.poolngm"
    private val LAUNCH_TIMEOUT = 5000L
    private lateinit var mDevice : UiDevice

    @Before
    fun setUp() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        mDevice.pressHome()

        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val intent = context.packageManager.getLaunchIntentForPackage(APP_PACKAGE)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        context.startActivity(intent)

        mDevice.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), LAUNCH_TIMEOUT)
    }

    @Test
    @Throws(UiObjectNotFoundException::class)
    fun validarExisteTextField() {
        var textFieldM3 = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/numMetroCubico"))
        var textFieldPH = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/numPH"))
        var textFieldG = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/numGramos"))
        var textFieldR = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/result"))

        val actualTextM3 = textFieldM3.text
        val actualTextPH = textFieldPH.text
        val actualTextG = textFieldG.text
        val actualTextR = textFieldR.text

        assertEquals("El cuadrado de texto no esta presente", true, textFieldM3.exists())
        assertEquals("El cuadrado de texto no esta presente", true, textFieldPH.exists())
        assertEquals("El cuadrado de texto no esta presente", true, textFieldG.exists())
        assertEquals("El cuadrado de texto no esta presente", true, textFieldR.exists())
    }

    @Test
    @Throws(UiObjectNotFoundException::class)
    fun resultadoCorrecto() {
        var textFieldM3 = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/numMetroCubico"))
        var textFieldPH = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/numPH"))
        var textFieldG = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/numGramos"))
        var textFieldR = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/result"))

        textFieldM3.setText("43")
        textFieldPH.setText("7.0")
        textFieldG.setText("5")

        var bCalc = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/btCalc"))
        bCalc.click()

        var ph = textFieldPH.text.toString().toDouble()
        var m3 = textFieldM3.text.toString().toInt()
        var gramos = textFieldG.text.toString().toInt()

        var final : Double = (phIDEAL - ph) * 10 * m3 * gramos

        var valuesTFR = textFieldR.text.split(" ")
        var numTFR = valuesTFR[0]

        assertEquals("El resultado no es correcto", true, final.toFloat() == numTFR.toFloat())
    }

    @Test
    @Throws(UiObjectNotFoundException::class)
    fun resultadoNegativo() {
        var textFieldM3 = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/numMetroCubico"))
        var textFieldPH = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/numPH"))
        var textFieldG = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/numGramos"))
        var textFieldR = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/result"))

        textFieldM3.setText("43")
        textFieldPH.setText("7.5")
        textFieldG.setText("5")

        var bCalc = mDevice.findObject(UiSelector().resourceId("$APP_PACKAGE:id/btCalc"))
        bCalc.click()

        var ph = textFieldPH.text.toString().toDouble()
        var m3 = textFieldM3.text.toString().toInt()
        var gramos = textFieldG.text.toString().toInt()

        var final : Double = (phIDEAL - ph) * 10 * m3 * gramos

        var valuesTFR = textFieldR.text.split(" ")
        var numTFR = valuesTFR[0]

        assertEquals("El resultado debería ser negativo", true, textFieldR.text.startsWith("-") && final.toFloat() == numTFR.toFloat())

    }
}