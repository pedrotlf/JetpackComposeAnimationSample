# Compose Preview Screenshot Testing
Screenshot testing is a powerful technique for ensuring UI consistency in your applications. By capturing a reference screenshot of each screen or component, you can compare it against newly generated screenshots after any code changes. This process helps detect unintended visual changes, ensuring your UI remains consistent and functions as expected.

## About the Tool
The Compose Preview Screenshot Testing tool combines the simplicity and features of composable previews with the productivity gains of running host-side screenshot tests. Compose Preview Screenshot Testing is designed to be as easy to use as composable previews.

### How It Works
1. **Reference Screenshots**: A baseline screenshot is created and stored as a reference for each screen or component.
2. **Test Execution**: During test runs, new screenshots are captured for the same screens or components.
3. **Comparison**: The newly generated screenshots are compared to the reference screenshots. Any discrepancies indicate visual changes in the UI.
4. **Test Logs**: If a test fails, an HTML report is generated, including information about which tests failed and highlights the regions of the screenshots that differ, making it easier to identify and resolve UI inconsistencies.