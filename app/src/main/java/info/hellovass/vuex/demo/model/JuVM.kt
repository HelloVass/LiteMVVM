package info.hellovass.vuex.demo.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class JuVM : ViewModel() {

    private val uiStateModel = MutableLiveData<UIStateModel>()

    private val footerStateModel = MutableLiveData<FooterStateModel>()

    private val repo: JuRepo = JuRepo()

    private val juList = arrayListOf<Ju>()

    private var pageNum: Int = 1

    fun getUIStateModel() = uiStateModel

    fun getFooterStateModel() = footerStateModel

    fun loadData() {

        footerStateModel.postValue(FooterStateModel.loading())

        repo.getJus(pageNum)
                .map { it ->
                    // cur
                    val cur = ArrayList(this.juList)
                    // add
                    this.juList.addAll(it)
                    // next
                    val next = ArrayList(this.juList)
                    // result
                    UIStateModel.success(cur, next)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    uiStateModel.value = it
                    footerStateModel.value = FooterStateModel.succeed(it.itemCount())
                    pageNum++
                }, { _ ->
                    footerStateModel.value = FooterStateModel.failed()
                })
    }
}

