package com.example.presentation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bumptech.glide.load.engine.Resource
import com.example.core.Status
import com.example.presentation.R
import com.example.presentation.viemodel.CockTailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CockTailsActivity : AppCompatActivity() {
    private val viewModel: CockTailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktails)
//        actionBar?.hide()
//        supportActionBar?.hide()
    }
    private fun initSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            viewModel.cockTailStatus.value?.status == Status.LOADING
        }
    }
}
