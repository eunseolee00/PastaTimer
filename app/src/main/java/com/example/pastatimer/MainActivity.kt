package com.example.pastatimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
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

        fun updateTimer(secondsLeft : Int) {
            val minutes = secondsLeft / 60
            val seconds = secondsLeft - minutes * 60
            var secondStr = seconds.toString()

            if (seconds <= 9) {
                secondStr = "0$secondStr"
            }

            textView.text =  minutes.toString()+":" + secondStr
        }//updateTimer

        fun startTimer(view : View) {

            if (!counterActive) {
                counterActive = true
                startButton.text = "Stop"

                countDownTimer = object : CountDownTimer(10000, 1000) {

                    override fun onTick(p0: Long) {
                        updateTimer((p0 / 1000).toInt())
                    }

                    override fun onFinish() {
                        //TODO("Not yet implemented")
                    }
                }//CountDownTimer
                countDownTimer.start()
            }//startTimer
            else {
                countDownTimer.cancel()
                counterActive = false
                startButton.text = "Start"
            }

        }//startTimer


    }//onCreate
}//MainActivity