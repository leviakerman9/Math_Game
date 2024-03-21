package com.chauhan.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var addition:Button
    lateinit var subtraction:Button
    lateinit var multiplication:Button
    lateinit var division:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addition=findViewById(R.id.buttonAdd)
        subtraction=findViewById(R.id.buttonSub)
        multiplication=findViewById(R.id.buttonMulti)
        division=findViewById(R.id.buttonDiv)

        addition.setOnClickListener {
            val intent=Intent(this,GameActivity::class.java)
            intent.putExtra("operation","Addition")
            startActivity(intent)
        }
        subtraction.setOnClickListener {
            val intent=Intent(this,GameActivity::class.java)
            intent.putExtra("operation","Subtraction")
            startActivity(intent)
        }
        multiplication.setOnClickListener {
            val intent=Intent(this,GameActivity::class.java)
            intent.putExtra("operation","Multiplication")
            startActivity(intent)
        }
        division.setOnClickListener {
            val intent=Intent(this,GameActivity::class.java)
            intent.putExtra("operation","Division")
            startActivity(intent)
        }
    }
}