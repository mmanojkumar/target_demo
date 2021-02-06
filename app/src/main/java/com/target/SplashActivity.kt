package com.target

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.product.presentation.activity.ProductActivity


class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom)
        zoomAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                val view = findViewById<View>(R.id.splash_title)
                view.animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
                view.visibility = View.VISIBLE
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })

        findViewById<View>(R.id.splash_logo).startAnimation(zoomAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, ProductActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)

    }

}