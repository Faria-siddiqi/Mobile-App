package com.example.instaclone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<Button>(R.id.button)
        loginBtn.setOnClickListener{launchHome()}
    }

    private fun launchHome() {
        Intent(applicationContext , SecondActivity::class.java).also {
            startActivity(it)
        }
    }


}
