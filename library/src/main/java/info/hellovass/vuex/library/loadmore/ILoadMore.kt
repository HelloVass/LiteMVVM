package info.hellovass.vuex.library.loadmore

interface ILoadMore {

    fun onLoadMoreBegin()

    fun onLoadMoreSucceed(hasMore: Boolean)

    fun onLoadMoreFailed()
}