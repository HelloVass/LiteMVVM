package info.hellovass.vuex.demo.ju

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import info.hellovass.vuex.demo.R
import info.hellovass.vuex.demo.betteradapter.BetterAdapter
import info.hellovass.vuex.demo.betteradapter.visitor.JuVistor
import info.hellovass.vuex.demo.model.JuVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var vm: JuVM? = null

    private var viewAdapter: BetterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create ViewModel
        vm = obtainVM()

        initRefreshComponent()

        // init List Component
        initRecyclerViewComponent()

        // load data
        vm?.loadData()
    }

    private fun obtainVM() = ViewModelProviders.of(this).get(JuVM::class.java)

    private fun initRefreshComponent() {

    }

    private fun initRecyclerViewComponent() {

        rcvList.apply {
            // set layoutManager
            layoutManager = LinearLayoutManager(this@MainActivity)
            // set Animator
            itemAnimator = DefaultItemAnimator()
            // set adapter
            viewAdapter = BetterAdapter(arrayListOf(), JuVistor())
            adapter = viewAdapter
        }

        vm?.let { juVM ->
            juVM.juDiffResult().observe(this, Observer { juDiffResult ->
                juDiffResult?.let { pair ->
                    viewAdapter?.apply {
                        setItems(pair.first)
                        pair.second!!.dispatchUpdatesTo(this)
                    }
                }
            })
        }
    }
}
