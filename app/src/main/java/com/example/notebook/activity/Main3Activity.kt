package com.example.notebook.activity

import android.os.Bundle
import com.example.notebook.R
import androidx.appcompat.app.AppCompatActivity

class Main3Activity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temp_layout)

        finish()
    }
}