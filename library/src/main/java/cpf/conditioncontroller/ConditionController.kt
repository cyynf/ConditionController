package cpf.conditioncontroller

import android.view.View

/**
 * 条件控制器
 */
class ConditionController {

    val views: ArrayList<Pair<View, (View) -> Boolean>> = arrayListOf()
    val result: ArrayList<Boolean> = arrayListOf()

    /**
     * 添加<T>
     * condition：条件，默认不为空
     */
    fun <T : View> add(
        view: T,
        condition: (T) -> Boolean = defaultCondition()
    ): ConditionController {
        @Suppress("UNCHECKED_CAST")
        views.add(Pair(view, condition as (View) -> Boolean))
        result.add(condition(view))
        return this
    }

    /**
     * 绑定视图
     * success：添加的<T>条件都满足时回调，默认 isEnabled = true
     * failure：添加的<T>条件有一个不满足时回调，默认 isEnabled = false
     */
    inline fun <E : View> bind(
        target: E,
        crossinline success: (E) -> Unit = { it.isEnabled = true },
        crossinline failure: (E) -> Unit = { it.isEnabled = false }
    ) {
        verify(target, success, failure)
        views.forEachIndexed { index, pair ->
            addConditionListener(pair.first) {
                result[index] = pair.second(pair.first)
                verify(target, success, failure)
            }
        }
    }

    /**
     * 验证添加的<T>是否都满足条件
     */
    inline fun <E : View> verify(
        target: E,
        success: (E) -> Unit,
        failure: (E) -> Unit
    ) {
        result.forEach {
            if (!it) {
                failure(target)
                return
            }
        }
        success(target)
    }
}