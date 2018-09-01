package info.hellovass.vuex.demo.betteradapter

import android.view.View
import info.hellovass.vuex.demo.ju.Ju

/**
 * 访问者
 */
interface Visitor {

    fun visitJu(ju: Ju): Int

    fun holder(viewType: Int, view: View): BetterAdapter.BetterVH<*>
}