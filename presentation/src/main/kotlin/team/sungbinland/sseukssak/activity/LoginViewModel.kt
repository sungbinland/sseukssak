/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.base.BaseViewModel

class LoginViewModel : BaseViewModel() {
    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun kakaoLogin() {
        event(Event.KakaoLogin(Unit))
    }

    fun skipLogin() {
        event(Event.SkipLogin(Unit))
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class KakaoLogin(val p: Unit) : Event()
        data class SkipLogin(val p: Unit) : Event()
    }
}