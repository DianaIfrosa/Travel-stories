package com.example.travelstories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.travelstories.ui.Main
import com.example.travelstories.ui.theme.TravelStoriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelStoriesTheme {
                Main()
            }
        }
    }
}
