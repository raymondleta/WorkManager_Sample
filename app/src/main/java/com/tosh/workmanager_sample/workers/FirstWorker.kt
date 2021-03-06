package com.tosh.workmanager_sample.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tosh.workmanager_sample.makeNotification
import com.tosh.workmanager_sample.sleep


class FirstWorker(ctx: Context, params: WorkerParameters): Worker(ctx, params) {

    private val TAG by lazy {
        FirstWorker::class.java.simpleName
    }
    override fun doWork(): Result {
        val data = inputData.getString("KEY_WORK")?:""
        makeNotification(data, applicationContext)

        return try {
            //Actual work
            sleep()
            makeNotification("Done with first work", applicationContext)
            Result.success()
        }catch (throwable: Throwable){
            Log.e(TAG, "Error doing first work", throwable)
            Result.failure()
        }
    }
}