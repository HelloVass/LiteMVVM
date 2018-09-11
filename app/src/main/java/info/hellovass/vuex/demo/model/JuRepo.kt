package info.hellovass.vuex.demo.model

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class JuRepo {

    private val dataSource = arrayListOf<Ju>()

    init {
        for (item in 0..100) {
            dataSource.add(newJu(item))
        }
    }

    fun getJus(page: Int): Observable<List<Ju>> {

        return Observable.timer(800, TimeUnit.MILLISECONDS)
                .map { _ -> mockFetch(page) }
    }

    private fun mockFetch(page: Int): List<Ju> {

        return dataSource.subList(10 * (page - 1), 10 * page)
    }

    private fun newJu(index: Int): Ju = Ju(index, "标题=>$index")
}