package info.hellovass.vuex.demo.model

import info.hellovass.vuex.demo.betteradapter.visitable.Visitable
import info.hellovass.vuex.demo.betteradapter.visitor.Visitor

data class Ju(val id: Int, val title: String) : Visitable {

    override fun accept(visitor: Visitor): Int = visitor.visitJu(this)
}