package info.hellovass.vuex.demo.ju

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import info.hellovass.vuex.demo.R
import info.hellovass.vuex.demo.betteradapter.BetterAdapter
import info.hellovass.vuex.demo.betteradapter.visitor.JuVistor
import info.hellovass.vuex.demo.ext.obtainVM
import info.hellovass.vuex.demo.model.FooterStateModel
import info.hellovass.vuex.demo.model.JuVM
import info.hellovass.vuex.library.loadmore.ILoadMore
import info.hellovass.vuex.library.loadmore.LoadMore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var vm: JuVM? = null

    private var viewAdapter: BetterAdapter? = null

    private var iLoadMore: ILoadMore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create VM
        vm = obtainVM(JuVM::class.java)

        // init Recyclerview
        initRecyclerViewComponent(rcvList)

        // init refresh component
        initRefreshComponent(refreshLayout)

        // init loadMore component
        initLoadMoreComponent(refreshLayout, rcvList)

        // 观察 VM 中的 state
        observeVMState(vm)

        // 加载第一页
        vm?.loadData(false)
    }

    private fun initRecyclerViewComponent(recyclerView: RecyclerView) {

        recyclerView.apply {
            // set layoutManager
            layoutManager = LinearLayoutManager(this@MainActivity)
            // set adapter
            viewAdapter = BetterAdapter(arrayListOf(), JuVistor())
            adapter = viewAdapter
        }
    }

    private fun initRefreshComponent(refreshLayout: SwipeRefreshLayout) {

        refreshLayout.apply {
            setOnRefreshListener { vm?.loadData(true) }
        }
    }

    private fun initLoadMoreComponent(refreshLayout: SwipeRefreshLayout, rcvList: RecyclerView) {

        iLoadMore = LoadMore.Builder(this)
                .recyclerView(rcvList)
                .loadMoreListener {
                    if (!refreshLayout.isRefreshing) {
                        vm?.loadData(false)
                    }
                }
                .build()
    }

    private fun observeVMState(vm: JuVM?) {

        vm?.let { juVM ->
            juVM.getUIStateModel().observe(this, Observer { uiStateModel ->
                uiStateModel?.let { it ->
                    viewAdapter!!.setItems(it.latest)
                    it.diffResult!!.dispatchUpdatesTo(viewAdapter!!)
                }
            })
        }

        vm?.let { juVM ->
            juVM.getRefreshStateModel().observe(this, Observer { refreshStateModel ->
                refreshStateModel?.let { it ->
                    refreshLayout.isRefreshing = it.isRefreshing
                }
            })
        }

        vm?.let { juVM ->
            juVM.getFooterStateModel().observe(this, Observer { footerStateModel ->
                footerStateModel?.let { it ->
                    when (it.status) {
                        FooterStateModel.Status.Loading ->
                            iLoadMore?.onLoadMoreBegin()
                        FooterStateModel.Status.NoMore ->
                            iLoadMore?.onLoadMoreSucceed(false)
                        FooterStateModel.Status.Completed ->
                            iLoadMore?.onLoadMoreSucceed(true)
                        FooterStateModel.Status.Failed ->
                            iLoadMore?.onLoadMoreFailed()
                    }
                }
            })
        }
    }
}
