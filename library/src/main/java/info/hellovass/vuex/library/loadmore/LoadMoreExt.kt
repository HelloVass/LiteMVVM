package info.hellovass.vuex.library.loadmore

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import info.hellovass.vuex.library.loadmore.GridLayoutManagerImpl
import info.hellovass.vuex.library.loadmore.LinearLayoutManagerImpl
import info.hellovass.vuex.library.loadmore.StaggeredLayoutManagerImpl

fun isOnBottom(recyclerView: RecyclerView?, dx: Int, dy: Int): Boolean {

    val layoutManager = recyclerView?.layoutManager

    return when (layoutManager) {

        is LinearLayoutManager -> {
            LinearLayoutManagerImpl(layoutManager).isOnBottom()
        }
        is GridLayoutManager -> {
            GridLayoutManagerImpl(layoutManager).isOnBottom()
        }
        is StaggeredGridLayoutManager -> {
            StaggeredLayoutManagerImpl(layoutManager).isOnBottom()
        }
        else -> {
            false
        }
    }
}