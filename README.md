# Jetpack Compose Animation Showcase
[![screenshot-test](https://img.shields.io/badge/screenshot_test-readme-28A745)](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/SCREENSHOT_TEST_README.md)

Welcome to the Jetpack Compose animations showcase repo! Harnessing the powerful capabilities of Jetpack Compose, this project is a collection of dynamic and interactive animations implemented using the "animation-core-android" library.

<p align="center">
  <img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/3cb85954-8a61-4154-a082-332a9137f61b" width="25%" align="center">
  <img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/3cb2e7aa-4d84-419e-9ba9-0ba2a4b07ccc" width="37.5%" align="center">
  <img src="https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/4a7790a3-c746-4829-8126-e47cafcb4b4a" width="21.25%" align="center">
</p>

## About the Project
Jetpack Compose, a modern UI toolkit for Android development, provides a declarative approach to building interfaces along with the ability to create intuitive animations. The "animation-core-android" library is a key component of Compose, offering features for crafting seamless transitions, captivating visual effects, and immersive interactions.

[Check Google's documentation for Jetpack Compose animations](https://developer.android.com/jetpack/compose/animation/introduction)

## Compose Preview Screenshot Testing
We recently added screenshot testing to our project, take a look at the [SCREENSHOT_TEST_README](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/SCREENSHOT_TEST_README.md) for more details.

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

![ValueTweenAnimation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/7d099e69-d124-45c3-926c-f8e32314625e)

Check out [Easgings.net](https://easings.net/) for some examples of different easings.

⚠️ CAUTION! Bouncing styles of Ease may put values beyond or below the provided range. This could cause crashes when dealing with border radius percent for example, since it's expected range is 0-100.

### Using spring
Now we're going to show changes in the size of a circular Box, but with some bounciness applied!

We're also using `animateDpAsState`, but now we replaced `tween` with `spring` and applied the `dampingRatio` and `stiffness` in order to achieve some bounciness.

[ValueSpringAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/ValueSpringAnimation.kt)

![ValueSpringAnimation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/b07520ad-1db9-4608-b002-59b8620c21cd)

⚠️ CAUTION! This boucing effect put values beyond or below the provided range. This could cause crashes when dealing with border radius percent for example, since it's expected range is 0-100.

## Multiple Value Animation
Here we showcase the power of `updateTransition` to simuntaniously animate changes in the size, shape and color of a Box. 

There are multiple ways to animate multiple values at once, but this feature provides a streamlined approach to organize and synchronize multiple animations at once. Whenever the `targetState` undergoes a change, it triggers the animations for all associated children.

[MultipleValueAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/MultipleValueAnimation.kt)

![MultipleValueAnimation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/b3690784-4e7e-4329-b84e-d0b24eff347c)

⚠️ CAUTION! `updateTransition` acts weird when we change the `targetValue` before the animation finishes (and we don't know the reason for that). You can try it out in our sample app by spamming the "toggle" button. Notice that we also have a multiple value change in the ["Value Animation using tween" sample](#using-tween), and it behaves much better when we spam the "toggle" button.

Another possible way to animate multiple values at once would be by simply calling the animations at the same time. `updateTransition` is more useful to organize your code and make it more readable and "friendly".

## Infinite Value Animation (Repeatable)
This is one of the most used animations, the infinite repeatable animation, using the `InfiniteTransition`. Mostly used in loading animations, this animation repeats itself by restarting the animation from 0 or reversing when it finishes. This feature is similar to `updateTransition`, organizing and syncronizing multiple animations, but now with an infinite duration.

Using this is as simple as the previous features, we just need to assign a `rememberInfiniteTransition()` to a `val` and call `.animateColor()` or `.animateFloat()` for example. The `animationSpec` parameter now receives a `InfiniteRepeatableSpec` where we pass an `infiniteRepeatable` that also specifies the animation duration with `tween` or `spring` for example, and receives the `repeatMode` that determines how the animation will keep repeating (reversing or restarting).

### Simple Infinite Value Animation
Here we showcase an infinite animation of a spining Box that keps changing it's color between red and green.

It is achieved by simply animating the rotation value from 0 to 360 (`animateFloat()`), using the `repeatMode = Restart` (since 360 degrees is the same as 0 degrees for the rotation), and the color from `Color.Red` to `Color.Green` (`animateColor()`), using the `repeatMode = Reverse`.

Notice that if the `targetValue` and the `initialValue` represents the same state we can use the `Restart` mode without noticing when the animation restarted, otherwise the animation would "blink" back to the initial state. With the `Reverse` mode we will never have that problem.

[InfiniteValueAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/InfiniteValueAnimation.kt)

![InfiniteValueAnimation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/c92d1499-b767-46f8-9a8c-cbab134489d9)

Here we are using the `tween` with the `LinearEasing`, to achieve that linear animation, but we could use any other kind of easing. Try it out!

### Infinite Border Animation
Now things are getting prettier. Here we're going to showcase an infinite rotating border animation. One with a linear easing and another one using the `EaseInOutCirc` combined with multiple turns (target rotation value as 1080 degrees).

To achieve that we used the Jetpack Compose Ui Graphic component called `Brush`. By calling its function `.sweepGradient()` and passing a list of colors, it creates a sweep gradient with the given colors which is drawn clockwise. Then we draw a circle using the resulting `Brush` and animate the rotation angle, putting it right before the `drawContent()` (inside the `drawWithContent{}`) so we can have the border effect.

[InfiniteBorderAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/InfiniteBorderAnimation.kt)

![InfiniteBorderAnimation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/768c2997-a207-4885-bf7c-ff34a2dea2bf)

### Infinite Shimmer Animation (Loading Skeleton)
As mentioned before, loading animations are the most used infinite animations (at least in our opinion). The infinite animated border shown previously can also be considered a loading animation. But one of newest animations used for loading is the Shimmer (mostly used as skeletons).

Now we are going to showcase an infinite shimmer animation behaving as a loading placeholder.

For that we will also use `Brush` to get a gradient effect, but now using the `linearGradient()`. For the shimmer effect we will use a list of `Color.LightGray` changing a bit of the `alpha` to achieve a "shining effect". Finally we will animate the `linearGradient()`'s `end` offset where the target value is 10 times the content size, this high value will make the shimmer effect pass quickly through the whole content during the animation.

Then just for the purpose of the example we added an image and then added a surface over it with the shimmer applied to simulate a loading layout hidding the content behind it.

[InfiniteShimmerAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/InfiniteShimmerAnimation.kt)

![InfiniteShimmerAnimation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/06b064d9-52ad-44b5-a6ef-68ea6ecebac8)

### Infinite Shimmer Text Animation
Loading can be one of the most popular usecase for the infinite animation but it's not the only one. We can also make text looks way cooler!

Here we will demonstrate an infinite shimmer effect applied to a text.

The code is really similar to the previous animation, the only differences are that we animate both the `linearGradient()`'s `start` and `end` parameters and also use the text's fontsize as the `targetValue`. The fontsize is important to know the angle of the gradient and make it the same through different text sizes. When updating the the `linearGradient()`'s `start` and `end` parameters, the difference between them will always be the fontsize, that way we will have an effect of a static gradient image moving around the text. Finally the resulting `Brush` will be used in the TextStyle.

[InfiniteTextShimmerAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/InfiniteTextShimmerAnimation.kt)

![InfiniteTextShimmerAnimation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/a3ba9b4e-7773-4e81-8611-4e9ec9320006)

Notice we added an odd param called `shimmerLaps` to our shimmer function. It determines the amount of times the gradient passes through the text before the animation finishes. It is most useful if combined with a non linear easing, so you can notice the effect better. Go to the code and try to play around with the `animationDurationMilis`, `shimmerLaps` and `easing` params. (Check [easings.net](https://easings.net/))

## Content Animation
And last but not least, the content animation. Its usage is pretty straight forward, similar to the visibility animation. Just wrap your content around the `AnimatedContent` and pass the `targetState` to determine what changes to trigger the animation.

Notice that we no longer have a `enter` and `exit` param as we did with the `AnimatedVisibility`, but we can still achieve that using the `transitionSpec`. Just pass a animation as the enter animation and use the `togetherWith` to add the exit animation to it.

The `AnimatedContent` also takes a param called `content`, that brings the `targetState` with it, this should determine the content depending on the `targetState` we receive.

Pay attention to the fact that `transitionSpec` is inside a `AnimatedContentTransitionScope`, where you can access the `targetState` and `initialState`. `content` is also inside a scope, the `AnimatedContentScope`, where we have access to the `transition`, which have a lot of useful fields.

### Simple Content Animation
Here we showcase a simple content change animation going between a red layout and a green layout. 

When we click the "toggle" button, the state goes from `true` to `false` and the transition between contents is animated. 

We take advantage of the `AnimatedContentScope` to compare the `transition.currentState` and `transition.targetState`. If both are different, then we know that the animation is ongoing, and with that information we can block interactions with the "toggle" button until the animation finishes.

[ContentAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/ContentAnimation.kt)

![ContentAnimation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/2ae3b478-41b5-4ce9-8f75-a6492e82cb77)

### Content Count Animation
Now we're going to show case a more complex, but still simple, animation of a number counter.

We added to buttons, the "count up" and the "count down" button. Here the count itself is the `targetValue`, whenever it changes, the transition animation is triggered.

The `transitionSpec` is now a little bit more complex. We are combining the `slideIntoContainer` with the `fadeIn` using the `+` operator for the enter animation and then using the `togetherWith` to add the `slideOutOfContainer` `+` `fadeOut` for the exit animation. Finally we get the resulting animation and combine it with the `SizeTransform(clip = false)` using the `using` function, this will prevent the animation from being clipped by its layout limits.

We also take advantage of the `AnimatedContentTransitionScope` to compare the `targetState` with the `initialState`. If the `targetState` is greater than the `initialState` then we know that we're couting up, this way we can determine if the animation goes upwards or downwards.

We still take advantage of the `AnimatedContentScope` to block interactions with the buttons while the animation happens, as we did with the previous showcase.

[ContentCountAnimation.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/ui/ContentCountAnimation.kt)

![ContentCountAnimation](https://github.com/pedrotlf/JetpackComposeAnimationSample/assets/38842991/56476945-85d7-42aa-b426-fd0fb3a24577)

# Conclusion
With a lot of animation samples available, this project definitively serves as a template for your own animations or even to copy to be used in your own projects!

# Contribution
Feel free to create pull requests if you want to add more cool animations examples to show to the community!

If you want to create a new screen, just add it to the [AnimationScreens.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/main/java/com/pedrotlf/jetpackcomposeanimationsample/AnimationScreens.kt) and the sample app will automatically add a button to open it.
