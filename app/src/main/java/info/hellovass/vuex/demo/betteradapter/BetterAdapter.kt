package info.hellovass.vuex.demo.betteradapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BetterAdapter(private val items: List<Visitable>, private val visitor: Visitor) : RecyclerView.Adapter<BetterAdapter.BetterVH<Visitable>>() {

    override fun getItemViewType(position: Int): Int = items[position].accept(visitor)

    override fun getItemCount(): Int = items.count()

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetterVH<Visitable> {

        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent,
                false)
        return visitor.holder(viewType, view) as BetterVH<Visitable>
    }

    override fun onBindViewHolder(holder: BetterVH<Visitable>, position: Int) {
        holder.bind(items[position])
    }

    abstract class BetterVH<in T>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: T)
    }
}