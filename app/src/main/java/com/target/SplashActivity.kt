package com.target

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.product.presentation.activity.ProductActivity


class SplashActivity : AppCompatActivity() {

    companion object{
        private const val SPLASH_TIME_OUT: Long = 3500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val imageView: ImageView = findViewById(R.id.splash_logo)
        Glide.with(this).asGif().load(R.raw.target_logo_anim).into(imageView)

        findViewById<View>(R.id.splash_title).animation =
                AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, ProductActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)

    }

}