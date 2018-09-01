package info.hellovass.vuex.demo.ju

import info.hellovass.vuex.demo.betteradapter.Visitable
import info.hellovass.vuex.demo.betteradapter.Visitor

class JuVisitable(private val ju: Ju) : Visitable {

    override fun accept(visitor: Visitor): Int = visitor.visitJu(ju)
}