package info.hellovass.vuex.demo.model

import io.reactivex.Observable

class JuRepo {

    companion object {
        const val COUNT = 10
    }

    fun getJus(): Observable<List<Ju>> {

        return Observable.create<List<Ju>> { emitter ->
            emitter.onNext(mockJus())
            emitter.onComplete()
        }
    }

    private fun mockJus(): List<Ju> =
            with(arrayListOf<Ju>()) {
                for (index in 1..COUNT) {
                    add(newJu(index))
                }
                this
            }

    private fun newJu(index: Int): Ju = Ju(index, "标题=>$index")
}