package info.hellovass.vuex.demo

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // init log
        iniLog()
    }

    private fun iniLog() {

        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(2)
                .methodOffset(5)
                .tag("AndroidVuex")
                .build()

        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}