package com.example.pastatimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var spaghetti: Button
    lateinit var penne: Button
    lateinit var soba: Button
    lateinit var rotini: Button
    lateinit var addSeconds: Button
    lateinit var startButton: Button
    lateinit var countDownTimer: CountDownTimer

    var howLong = 0
    var counterActive = false
    var addSec = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //findViewById for all attributes:
        textView = findViewById(R.id.textView)
        spaghetti = findViewById(R.id.spaghetti)
        penne = findViewById(R.id.penne)
        soba = findViewById(R.id.soba)
        rotini = findViewById(R.id.rotini)
        addSeconds = findViewById(R.id.add30Sec)
        startButton = findViewById(R.id.startButton)

        addSeconds.setOnClickListener() {

            fun onClick(view: View?) {
                addSec = true
                countDownTimer.start()
            }//onClick

        }//addSeconds.setOnClickListener
    }//onCreate

    fun setCookingTime( view: View){
       when(view.id){
           R.id.spaghetti -> updateTimer( 570)
           R.id.penne -> updateTimer( 660)
           R.id.soba -> updateTimer(420)
           R.id.rotini -> updateTimer(540)
       }//when
    }//setCookingTime

    fun updateTimer(secondsLeft : Int) {
        val minutes = secondsLeft / 60
        val seconds = secondsLeft - minutes * 60
        var secondStr = seconds.toString()

        if (seconds <= 9) {
            secondStr = "0$secondStr"
        }
        textView.text =  minutes.toString() + ":" + secondStr
    }//updateTimer

    fun startTimer(view : View) {
        if (!counterActive) {
            counterActive = true
            startButton.text = "Stop"

            if (addSec) {
                howLong += 30000
            }

            countDownTimer = object : CountDownTimer(howLong.toLong(), 1000) {

                override fun onTick(p0: Long) {

                    if (addSec) {
                        updateTimer((p0 + 30000 / 1000).toInt())
                    }
                    else {
                        updateTimer((p0 / 1000).toInt())
                    }
                }
                override fun onFinish() {
                    //TODO("Not yet implemented")
                }
            }//CountDownTimer
            countDownTimer.start()
        }
        else {
            countDownTimer.cancel()
            counterActive = false
            startButton.text = "Start"
        }

    }//startTimer

}//MainActivity