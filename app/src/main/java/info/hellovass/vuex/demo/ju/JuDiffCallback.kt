package info.hellovass.vuex.demo.ju

import android.support.v7.util.DiffUtil
import info.hellovass.vuex.demo.model.Ju

class JuDiffCallback(private val old: List<Ju>, private val new: List<Ju>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = old.count()

    override fun getNewListSize(): Int = new.count()

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition].id == new[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition] == new[newItemPosition]
}