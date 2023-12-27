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

## Multiple Value Animation
Here we showcase the power of `updateTransition` to simuntaniously animate changes in the size, shape and color of a Box. 

There are multiple ways to animate multiple values at once, but this feature provides a streamlined approach to organize and synchronize multiple animations at once. Whenever the `targetState` undergoes a change, it triggers the animations for all associated children.

[MultipleValueAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/MultipleValueAnimation.kt)

<img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/b3690784-4e7e-4329-b84e-d0b24eff347c" width="30%">

CAUTION! `updateTransition` acts weird when we change the `targetValue` before the animation finishes (and we don't know the reason for that). You can try it out in our sample app by spamming the "toggle" button. Notice that we also have a multiple value change in the ["Value Animation using tween" sample](#using-tween), and it behaves much better when we spam the "toggle" button.

Another possible way to animate multiple values at once would be by simply calling the animations at the same time. `updateTransition` is more useful to organize your code and make it more readable and "friendly".

## Infinite Value Animation (Repeatable)
This is one of the most used animations, the infinite repeatable animation, using the `InfiniteTransition`. Mostly used in loading animations, this animation repeats itself by restarting the animation from 0 or reversing when it finishes. This feature is similar to `updateTransition`, organizing and syncronizing multiple animations, but now with an infinite duration.

Using this is as simple as the previous features, we just need to assign a `rememberInfiniteTransition()` to a `val` and call `.animateColor()` or `.animateFloat()` for example. The `animationSpec` parameter now receives a `InfiniteRepeatableSpec` that also specifies the animation duration with `tween` or `spring` for example, and receives the `repeatMode` that determines how the animation will keep repeating (reversing or restarting).

### Simple Infinite Value Animation
Here we showcase an infinite animation of a spining Box that keps changing it's color between red and green.

It is achieved by simply animating the rotation value from 0 to 360 (`animateFloat()`), using the `repeatMode = Restart` (since 360 degrees is the same as 0 degrees for the rotation), and the color from `Color.Red` to `Color.Green` (`animateColor()`), using the `repeatMode = Reverse`.

Notice that if the `targetValue` and the `initialValue` represents the same state we can use the `Restart` mode without noticing when the animation restarted, otherwise the animation would "blink" back to the initial state. With the `Reverse` mode we will never have that problem.

[InfiniteValueAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/InfiniteValueAnimation.kt)

<img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/c92d1499-b767-46f8-9a8c-cbab134489d9" width="30%">

Here we are using the `tween` with the `LinearEasing`, to achieve that linear animation, but we could use any other kind of easing. Try it out!

### Infinite Border Animation
Now things are getting prettier. Here we're going to showcase an infinite rotating border animation. One with a linear easing and another one using the `EaseInOutCirc` combined with multiple turns (target rotation value 1080 degrees).

To achieve that we used the Jetpack Compose Ui Graphic component called `Brush`. By calling its function `.sweepGradient()` and passing a list of colors, it creates a sweep gradient with the given colors which draws clockwise. Then we draw a circle using the resulting `Brush` and animate the rotation angle, putting it right before the `drawContent()` (inside the `drawWithContent{}`) so we can have the border effect.

[InfiniteBorderAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/InfiniteBorderAnimation.kt)

<img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/768c2997-a207-4885-bf7c-ff34a2dea2bf" width="50%">
