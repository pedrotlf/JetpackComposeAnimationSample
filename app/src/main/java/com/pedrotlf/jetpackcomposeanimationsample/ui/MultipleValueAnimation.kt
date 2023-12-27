package com.pedrotlf.jetpackcomposeanimationsample.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Using [updateTransition] to animate multiple values at once. Also using [tween] to determine
 * the speed and acceleration through the animation time. There are multiple ways to animate
 * multiple values at once, but [updateTransition] is useful to organize the animations as it
 * triggers all of its children animations when its targetState changes.
 *
 * Caution! Bouncing styles of Ease may put values beyond or below the provided range. This could
 * cause crashes when dealing with border radius since it's expected range is 0-100.
 *
 * Easing animations examples: [easing.net](https://easings.net/)
 */
@Composable
fun MultipleValueAnimation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var toggle by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                toggle = !toggle
            }) {
                Text(text = "Toggle size")
            }
        }
        val transition = updateTransition(
            targetState = toggle,
            label = null
        )
        val borderRadius by transition.animateInt(
            label = "Border radius animation",
            transitionSpec = {
                tween(
                    durationMillis = 1000,
                    easing = EaseOutCubic
                )
            },
            targetValueByState = { targetToggle ->
                if (targetToggle) 0 else 50
            }
        )
        val size by transition.animateDp(
            label = "Size animation",
            transitionSpec = {
                tween(
                    durationMillis = 1000,
                    easing = EaseOutCubic
                )
            },
            targetValueByState = { targetToggle ->
                if (targetToggle) 400.dp else 200.dp
            }
        )
        val color by transition.animateColor(
            label = "Size animation",
            transitionSpec = {
                tween(
                    durationMillis = 1000,
                    easing = EaseOutCubic
                )
            },
            targetValueByState = { targetToggle ->
                if (targetToggle) Color.Red else Color.Blue
            }
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(RoundedCornerShape(borderRadius))
                    .background(color)
            )
        }
    }
}
