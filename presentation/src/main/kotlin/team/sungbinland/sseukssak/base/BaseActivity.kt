/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.base

import android.graphics.Color
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.github.jisungbin.logeukes.LoggerType
import io.github.jisungbin.logeukes.logeukes
import land.sungbin.systemuicontroller.setSystemBarsColor
import team.sungbinland.sseukssak.util.extensions.collectWithLifecycle
import team.sungbinland.sseukssak.util.extensions.toMessage
import team.sungbinland.sseukssak.util.extensions.toast

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
) : AppCompatActivity() {

    abstract val vm: VM
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        setSystemBarsColor(
            color = Color.WHITE,
            darkIcons = true
        )

        vm.exceptionFlow.collectWithLifecycle(this) { exception ->
            logeukes(type = LoggerType.E) { exception }
            toast(exception.toMessage(applicationContext))
        }
    }
}
