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
