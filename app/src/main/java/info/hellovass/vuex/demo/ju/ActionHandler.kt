package info.hellovass.vuex.demo.ju

import android.arch.lifecycle.MutableLiveData
import info.hellovass.vuex.demo.model.FooterStateModel
import info.hellovass.vuex.demo.model.Ju
import info.hellovass.vuex.demo.model.RefreshStateModel

interface ActionHandler {
    fun loading()
    fun handle(juList: MutableList<Ju>, it: List<Ju>)
    fun succeed(hasMore: Boolean)
    fun failed()
}

class RefreshHandler(private val _refreshStateModel: MutableLiveData<RefreshStateModel>) : ActionHandler {

    override fun failed() {
        _refreshStateModel.postValue(RefreshStateModel.failed())
    }

    override fun loading() {
        _refreshStateModel.postValue(RefreshStateModel.loading())
    }

    override fun handle(juList: MutableList<Ju>, it: List<Ju>) {
        juList.clear()
        juList.addAll(it)
    }

    override fun succeed(hasMore: Boolean) {
        _refreshStateModel.postValue(RefreshStateModel.succeed())
    }
}

class FooterHandler(private val _footerStateModel: MutableLiveData<FooterStateModel>) : ActionHandler {

    override fun loading() {
        _footerStateModel.postValue(FooterStateModel.loading())
    }

    override fun handle(juList: MutableList<Ju>, it: List<Ju>) {
        juList.addAll(it)
    }

    override fun succeed(hasMore: Boolean) {
        _footerStateModel.postValue(FooterStateModel.succeed(hasMore))
    }

    override fun failed() {
        _footerStateModel.postValue(FooterStateModel.failed())
    }
}