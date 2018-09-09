package info.hellovass.vuex.demo.ext

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

fun <T : ViewModel> AppCompatActivity.obtainVM(clz: Class<T>): T {
    return ViewModelProviders.of(this).get(clz)
}