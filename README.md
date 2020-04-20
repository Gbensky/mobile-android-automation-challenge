# qa-automation-android-test

### Setup:
  1) Install Android Studio as described [here](https://developer.android.com/studio/install)
  
  2) [Fork](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository) the repository to local.
  
  3) Open the repository in Android Studio and build the application. You can choose to build it on a physical device or an emulator.

### Additional Info:
   **Username**: automation@gymondo.de
   
   **Password**: automation

### Execution Instructions

1) Add ANDROID_HOME to your PATH (directory of the android sdk) in your .bash_profile file
2) Add the following line to your .bash_profile file
```bash
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```
3) Connect a physical device to your system (ensure USB debugging is enabled)
4) Open project in Android Studio
5) Open terminal in Android Studio
6) Run the following command in terminal

```bash
adb shell am instrument -w com.example.android.gymondoautomationtest.test/androidx.test.runner.AndroidJUnitRunner
```

