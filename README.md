# 这是一个手表DPI工具 

## How to use:
安装release里面的apk 然后执行命令  ([adb下载链接(Googel)](https://developer.android.google.cn/tools/releases/platform-tools?hl=zh-cn#downloads.html))  
`adb shell pm grant cn.rlint.dpihelper android.permission.WRITE_SECURE_SETTINGS`  

一次电脑授权就可以使用 OPPO Watch 4 Pro A11 实测是这样的

## Tips:
1. 由于无法通知系统 所以修改无法实时生效
2. 关于初始的DPI 我并不知道获取初始DPI的方法 所以你第一次打开应用时的DPI就是原始DPI 如果获取不到默认320
