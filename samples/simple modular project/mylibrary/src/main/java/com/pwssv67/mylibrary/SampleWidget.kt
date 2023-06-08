package com.pwssv67.mylibrary

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SampleHelloWidget(name: String = "Hello") {
    Text("Hello, $name!")
}