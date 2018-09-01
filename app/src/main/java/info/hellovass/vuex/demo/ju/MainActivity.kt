package info.hellovass.vuex.demo.ju

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import info.hellovass.vuex.demo.R
import info.hellovass.vuex.demo.betteradapter.BetterAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
    }

    private fun initData() {

        rcvList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = BetterAdapter(arrayListOf(), JuVistor())
        }
    }
}
