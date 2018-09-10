package info.hellovass.vuex.library.loadmore

interface ILoadMore {

    fun onLoadMoreBegin()

    fun onLoadMoreSucceed(hasMoreItems: Boolean)

    fun onLoadMoreFailed()
}