package cpf.conditioncontroller

import android.view.View

/**
 * 条件控制器
 */
class ConditionController {

    private val views: ArrayList<Pair<View, (View) -> Boolean>> = arrayListOf()
    private val cacheResults: ArrayList<Boolean> = arrayListOf()
    private var callbacks: ArrayList<(Boolean) -> Unit> = arrayListOf()
    private var result: Boolean? = null
    private lateinit var conditionWatcher: ConditionWatcher

    /**
     * 自定义ConditionWatcher
     */
    fun setConditionWatcher(conditionWatcher: ConditionWatcher): ConditionController {
        this.conditionWatcher = conditionWatcher
        return this
    }

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
        cacheResults.add(condition(view))
        return this
    }

    /**
     * 绑定视图
     * callback：(Boolean) -> Unit，默认设置isEnabled
     */
    fun <E : View> bind(
        target: E,
        callback: (Boolean) -> Unit = defaultCallback(target)
    ): ConditionController {
        callbacks.add(callback)
        return this
    }

    /**
     * 新增提交方法，支持多bind视图
     */
    fun commit() {
        if (!this::conditionWatcher.isInitialized) {
            conditionWatcher = DefaultConditionWatcher()
        }
        views.forEachIndexed { index, pair ->
            conditionWatcher.addWatcher(pair.first) {
                cacheResults[index] = pair.second(pair.first)
                verify()
            }
        }
        verify()
    }

    /**
     * 强制更新
     */
    fun forceUpdate() {
        views.forEachIndexed { index, pair ->
            cacheResults[index] = pair.second(pair.first)
        }
        verify()
    }

    /**
     * 验证缓存的结果是否都满足条件
     */
    private fun verify() {
        val newResult = cacheResults.all { it }
        if (newResult != result) {
            callbacks.forEach { it.invoke(newResult) }
            result = newResult
        }
    }
}