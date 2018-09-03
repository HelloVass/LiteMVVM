package info.hellovass.vuex.demo.betteradapter.visitor

import android.view.View
import info.hellovass.vuex.demo.R
import info.hellovass.vuex.demo.betteradapter.BetterAdapter
import info.hellovass.vuex.demo.betteradapter.vh.JuHolder
import info.hellovass.vuex.demo.model.Ju

class JuVistor : Visitor {

    override fun visitJu(ju: Ju): Int = R.layout.listitem_ju

    override fun createVH(viewType: Int, view: View): BetterAdapter.BetterVH<*> {
        return when (viewType) {
            R.layout.listitem_ju -> JuHolder(view)
            else -> throw IllegalStateException("")
        }
    }
}