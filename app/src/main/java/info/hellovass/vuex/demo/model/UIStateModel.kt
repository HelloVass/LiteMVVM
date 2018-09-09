package info.hellovass.vuex.demo.model

import android.support.v7.util.DiffUtil
import info.hellovass.vuex.demo.ju.JuDiffCallback

data class UIStateModel(val latest: List<Ju>, val diffResult: DiffUtil.DiffResult?) {

    companion object {

        fun success(cur: List<Ju>, next: List<Ju>): UIStateModel {
            val callback = JuDiffCallback(cur, next)
            val diffResult = DiffUtil.calculateDiff(callback)
            return UIStateModel(next, diffResult)
        }
    }

    fun itemCount(): Int = latest.count()
}