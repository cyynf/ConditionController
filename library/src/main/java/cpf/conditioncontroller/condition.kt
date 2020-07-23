package cpf.conditioncontroller

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.TextView

/**
 * Author: cpf
 * Date: 2020/7/23
 * Email: cpf4263@gmail.com
 */

/**
 * 默认条件
 */
fun <T> defaultCondition(): (T) -> Boolean {
    return {
        when (it) {
            is ProgressBar -> it.progress in 1..it.max
            is CompoundButton -> it.isChecked
            is TextView -> !it.text.isNullOrBlank()
            else -> true
        }
    }
}

/**
 * 状态变化监听
 */
@SuppressLint("ClickableViewAccessibility")
inline fun addConditionListener(view: View, crossinline callBack: () -> Unit) {
    when (view) {
        is ProgressBar -> {
            view.setOnTouchListener { _, _ ->
                callBack()
                return@setOnTouchListener false
            }
        }
        is CompoundButton -> {
            view.setOnCheckedChangeListener { _, _ ->
                callBack()
            }
        }
        is TextView -> {
            view.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    callBack()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }
    }
}

