package com.p.andrews.dogggs

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import com.p.andrews.style.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentNightMode =
                resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            AppTheme(isDarkMode = currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                Column(
                    Modifier
                        .statusBarsPadding()
                        .background(AppTheme.colors.primary.background)
                ) {
                    AppNavGraph()
                }
            }
        }
    }
}