# ConditionController
条件控制器，支持TextView，EditText，Button，Checkbox，RadioButton，SeekBar，ProgressBar等
![image](https://github.com/cyynf/ConditionController/blob/master/image.gif)

## Usage
添加 jitPack.io 仓库
``` groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
添加依赖
``` groovy
	implementation 'com.github.cyynf:ConditionController:1.0.0'
```
简单使用
``` kotlin
ConditionController<TextView>()
            .add(phone)
            .add(password)
            .add(agreement)
            .bind(submit)
```
自定义条件
``` kotlin
ConditionController<TextView>()
            .add(phone) {
                // 手机号码必须为13位
                it.length() == 13
            }
            .add(password)
            .add(agreement)
            .bind(
                target = submit,
                success = {
                    it.setBackgroundColor(Color.GREEN)
                },
                failure = {
                    it.setBackgroundColor(Color.RED)
                })
```