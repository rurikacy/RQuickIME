<div align="center">
  <img src="docs/icon.png" alt="RQuickIME icon" width="88" height="88" />
  <h1>RQuickIME</h1>
  <p>Open the system keyboard picker from Android Quick Settings.</p>
  <p>
    <img alt="Kotlin 2.2.10" src="https://img.shields.io/badge/Kotlin-2.2.10-7F52FF" />
    <img alt="Android 10+" src="https://img.shields.io/badge/Android-10%2B-7452B3" />
    <img alt="Android Gradle Plugin 9.0.0" src="https://img.shields.io/badge/AGP-9.0.0-4285F4" />
    <img alt="Version 1.0.0" src="https://img.shields.io/badge/Version-1.0.0-0F766E" />
    <a href="LICENSE"><img alt="The Unlicense" src="https://img.shields.io/badge/License-Unlicense-7452B3" /></a>
  </p>
  <p><a href="README.md">简体中文</a> · <a href="https://github.com/rurikacy/RQuickIME/releases/tag/v1.0.0">Download v1.0.0</a></p>
</div>

---

## Using the tile

1. Install the signed APK on Android 10 or later. Open RQuickIME and tap **打开系统输入法设置** (Keyboard settings) to enable the keyboards you want to switch between.
2. Open Quick Settings (Control Center on HyperOS), tap **Edit**, and add the keyboard tile labeled **切换输入法** (Switch keyboard). On Android 13 or later, you can also tap **请求添加到控制中心** in the app to request the tile.
3. While typing, open Quick Settings and tap the tile. Choose a keyboard in the system picker to return to your app.

## How it works

Android discovers the Kotlin tile service through its manifest declaration. When you tap the tile, the service calls `startActivityAndCollapse()` to close Quick Settings and open a transparent activity. Once its input field establishes an input connection, the activity calls `InputMethodManager.showInputMethodPicker()`. Android handles keyboard selection. The app requires no root access, ADB authorization, or accessibility service.

## Build

Open the project in Android Studio with JDK 17, Android SDK Platform 36, and Build-Tools 36.0.0. From the project root, run `.\gradlew.bat assembleDebug` on Windows or `sh gradlew assembleDebug` on macOS or Linux. The debug APK is written to `app/build/outputs/apk/debug/app-debug.apk`.
