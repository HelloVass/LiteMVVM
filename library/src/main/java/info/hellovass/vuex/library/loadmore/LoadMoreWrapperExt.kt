package info.hellovass.vuex.library.loadmore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.hellovass.vuex.library.loadmore.LoadMoreWrapper
import info.hellovass.vuex.library.loadmore.Status

fun LoadMoreWrapper.isLoadingType(position: Int): Boolean {

    return this.status == Status.Loading && position == adapter.itemCount
}

fun LoadMoreWrapper.isNoMoreType(position: Int): Boolean {

    return this.status == Status.NoMore && position == adapter.itemCount
}

fun LoadMoreWrapper.isErrorType(position: Int): Boolean {

    return this.status == Status.Error && position == adapter.itemCount
}

fun LoadMoreWrapper.inflate(parent: ViewGroup, layoutResId: Int): View {

    return LayoutInflater.from(this.context).inflate(layoutResId, parent, false)
}