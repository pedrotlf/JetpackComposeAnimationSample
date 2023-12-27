# Jetpack Compose Animation Showcase
Welcome to the Jetpack Compose animations showcase repo! Harnessing the powerful capabilities of Jetpack Compose, this project is a collection of dynamic and interactive animations implemented using the "animation-core-android" library.

<p align="center">
  <img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/53554000-0347-4d07-971b-bd3af97005f9" width="20%" align="center">
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/3cb2e7aa-4d84-419e-9ba9-0ba2a4b07ccc" width="30%" align="center">
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/3cb85954-8a61-4154-a082-332a9137f61b" width="20%" align="center">
</p>

## About the Project
Jetpack Compose, a modern UI toolkit for Android development, provides a declarative approach to building interfaces along with the ability to create intuitive animations. The "animation-core-android" library is a key component of Compose, offering features for crafting seamless transitions, captivating visual effects, and immersive interactions.

[Check Google's documentation for Jetpack Compose animations](https://developer.android.com/jetpack/compose/animation/introduction)

# Showcase
Let's dive into the showcase!

## Visibility Animation
This showcases the seamless transition of an object between visibility states. Easy to achieve by simply wraping your object or layout with the composable `AnimatedVisibility()`.

[VisibilityAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/VisibilityAnimation.kt)

![visibility_animation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/67662c0a-56f0-4137-8c61-1dd83eeef51e)

You can also edit your animation changing the `enter` and/or `exit` parameter. You can also combine animations using the operator `+`.

```kotlin
//Give this example a try!
AnimatedVisibility(
    visible = isVisible,
    modifier = Modifier.fillMaxWidth().weight(1f),
    enter = fadeIn() + slideInHorizontally(),
    exit = fadeOut() + slideOutHorizontally()
) {
    Box(modifier = Modifier.background(Color.Red))
}
```

## Value Animation
This demonstrates the dynamic animation of attribute changes within a Composable object. 

### Using tween
In our example we're going to show changes in the radius and size of a Box. We use the `animateIntAsState` to change the radius percent value according to the `tween` used in the `animationSpec` param. The same is true for the size but we need to use the `animateDpAsState` instead.

The `easing` parameter makes most of the magic, it's used to determine the acceleration and speed at which the object's value changes as frames progress.

You will notice that we animate only a value and do not wrap the object around another Composable like we did with the `VisibilityAnimation`. Jetpack Compose already recomposes the `Box` whenever the `size` or the `borderRadius` change, causing the animation.

[ValueTweenAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/ValueTweenAnimation.kt)

<img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/7d099e69-d124-45c3-926c-f8e32314625e" width="30%">

Check out [Easgings.net](https://easings.net/) for some examples of different easings.

CAUTION! Bouncing styles of Ease may put values beyond or below the provided range. This could cause crashes when dealing with border radius percent for example, since it's expected range is 0-100.

### Using spring
Now we're going to show changes in the size of a circular Box, but with some bounciness applied!

We're also using `animateDpAsState`, but now we replaced `tween` with `spring` and applied the `dampingRatio` and `stiffness` in order to achieve some bounciness.

[ValueSpringAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/ValueSpringAnimation.kt)

<img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/b07520ad-1db9-4608-b002-59b8620c21cd" width="30%">

CAUTION! This boucing effect put values beyond or below the provided range. This could cause crashes when dealing with border radius percent for example, since it's expected range is 0-100.
