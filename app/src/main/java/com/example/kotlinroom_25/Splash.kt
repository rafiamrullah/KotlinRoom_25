package com.example.kotlinroom_25

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Splash {
    class SplashActivity : AppCompatActivity() {

        private lateinit var splash: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)

            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

            val fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.stb)


            splash = findViewById(R.id.splash)

            splash.startAnimation(fadeAnimation)

            val spalshTo = 3000
            val homeIntent = Intent(this,MainActivity::class.java)

            Handler().postDelayed({
                startActivity(homeIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                finish()
            }, spalshTo.toLong())
        }
    }
}