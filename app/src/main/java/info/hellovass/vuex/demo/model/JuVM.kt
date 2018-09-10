package info.hellovass.vuex.demo.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import info.hellovass.vuex.demo.ju.FooterHandler
import info.hellovass.vuex.demo.ju.RefreshHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class JuVM : ViewModel() {

    private val _uiStateModel = MutableLiveData<UIStateModel>()

    private val _refreshStateModel = MutableLiveData<RefreshStateModel>()

    private val _footerStateModel = MutableLiveData<FooterStateModel>()

    private val repo: JuRepo = JuRepo()

    private val juList = arrayListOf<Ju>()

    private var pageNum: Int = 1

    fun getUIStateModel() = _uiStateModel

    fun getRefreshStateModel() = _refreshStateModel

    fun getFooterStateModel() = _footerStateModel

    fun loadData(pullToRefresh: Boolean) {

        // 根据是否为刷新操作创建处理器
        val actionHandler = when {
            pullToRefresh -> {
                pageNum = 1
                RefreshHandler(_refreshStateModel)
            }
            else -> {
                FooterHandler(_footerStateModel)
            }
        }

        // 进入 loading 状态
        actionHandler.loading()

        // 加载数据
        repo.getJus(pageNum)
                .map { it ->
                    // 1.cur
                    val cur = ArrayList(this.juList)
                    // 2.handle data
                    actionHandler.handle(this.juList, it)
                    // 3.next
                    val next = ArrayList(this.juList)
                    // 4.calculate result
                    UIStateModel.success(cur, next, it.count())
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    // 更新状态
                    actionHandler.succeed(it.hasMore())
                    // 更新列表
                    _uiStateModel.value = it
                    // 页数加 1
                    pageNum++
                }, { _ ->
                    actionHandler.failed()
                })
    }
}

