package com.chauhan.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var textScore:TextView
    lateinit var buttonAgain:Button
    lateinit var buttonExit:Button
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        textScore=findViewById(R.id.textScore)
        buttonAgain=findViewById(R.id.buttonAgain)
        buttonExit=findViewById(R.id.buttonExit)
        val score =intent.getIntExtra("userScore",0)
        textScore.text= "your score $score"


        buttonAgain.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonExit.setOnClickListener {
            val intent=Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}