<div align="center">
  <img src="docs/icon.png" alt="输入法快捷切换图标" width="88" height="88" />
  <h1>RQuickIME</h1>
  <p>从 Android 控制中心一键打开系统输入法选择器，快速切换已启用的键盘。</p>
  <p>
    <img alt="Kotlin 2.2.10" src="https://img.shields.io/badge/Kotlin-2.2.10-7F52FF" />
    <img alt="Android 10+" src="https://img.shields.io/badge/Android-10%2B-7452B3" />
    <img alt="Android Gradle Plugin 9.0.0" src="https://img.shields.io/badge/AGP-9.0.0-4285F4" />
    <img alt="Version 1.0.0" src="https://img.shields.io/badge/Version-1.0.0-0F766E" />
    <a href="LICENSE"><img alt="The Unlicense" src="https://img.shields.io/badge/License-Unlicense-7452B3" /></a>
  </p>
  <p><a href="README_EN.md">English</a> · <a href="https://github.com/rurikacy/RQuickIME/releases/tag/v1.0.0">下载 v1.0.0</a></p>
</div>

---

## 使用方法

1. 在 Android 10 及以上手机安装并打开应用，点「打开系统输入法设置」，启用至少两种想使用的键盘。
2. 下拉控制中心，进入「编辑」，添加键盘图标「切换输入法」并保存。若找不到，Android 13 及以上还可从应用首页点「请求添加到控制中心」。
3. 需要换键盘时，点击磁贴，在系统弹窗中选择输入法，即可回到原应用继续输入。

## 技术原理

应用使用 Kotlin 和 Android 公开 API。它在 `AndroidManifest.xml` 中注册 `TileService` 并声明磁贴的名称和图标，供系统控制中心发现和展示。

点击磁贴后，`ImeTileService` 调用 `startActivityAndCollapse()` 收起控制中心，打开透明的 `PickerActivity`。这个窗口通过一个不可见的输入框与系统建立输入连接，随后调用 `InputMethodManager.showInputMethodPicker()`。Android 负责显示选择窗口并执行切换；选择或取消后，透明窗口退出。整个过程无需 Root、ADB 授权或无障碍权限。

已在 API 37 模拟器验证磁贴添加和系统弹窗。红米 K80 上的 HyperOS 表现待实机确认。

## 编译

在 Android Studio 中打开项目，使用 JDK 17、Android SDK Platform 36 和 Build-Tools 36.0.0。在项目根目录运行 `.\gradlew.bat assembleDebug`（Windows）或 `sh gradlew assembleDebug`（macOS / Linux）。生成的安装包位于 `app/build/outputs/apk/debug/app-debug.apk`。
