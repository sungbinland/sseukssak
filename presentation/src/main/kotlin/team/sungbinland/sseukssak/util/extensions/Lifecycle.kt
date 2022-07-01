/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.util.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 받은 [LifecycleOwner] 의 상태가 [Lifecycle.State.CREATED] 이상일 때 실행시키는 확장함수
 */
fun LifecycleOwner.launchedWhenCreated(action: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launchWhenCreated {
        action()
    }

}

/**
 * lifecycle에 맞게 collect/cancel 반복
 */
fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}