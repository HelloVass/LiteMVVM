package info.hellovass.vuex.demo.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v7.util.DiffUtil
import info.hellovass.vuex.demo.ju.JuDiffCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class JuVM : ViewModel() {

    private val _jusDiffResult = MutableLiveData<Pair<List<Ju>, DiffUtil.DiffResult?>>()

    private val repo: JuRepo = JuRepo()

    fun juDiffResult() = _jusDiffResult

    fun loadData() {

        val emptyJus = emptyList<Ju>()

        val initialValue = Pair(emptyJus, null)

        repo.getJus()
                .scan<Pair<List<Ju>, DiffUtil.DiffResult?>>(initialValue) { pair, next ->

                    val callback = JuDiffCallback(pair.first, next)
                    val diffResult = DiffUtil.calculateDiff(callback)
                    Pair(next, diffResult)
                }
                .skip(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it -> _jusDiffResult.value = it }
    }
}