package com.pedrotlf.jetpackcomposeanimationsample

import androidx.compose.runtime.Composable
import com.pedrotlf.jetpackcomposeanimationsample.ui.ContentAnimation
import com.pedrotlf.jetpackcomposeanimationsample.ui.ContentCountAnimation
import com.pedrotlf.jetpackcomposeanimationsample.ui.InfiniteBorderAnimation
import com.pedrotlf.jetpackcomposeanimationsample.ui.InfiniteShimmerAnimation
import com.pedrotlf.jetpackcomposeanimationsample.ui.InfiniteTextShimmerAnimation
import com.pedrotlf.jetpackcomposeanimationsample.ui.InfiniteValueAnimation
import com.pedrotlf.jetpackcomposeanimationsample.ui.MultipleValueAnimation
import com.pedrotlf.jetpackcomposeanimationsample.ui.ValueSpringAnimation
import com.pedrotlf.jetpackcomposeanimationsample.ui.ValueTweenAnimation
import com.pedrotlf.jetpackcomposeanimationsample.ui.VisibilityAnimation

enum class AnimationScreens {
    VisibilityAnimation,
    ValueTweenAnimation,
    ValueSpringAnimation,
    MultipleValueAnimation,
    InfiniteValueAnimation,
    InfiniteBorderAnimation,
    InfiniteShimmerAnimation,
    InfiniteTextShimmerAnimation,
    ContentAnimation,
    ContentCountAnimation;

    @Composable
    fun ComposeScreen() {
        when (this) {
            VisibilityAnimation -> VisibilityAnimation()
            ValueTweenAnimation -> ValueTweenAnimation()
            ValueSpringAnimation -> ValueSpringAnimation()
            MultipleValueAnimation -> MultipleValueAnimation()
            InfiniteValueAnimation -> InfiniteValueAnimation()
            InfiniteBorderAnimation -> InfiniteBorderAnimation()
            InfiniteShimmerAnimation -> InfiniteShimmerAnimation()
            InfiniteTextShimmerAnimation -> InfiniteTextShimmerAnimation()
            ContentAnimation -> ContentAnimation()
            ContentCountAnimation -> ContentCountAnimation()
        }
    }
}
