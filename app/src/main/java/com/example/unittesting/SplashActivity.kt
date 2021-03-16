package com.example.unittesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.unittesting.ui.homePage.HomePageActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
    override fun onStart() {
        super.onStart()
        Handler()
            .postDelayed({
                startActivity(Intent(this@SplashActivity, HomePageActivity::class.java))
                finish()
            }, 2000)

    }
}