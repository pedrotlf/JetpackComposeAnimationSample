# Compose Preview Screenshot Testing
Screenshot testing is a powerful technique for ensuring UI consistency in your applications. By capturing a reference screenshot of each screen or component, you can compare it against newly generated screenshots after any code changes. This process helps detect unintended visual changes, ensuring your UI remains consistent and functions as expected.

## About the Tool
The Compose Preview Screenshot Testing tool combines the simplicity and features of composable previews with the productivity gains of running host-side screenshot tests. Compose Preview Screenshot Testing is designed to be as easy to use as composable previews.

[Check Google's Compose Preview Screenshot Testing documentation](https://developer.android.com/studio/preview/compose-screenshot-testing)

### How It Works
1. **Reference Screenshots**: A baseline screenshot is created and stored as a reference for each screen or component.
2. **Test Execution**: During test runs, new screenshots are captured for the same screens or components.
3. **Comparison**: The newly generated screenshots are compared to the reference screenshots. Any discrepancies indicate visual changes in the UI.
4. **Test Logs**: If a test fails, an HTML report is generated, including information about which tests failed and highlights the regions of the screenshots that differ, making it easier to identify and resolve UI inconsistencies.

# Setting Up Tests
Setting up the tests is really easy. Lets see how we set up some tests for all the screens we currently have in our app. 

## 1. Preparing the project
We are not going to dive into setup details, but it is important to mention that we needed to prepare our project in order to be able to use Compose Preview Screenshot Testing tool.

First of all we needed to make sure that our project had Kotlin 1.9.20 or higher and Android Gradle 8.5.0-beta01 or higher. If your current project does not have that and you want to use the testing tool, make sure to update your project before doing that.

[Here's a link to the official docs section where you'll find a simple guide to setup/enable the testing tool.](https://developer.android.com/studio/preview/compose-screenshot-testing#setup)

## 2. Creating tests
First lets create a directory called `screenshotTest\kotlin` in the `ProjectName\app\src` folder. If you already enabled the testing tool, Android Studio will recommend the name as you begin to type it when creating the directory.

Now we create a .kt file that will contain the tests we want. In our project we called it [Previews.kt](https://github.com/pedrotlf/JetpackComposeAnimationSample/blob/main/app/src/screenshotTest/kotlin/Previews.kt). Here we will add `@Preview @Composable` functions, Android Studio will automatically detect and use those functions to make the screenshot tests. Here's a code example of a function:

```kotlin
@Preview
@Composable
private fun VisibilityAnimationPreview() {
    JetpackComposeAnimationSampleTheme {
        VisibilityAnimation()
    }
}
```

With that simple function we can test the `VisibilityAnimation` screen.

Add as many screenshot tests as you like. You can also create different directories and files, if they are all inside `screenshotTest\kotlin` Android Studio will also detect the tests properly. If you have a big project with different packages, you can just replicate the folder structure there and Android Studio will use that structure when generating the logs and screenshot references.

## 3. Creating screenshot reference
To create the screenshot reference we simply need to run the gradle task `updateDebugScreenshotTest`. You can run it in the terminal using `./gradlew` command or you can find it in the Gradle task list that Android Studio generates when you sync the project with gradle files.

After the task is done you'll notice that Android Studio automatically creates a `screenshotTest` folder inside the `debug [main]` directory. There you can find all the screenshots that Android Studio will use as reference when testing.

And with that you are all setup to run your screenshot tests.

## 4. Running the tests
To run the tests you just need to run the `validateDebugScreenshotTest`. You can run it in the terminal using `./gradlew` command or you can find it in the Gradle task list that Android Studio generates when you sync the project with gradle files. With that, Android Studio will take new screenshots and compare them with their respective references.

## 5. Checking the results
Once the validation task is finished, you can now find the results in the `app\build\reports` directory. If you open the `index.html` in a browser, you can see the result in details. 

To serve as an example, we intentionally added an "y" to the end of our button text in the `VisibilityAnimation` screen and ran the tests. Here's what we got from the `index.html`.

![image](https://github.com/user-attachments/assets/b071db83-4dd8-4aea-ae89-4095de22577d)
![image](https://github.com/user-attachments/assets/f0eed19c-72ec-4cac-b6ac-617a35f373a5)

Notice that it detected the difference and even marked the exact place where it differs from the original screenshot.

# Conclusion
With that we can now add tests that ensure our app UI is not unintentionally impacted by code changes.
