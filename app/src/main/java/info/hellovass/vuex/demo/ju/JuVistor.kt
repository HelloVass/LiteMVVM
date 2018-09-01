package info.hellovass.vuex.demo.ju

import android.view.View
import info.hellovass.vuex.demo.R
import info.hellovass.vuex.demo.betteradapter.BetterAdapter
import info.hellovass.vuex.demo.betteradapter.Visitor

class JuVistor : Visitor {

    override fun visitJu(ju: Ju): Int = R.layout.listitem_ju

    override fun holder(viewType: Int, view: View): BetterAdapter.BetterVH<*> {
        return when (viewType) {
            R.layout.listitem_ju -> JuHolder(view)
            else -> throw IllegalStateException("")
        }
    }
}