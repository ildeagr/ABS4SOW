package com.example.abs4sow

import android.content.Intent
import android.os.IBinder
import com.google.android.gms.location.ActivityRecognitionResult
import com.google.android.gms.location.DetectedActivity

class ActivityRecognizeService {
    fun ActivityRecognizedService() {
    }
    var LOCAL_BROADCAST_NAME: String = "LOCAL_ACT_RECOGNITION"
    var LOCAL_BROADCAST_EXTRA: String = "RESULT"

    fun onBind(intent: Intent?): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (ActivityRecognitionResult.hasResult(intent)) {
            val result = ActivityRecognitionResult.extractResult(intent!!)
            handleDetectedActivities(result!!.probableActivities)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun handleDetectedActivities(probableActivities: kotlin.collections.List<DetectedActivity>) {
        val str = StringBuilder()

        for (activity in probableActivities) {
            when (activity.type) {
                DetectedActivity.IN_VEHICLE -> {
                    str.append(" " + "In_Vehicle:" + " " + activity.confidence + " ")
                }

                DetectedActivity.ON_BICYCLE -> {
                    str.append(" " + "On_Bicycle:" + " " + activity.confidence + " ")
                }

                DetectedActivity.ON_FOOT -> {
                    str.append(" " + "On_Foot:" + " " + activity.confidence + " ")
                }

                DetectedActivity.RUNNING -> {
                    str.append(" " + "Running:" + " " + activity.confidence + " ")
                }

                DetectedActivity.STILL -> {
                    str.append(" " + "Still:" + " " + activity.confidence + " ")
                }

                DetectedActivity.TILTING -> {
                    str.append(" " + "Tilting:" + " " + activity.confidence + " ")
                }

                DetectedActivity.WALKING -> {
                    str.append(" " + "Walking:" + " " + activity.confidence + " ")
                }

                DetectedActivity.UNKNOWN -> {
                    str.append(" " + "Unknown:" + " " + activity.confidence + " ")
                }
            }
        }

        val intent = Intent(LOCAL_BROADCAST_NAME)
        intent.putExtra(LOCAL_BROADCAST_EXTRA, str.toString())
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}