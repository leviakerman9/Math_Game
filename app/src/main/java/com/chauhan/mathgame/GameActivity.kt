package com.chauhan.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import java.util.Objects
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var textScore:TextView
    lateinit var textLife:TextView
    lateinit var textTime:TextView
    lateinit var textQuestion:TextView
    lateinit var editTextAnswer:EditText
    lateinit var buttonOk:Button
    lateinit var buttonNext:Button
    var correctAns=0
    var score=0
    var life=3
    lateinit var timer:CountDownTimer
    private val startTimeInMillis:Long=15000
    var leftTimeInMillis=startTimeInMillis

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar!!.title="Addition"

        textScore=findViewById(R.id.textViewScore)
        textLife=findViewById(R.id.textViewLife)
        textTime=findViewById(R.id.textViewTime)
        textQuestion=findViewById(R.id.textViewQuestion)
        editTextAnswer=findViewById(R.id.editTextAnswer)
        buttonOk=findViewById(R.id.buttonOk)
        buttonNext=findViewById(R.id.buttonNext)
        gameContinue()

        buttonOk.setOnClickListener {
            val input=editTextAnswer.text.toString()
            if(input==""){
                Toast.makeText(this,"please write something or tap the next button",Toast.LENGTH_LONG).show()
            }
            else{
                pauseTimer()
                val userAnswer=input.toInt()
                if(userAnswer==correctAns){
                    score += 10
                    textQuestion.text="Write answer"
                    textScore.text=score.toString()
                }
                else{
                    life--
                    textQuestion.text="Wrong answer"
                    textLife.text=life.toString()
                }
            }

        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            gameContinue()
            editTextAnswer.setText("")

            if(life==0){
                Toast.makeText(this,"Game Over",Toast.LENGTH_LONG).show()
                val intent=Intent(this,ResultActivity::class.java)
                intent.putExtra("userScore",score)
                startActivity(intent)
                finish()
            }
            else{
                 gameContinue()
            }
        }


    }
    @SuppressLint("SetTextI18n")
    private fun gameContinue() {
        val operation = intent.getStringExtra("operation")
        when (operation) {
            "Addition" -> generateAdditionQuestion()
            "Subtraction" -> generateSubtractionQuestion()
            "Multiplication"-> generateMultiplicationQuestion()
            "Division"-> generateDivisionQuestion()
            // Add more cases for other operations if needed
        }

        countDown()
    }

    @SuppressLint("SetTextI18n")
    private fun generateDivisionQuestion() {
        val num1 = Random.nextInt(0, 100)
        val num2 = Random.nextInt(0, 100)
        textQuestion.text = "$num1 / $num2"
        correctAns = num1 / num2
    }

    @SuppressLint("SetTextI18n")
    private fun generateMultiplicationQuestion() {
        val num1 = Random.nextInt(0, 100)
        val num2 = Random.nextInt(0, 100)
        textQuestion.text = "$num1 * $num2"
        correctAns = num1 * num2
    }

    @SuppressLint("SetTextI18n")
    private fun generateAdditionQuestion() {
        val num1 = Random.nextInt(0, 100)
        val num2 = Random.nextInt(0, 100)
        textQuestion.text = "$num1 + $num2"
        correctAns = num1 + num2
    }

    @SuppressLint("SetTextI18n")
    private fun generateSubtractionQuestion() {
        val num1 = Random.nextInt(0, 100)
        val num2 = Random.nextInt(0, num1)
        textQuestion.text = "$num1 - $num2"
        correctAns = num1 - num2
    }
    fun countDown() {
         timer = object : CountDownTimer(leftTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                    leftTimeInMillis=millisUntilFinished
                    updateText()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                    pauseTimer()
                    resetTimer()
                    updateText()
                    life--
                    textLife.text=life.toString()
                    textQuestion.text="Sorry,Time over!"
            }
        }

        // Start the countdown timer
        timer.start()
    }

    private fun resetTimer() {
       leftTimeInMillis=startTimeInMillis
    }

    private fun pauseTimer() {
           timer.cancel()
    }

    private fun updateText() {
        val remainingTime:Int=(leftTimeInMillis/1000).toInt()
        textTime.text= String.format(Locale.getDefault(),"%02d",remainingTime)
    }

}
