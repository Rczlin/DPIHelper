# 这是一个手表DPI工具

## 免责声明
1. 修改设备 DPI 可能导致部分应用或系统界面显示异常，请谨慎使用并自行承担风险。
2. 使用本工具前请确保已备份重要数据，因使用本工具造成的设备故障、数据丢失、系统不稳定等后果，开发者不承担任何责任。
3. 本工具仅支持已知兼容的设备及系统版本，因设备差异、厂商定制等原因导致功能失效或异常，开发者不保证适配所有设备。
4. 用户需自行确保操作合法合规，因违规操作导致的任何法律责任由用户自行承担。
6. 本工具涉及部分系统权限操作，如需授权请谨慎操作，切勿用于非法用途。
6. 由于系统或第三方软件更新可能影响本工具功能，开发者不保证持续兼容和维护。
7. 本工具不收集任何用户数据，亦不向第三方发送信息。请放心使用。

## How to use:
安装`release`里面的apk 然后执行命令  ([adb下载链接(Googel)](https://developer.android.google.cn/tools/releases/platform-tools?hl=zh-cn#downloads.html))  
   ```bash
   adb shell pm grant cn.rlint.dpihelper android.permission.WRITE_SECURE_SETTINGS
   ```

一次电脑授权就可以使用 OPPO Watch 4 Pro A11 实测是这样的

## Tips:
1. 由于无法通知系统 所以修改无法实时生效
2. 关于初始的DPI 我并不知道获取初始DPI的方法 所以你第一次打开应用时的DPI就是原始DPI 如果获取不到默认320
3. 如果DPI调错了 但是你无法使用软件 并且ADB还能链接 请尝试执行恢复
   ```bash
   adb shell wm density reset
   ```
   


