package info.hellovass.vuex.demo.betteradapter.visitor

import android.view.View
import info.hellovass.vuex.demo.betteradapter.BetterAdapter
import info.hellovass.vuex.demo.model.Ju

/**
 * 访问者
 */
interface Visitor {

    fun visitJu(ju: Ju): Int

    fun createVH(viewType: Int, view: View): BetterAdapter.BetterVH<*>
}