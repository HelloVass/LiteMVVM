package info.hellovass.vuex.demo.model

import android.support.v7.util.DiffUtil
import info.hellovass.vuex.demo.ju.JuDiffCallback

data class UIStateModel(val latest: List<Ju>, val diffResult: DiffUtil.DiffResult?, val count: Int) {

    companion object {

        fun success(cur: List<Ju>, next: List<Ju>, count: Int): UIStateModel {
            val callback = JuDiffCallback(cur, next)
            val diffResult = DiffUtil.calculateDiff(callback)
            return UIStateModel(next, diffResult, count)
        }
    }

    /**
     * 判断是否还有更多数据，如果下一页的 size 小于 10，则没有更多数据
     */
    fun hasMore(): Boolean = count >= 10
}