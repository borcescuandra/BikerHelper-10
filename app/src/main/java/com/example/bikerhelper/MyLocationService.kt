package com.example.bikerhelper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.bikerhelper.RecordingActivity.Companion.getMainInstance
import com.google.android.gms.location.LocationResult
import java.time.LocalTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit


class MyLocationService : BroadcastReceiver(){

    private val rotation= getMainInstance().rotationVector

    companion object {
        const val ACTION_PROCESS_UPDATE="com.android.example.bikerhelper.UPDATE_LOCATION"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?){
        if(intent!=null){
            val action = intent.action

            if(action.equals(ACTION_PROCESS_UPDATE))
            {
                val result = LocationResult.extractResult(intent)
                val location = result.lastLocation

                val locationString=StringBuilder(location.latitude.toString())
                    .append("/").append(location.longitude).toString()
                val latitude=location.latitude
                val longitude=location.longitude
                val time= LocalTime.now(ZoneId.of("Europe/Bucharest")).truncatedTo(ChronoUnit.SECONDS)
                val timeDouble=time.toSecondOfDay()

                try{
                    getMainInstance().updateUITimer()
                    //Populated RoomDB with latitude,longitude and time
                    getMainInstance().insertDB(0,latitude,longitude,rotation,timeDouble)
                } catch (e:Exception){
                    //if app in killed mode
                    Toast.makeText(context,locationString, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}