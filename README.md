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
	implementation 'com.github.cyynf:ConditionController:1.1.1'
```
简单使用
``` kotlin
ConditionController()
            .add(phone)
            .add(password)
            .add(agreement)
            .bind(submit)
            .commit()
```
自定义条件
``` kotlin
ConditionController()
            .add(phone) {it.length() == 11}
            .add(password)
            .add(agreement)
            .bind(submit) {
                submit.isEnabled = it
                if (it) {
                    submit.setBackgroundColor(Color.GREEN)
                } else {
                    submit.setBackgroundColor(Color.RED)
                }
            }
            .commit()
```