package info.hellovass.vuex.demo.model

import io.reactivex.Observable

class JuRepo {

    fun getJus(page: Int): Observable<List<Ju>> {

        return Observable.create<List<Ju>> { emitter ->
            emitter.onNext(mockJus(page))
            emitter.onComplete()
        }
    }

    private fun mockJus(page: Int): List<Ju> {

        val result = arrayListOf<Ju>()
        for (item in 10 * (page - 1)..(10 * page - 1)) {
            result.add(newJu(item))
        }
        return result
    }

    private fun newJu(index: Int): Ju = Ju(index, "标题=>$index")
}