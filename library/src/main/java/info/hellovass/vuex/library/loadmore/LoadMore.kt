package info.hellovass.vuex.library.loadmore

import android.content.Context
import android.support.v7.widget.RecyclerView

class LoadMore(private val context: Context,
               private val recyclerView: RecyclerView,
               private val loadMoreListener: () -> Unit) : ILoadMore {

    private var isFailed: Boolean = false

    private var isLoading: Boolean = false

    private var hasMore: Boolean = true

    private lateinit var userAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    private lateinit var loadMoreWrapper: LoadMoreWrapper

    private lateinit var myAdapterDataObserver: RecyclerView.AdapterDataObserver

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (isOnBottom(recyclerView, dx, dy)) {
                onReachBottom()
            }
        }
    }

    private constructor(builder: Builder) : this(builder.context, builder.recyclerView, builder.loadMoreListener) {

        this.userAdapter = recyclerView.adapter!!
        this.loadMoreWrapper = LoadMoreWrapper(this.context, userAdapter, Status.Completed)
        this.myAdapterDataObserver = MyAdapterDataObserver(this.loadMoreWrapper)

        applyUserAdapterDataObserver(this.userAdapter, this.myAdapterDataObserver)
        applyLoadMoreWrapperAdapter(this.recyclerView, this.loadMoreWrapper)
        applyOnScrollListener(this.recyclerView, this.scrollListener)
    }


    private fun applyOnScrollListener(recyclerView: RecyclerView, scrollListener: RecyclerView.OnScrollListener) = recyclerView.apply {
        addOnScrollListener(scrollListener)
    }

    private fun applyUserAdapterDataObserver(userAdapter: RecyclerView.Adapter<*>, observer: RecyclerView.AdapterDataObserver) = userAdapter.apply {
        registerAdapterDataObserver(observer)
    }

    private fun applyLoadMoreWrapperAdapter(recyclerView: RecyclerView, loadMoreWrapper: RecyclerView.Adapter<*>) = recyclerView.apply {
        this.adapter = loadMoreWrapper
    }

    private fun onReachBottom() {

        if (isFailed) {
            return
        }

        if (isLoading) {
            return
        }

        if (!hasMore) {
            return
        }
        loadMoreListener()
    }

    override fun onLoadMoreBegin() {

        isFailed = false
        isLoading = true
        loadMoreWrapper.notifyStatusChanged(Status.Loading)
    }

    override fun onLoadMoreSucceed(hasMoreItems: Boolean) {

        isFailed = false
        isLoading = false
        hasMore = hasMoreItems

        if (!hasMoreItems) {
            loadMoreWrapper.notifyStatusChanged(Status.NoMore)
        }
    }

    override fun onLoadMoreFailed() {

        isFailed = true
        isLoading = false
        loadMoreWrapper.notifyStatusChanged(Status.Error)
    }

    override fun resetLoadMore() {

        isFailed = false
        isLoading = false
        hasMore = true
        loadMoreWrapper.notifyStatusChanged(Status.Completed)
    }

    class Builder(val context: Context) {

        lateinit var recyclerView: RecyclerView
            private set

        lateinit var loadMoreListener: () -> Unit
            private set

        fun recyclerView(recyclerView: RecyclerView) = apply {
            this.recyclerView = recyclerView
        }

        fun loadMoreListener(loadMoreListener: () -> Unit) = apply {
            this.loadMoreListener = loadMoreListener
        }

        fun build() = LoadMore(this)
    }
}