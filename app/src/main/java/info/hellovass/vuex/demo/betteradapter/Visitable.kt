package info.hellovass.vuex.demo.betteradapter

/**
 * 被访问者
 */
interface Visitable {

    fun accept(visitor: Visitor): Int
}