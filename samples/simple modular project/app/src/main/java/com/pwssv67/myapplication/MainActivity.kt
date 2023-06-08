package com.pwssv67.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pwssv67.myapplication.ui.theme.MyApplicationTheme
import com.pwssv67.nameProvider.impl.NameProviderImpl
import com.pwssv67.uiBuilder.Build

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nameProvider = NameProviderImpl() //instead of proper DI, this is a simplified version

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Build(nameProvider)
                }
            }
        }
    }
}