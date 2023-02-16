package com.example.codelabtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val TAG = "ActivityMain"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // We can assign coroutine to variable
        val job = GlobalScope.launch(Dispatchers.Default){
            Log.d(TAG, "Starting long run calculations...")
            //All code inside withTimeout will run for 5000 mills  and then coroutine will be canceled OR you just can use job.cancel() after some delay
            withTimeout(5000){

                for (i in 30..45){
                    delay(1000)
                    //Good practice to check if launch was not canceled  using function isAlive because there is a possibility to fail cancellation  using job.cancel()
                    if (isActive){
                        Log.d(TAG, "Result for i = $i: ${fib(i)}")
                    }
                }
            }

            Log.d(TAG, "Ending of long running calculation...")
        }
    }

    private fun fib(i: Int): Long {
        return if (i==0) 0
        else if(i==1) 1
        else fib(i-1) + fib(i-2)
    }


}