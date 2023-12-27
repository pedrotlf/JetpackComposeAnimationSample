package com.pedrotlf.jetpackcomposeanimationsample.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Using [AnimatedVisibility] to animate between visible and not visible
 */
@Composable
fun VisibilityAnimation() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var isVisible by remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text(text = "Toggle visibility")
        }
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            Box(modifier = Modifier.background(Color.Red))
        }
    }
}
