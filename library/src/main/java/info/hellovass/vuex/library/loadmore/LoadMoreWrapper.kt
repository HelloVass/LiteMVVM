package info.hellovass.vuex.library.loadmore

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import info.hellovass.vuex.library.R
import kotlinx.android.synthetic.main.listitem_loadmore.view.*

class LoadMoreWrapper(val context: Context, val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, var status: Status = Status.Completed) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        private const val TYPE_LOAD_MORE = Int.MAX_VALUE
    }

    override fun getItemCount(): Int {

        return when (status) {
            Status.Loading, Status.Error, Status.NoMore -> {
                adapter.itemCount + 1
            }
            else -> {
                adapter.itemCount
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when {
            isLoadingType(position) || isErrorType(position) || isNoMoreType(position) -> {
                TYPE_LOAD_MORE
            }
            else -> {
                adapter.getItemViewType(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_LOAD_MORE -> {
                LoadMoreVH(inflate(parent, R.layout.listitem_loadmore))
            }
            else -> {
                adapter.onCreateViewHolder(parent, viewType)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        return when (getItemViewType(position)) {
            TYPE_LOAD_MORE -> {
                (holder as LoadMoreVH).onBindViewHolder(status)
            }
            else -> {
                adapter.onBindViewHolder(holder, position)
            }
        }
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)

        adapter.setHasStableIds(hasStableIds)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)

        adapter.onViewRecycled(holder)
    }

    fun notifyStatusChanged(status: Status) {

        if (this.status == status)
            return
        else
            this.status = status

        this.notifyDataSetChanged()
    }

    class LoadMoreVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBindViewHolder(status: Status) {

            itemView.tvTitle.apply {
                text = status.title ?: ""
            }
        }
    }
}