import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
import com.pedrotlf.jetpackcomposeanimationsample.ui.theme.JetpackComposeAnimationSampleTheme

@Preview
@Composable
private fun VisibilityAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        VisibilityAnimation()
    }
}

@Preview
@Composable
private fun ValueTweenAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        ValueTweenAnimation()
    }
}

@Preview
@Composable
private fun ValueSpringAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        ValueSpringAnimation()
    }
}

@Preview
@Composable
private fun MultipleValueAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        MultipleValueAnimation()
    }
}

@Preview
@Composable
private fun InfiniteValueAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        InfiniteValueAnimation()
    }
}

@Preview
@Composable
private fun InfiniteBorderAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        InfiniteBorderAnimation()
    }
}

@Preview
@Composable
private fun InfiniteShimmerAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        InfiniteShimmerAnimation()
    }
}

@Preview
@Composable
private fun InfiniteTextShimmerAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        InfiniteTextShimmerAnimation()
    }
}

@Preview
@Composable
private fun ContentAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        ContentAnimation()
    }
}

@Preview
@Composable
private fun ContentCountAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        ContentCountAnimation()
    }
}