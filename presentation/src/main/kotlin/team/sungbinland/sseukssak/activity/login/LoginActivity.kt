/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import io.github.jisungbin.logeukes.LoggerType
import io.github.jisungbin.logeukes.logeukes
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseActivity
import team.sungbinland.sseukssak.databinding.ActivityLoginBinding
import team.sungbinland.sseukssak.util.extensions.toast

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

    override val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding.vm = vm

        vm.checkLoginState { state ->
            when (state) {
                LoginState.LogOut -> {}
                LoginState.LoggedIn -> {
                    // TODO 메인 화면으로 이동 혹은 로그아웃 처리
                    logeukes(type = LoggerType.I) { "이미 로그인 된 상태입니다" }
                }
            }
        }

        vm.eventFlow
            .flowWithLifecycle(this.lifecycle)
            .onEach { event -> handleEvent(event) }
            .launchIn(lifecycleScope)
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.KakaoLogin -> kakaoLogin()
        is Event.SkipLogin -> skipLogin()
    }

    private fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                logeukes(type = LoggerType.E) { "카카오계정으로 로그인 실패\n$error" }
            } else if (token != null) {
                logeukes(type = LoggerType.I) { "카카오계정으로 로그인 성공 ${token.accessToken}" }
                loginSuccess()
                // TODO 메인 화면으로 이동
            }
        }
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            loginWithKakaoTalkApp(callback)
        } else {
            loginWithKakaoAccount(callback)
        }
    }

    private fun skipLogin() {
        logeukes { "로그인 건너 뛰기" }
    }

    private fun loginWithKakaoTalkApp(callback: (OAuthToken?, Throwable?) -> Unit) {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
                logeukes(type = LoggerType.E) { "loginWithKakaoTalkApp: error: $error" }
                loginWithKakaoAccount(callback)
            } else if (token != null) {
                // TODO 메인 화면으로 이동
                loginSuccess()
                logeukes(type = LoggerType.I) { "로그인 성공 ${token.accessToken}" }
            }
        }
    }

    private fun loginWithKakaoAccount(callback: (OAuthToken?, Throwable?) -> Unit) {
        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
    }

    private fun loginSuccess() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                toast("로그인 실패: $error")
            } else if (user != null) {
                toast("${user.kakaoAccount?.profile?.nickname}님 환영합니다!")
            }
        }
    }
}
