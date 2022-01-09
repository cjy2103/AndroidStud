package com.example.findviewbyid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvWord : TextView
    private lateinit var btnChange : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        wordChange()
    }

    /**
     * @DESC: 초기 바인딩 
     */
    private fun initBinding(){
        tvWord = findViewById(R.id.tv_word)
        btnChange = findViewById(R.id.btn_change)
    }

    /**
     * @DESC: 단어변경
     */
    private fun wordChange(){
        btnChange.setOnClickListener {
            tvWord.setText("값변경")
        }
    }
}