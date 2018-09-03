package info.hellovass.vuex.demo.betteradapter.vh

import android.view.View
import info.hellovass.vuex.demo.betteradapter.BetterAdapter
import info.hellovass.vuex.demo.model.Ju
import kotlinx.android.synthetic.main.listitem_ju.view.*

class JuHolder(val view: View) : BetterAdapter.BetterVH<Ju>(view) {

    override fun bind(item: Ju) {

        view.tvTitle.apply {
            text = item.title
        }
    }
}