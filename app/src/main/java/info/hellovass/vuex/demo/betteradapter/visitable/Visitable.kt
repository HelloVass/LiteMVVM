package info.hellovass.vuex.demo.betteradapter.visitable

import info.hellovass.vuex.demo.betteradapter.visitor.Visitor

/**
 * 被访问者
 */
interface Visitable {

    fun accept(visitor: Visitor): Int
}