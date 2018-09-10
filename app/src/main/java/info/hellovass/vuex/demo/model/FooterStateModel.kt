package info.hellovass.vuex.demo.model

data class FooterStateModel(val status: Status) {

    enum class Status {
        Loading,
        NoMore,
        Completed,
        Failed,
    }

    companion object {

        /**
         * 加载中
         */
        fun loading(): FooterStateModel =
                FooterStateModel(status = Status.Loading)

        /**
         * 加载成功
         */
        fun succeed(hasMore: Boolean): FooterStateModel {
            return if (!hasMore)
                FooterStateModel(status = Status.NoMore)
            else
                FooterStateModel(status = Status.Completed)
        }

        /**
         * 加载失败
         */
        fun failed(): FooterStateModel =
                FooterStateModel(status = Status.Failed)
    }
}