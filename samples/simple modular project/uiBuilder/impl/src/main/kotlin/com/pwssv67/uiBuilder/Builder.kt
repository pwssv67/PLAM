package com.pwssv67.uiBuilder

import androidx.compose.runtime.Composable
import com.pwssv67.mylibrary.SampleHelloWidget
import com.pwssv67.nameProvider.api.NameProvider

@Composable
fun Build(nameProvider: NameProvider) {
    SampleHelloWidget(name = nameProvider.getName())
}