package info.hellovass.vuex.demo.betteradapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.hellovass.vuex.demo.betteradapter.visitable.Visitable
import info.hellovass.vuex.demo.betteradapter.visitor.Visitor

class BetterAdapter(private var items: List<Visitable>, private val visitor: Visitor) : RecyclerView.Adapter<BetterAdapter.BetterVH<Visitable>>() {

    override fun getItemViewType(position: Int): Int = items[position].accept(visitor)

    override fun getItemCount(): Int = items.count()

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetterVH<Visitable> {

        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent,
                false)
        return visitor.createVH(viewType, view) as BetterVH<Visitable>
    }

    override fun onBindViewHolder(holder: BetterVH<Visitable>, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: List<Visitable>?) {
        items?.let {
            this.items = it
        }
    }

    abstract class BetterVH<in T>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: T)
    }
}