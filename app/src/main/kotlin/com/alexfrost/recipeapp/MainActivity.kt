package com.alexfrost.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexfrost.core.ui.R as CommonRes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(CommonRes.layout.activity_main)
    }
}
