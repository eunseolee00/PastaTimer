package com.example.pastatimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.NumberFormat as NumberFormat

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

    }//onCreate



    fun setCookingTime( view: View){
        if(counterActive){
            countDownTimer.cancel()
            counterActive = false
            startButton.text = "Start"
        }
       when(view.id){
           R.id.spaghetti -> {
               updateTimer(570)
               howLong = 570000}
           R.id.penne -> {
               updateTimer(660)
               howLong = 660000}
           R.id.soba -> {
               updateTimer(420)
               howLong = 420000}
           R.id.rotini -> {
               updateTimer(540)
               howLong = 540000
           }
       }//when
    }//setCookingTime

    fun updateTimer(secondsLeft : Int) {
        var minutes = secondsLeft / 60
        var seconds = secondsLeft - (minutes * 60)
        var secondStr = seconds.toString()

       if (seconds <= 9) {
            secondStr = "0$secondStr"
        }
        textView.text =  minutes.toString() + ":" + secondStr
    }//updateTimer

    fun startTimer(view : View) {

        if (view.id == R.id.add30Sec) {
            if(counterActive){
                countDownTimer.cancel()
                counterActive = false
            }

            howLong += 30000
            updateTimer(howLong/1000)

            countDownTimer = object : CountDownTimer(howLong.toLong(), 1000){
                override fun onTick(p0: Long) {
                    updateTimer((p0 / 1000).toInt())
                }//onTick

                override fun onFinish() {
                    //TODO("Not yet implemented")
                }//onFinish
            }//countDownTimer

            countDownTimer.start()
            counterActive = true
        }

        if (!counterActive) {
            counterActive = true
            startButton.text = "Stop"

            countDownTimer = object : CountDownTimer(howLong.toLong(), 1000) {

                override fun onTick(p0: Long) {
                    updateTimer((p0 / 1000).toInt())
                    howLong -= 1000
                }//onTick
                override fun onFinish() {
                    //TODO("Not yet implemented")
                }//onFinish
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