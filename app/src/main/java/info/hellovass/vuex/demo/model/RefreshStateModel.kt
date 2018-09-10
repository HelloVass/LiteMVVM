package info.hellovass.vuex.demo.model

data class RefreshStateModel(val isRefreshing: Boolean) {

    companion object {

        fun loading() = RefreshStateModel(true)

        fun succeed() = RefreshStateModel(false)

        fun failed(): RefreshStateModel = RefreshStateModel(false)
    }
}