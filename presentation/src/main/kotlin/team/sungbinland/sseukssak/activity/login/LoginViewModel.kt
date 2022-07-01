/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : BaseViewModel() {
    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    /**
     * 로그인 상태 확인
     *
     * @return 로그인 된 상태: LoginState.LoggedIn / 로그 아웃 상태: LoginState.LogOut
     * */
    fun checkLoginState() =
        if (repository.checkLoggedIn()) {
            LoginState.LoggedIn
        } else {
            LoginState.LogOut
        }

    /**
     * 유저 아이디 저장
     *
     * 유저 아이디가 저장되면 로그인 된 상태로 간주한다
     *
     * @param userId UserApiClient.instance.me.user.id
     * */
    fun saveLoginState(userId: Long) {
        repository.saveUserId(userId)
    }

    /**
     * 카카오 로그인 UI 이벤트
     * */
    fun kakaoLogin() {
        event(Event.KakaoLogin)
    }

    /**
     * 로그인 건너뛰기 UI 이벤트
     * */
    fun skipLogin() {
        event(Event.SkipLogin)
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}
