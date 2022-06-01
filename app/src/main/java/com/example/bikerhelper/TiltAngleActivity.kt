package com.example.bikerhelper

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.android.synthetic.main.activity_tilt_angle.*
import java.io.BufferedWriter
import java.lang.StrictMath.atan
import java.lang.StrictMath.sqrt
import kotlin.math.atan
import kotlin.math.sqrt

class TiltAngleActivity : AppCompatActivity(), SensorEventListener {

    private var RAD_TO_DEG = 57.295779513082320876798154814105f

    private var sensorManager: SensorManager? = null
    private var color = false

    private  var ax=0
    private  var ay=0
    private  var az=0


    override fun onAccuracyChanged(s: Sensor?, i: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tilt_angle)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }


    private fun getAccelerometer(event: SensorEvent) {
        // Movement
        val xVal = event.values[0]
        val yVal = event.values[1]
        val zVal = event.values[2]
        tvXAxiz.text = "X Value: ".plus(xVal.toString())
        tvYAxis.text = "Y Value: ".plus(yVal.toString())
        tvZAxis.text = "Z Value: ".plus(zVal.toString())



        val accelerationSquareRoot = (xVal * xVal + yVal * yVal + zVal * zVal) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)

        ax = (RAD_TO_DEG * atan(xVal / sqrt(yVal * yVal + zVal * zVal))).toInt()
        ay = (RAD_TO_DEG * atan(yVal / sqrt(xVal * xVal + zVal * zVal))).toInt()
        az = (RAD_TO_DEG * atan(xVal / sqrt(yVal * yVal + xVal * xVal))).toInt()

        x.text ="X value:".plus(ax.toString())
        y.text ="Y value:".plus(ay.toString())
        z.text ="Z value:".plus(az.toString())





        if (accelerationSquareRoot >= 3) {
            Toast.makeText(this, "Device was shuffled", Toast.LENGTH_SHORT).show()
            if (color) {
                relative.setBackgroundColor(resources.getColor(R.color.colorAccent))
            } else {
                relative.setBackgroundColor(Color.YELLOW)
            }
            color = !color
        }
    }


    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}